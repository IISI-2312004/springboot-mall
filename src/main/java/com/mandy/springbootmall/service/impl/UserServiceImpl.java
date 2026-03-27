package com.mandy.springbootmall.service.impl;

import com.mandy.springbootmall.dao.UserDao;
import com.mandy.springbootmall.dto.UserLoginRequest;
import com.mandy.springbootmall.dto.UserRegisterRequest;
import com.mandy.springbootmall.model.User;
import com.mandy.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserService.class);



    @Autowired
    private UserDao userDao;
    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }
    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {

        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());

        if(user != null){
            log.warn("該email {} 已經被註冊" ,userRegisterRequest.getEmail());
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);

        }else {
        return userDao.register(userRegisterRequest);
        }

    }

    @Override
    public User login(UserLoginRequest userLoginRequest){
        User user = userDao.getUserByEmail(userLoginRequest.getEmail());
        if(user== null){
            log.warn("該 email {} 尚未註冊" ,userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if(user.getPassword().equals(userLoginRequest.getPassword())){
            return user;
        }else{
            log.warn("email {} 的密碼不正確", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
