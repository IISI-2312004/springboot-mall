package com.mandy.springbootmall.dao;

import com.mandy.springbootmall.dto.UserRegisterRequest;
import com.mandy.springbootmall.model.User;

public interface UserDao {
    User getUserById(Integer userId);
    User getUserByEmail(String email);
    Integer register(UserRegisterRequest userRegisterRequest);
}
