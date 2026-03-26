package com.mandy.springbootmall.service.impl;

import com.mandy.springbootmall.dao.UserDao;
import com.mandy.springbootmall.dto.UserRegisterRequest;
import com.mandy.springbootmall.model.User;
import com.mandy.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.register(userRegisterRequest);
    }
}
