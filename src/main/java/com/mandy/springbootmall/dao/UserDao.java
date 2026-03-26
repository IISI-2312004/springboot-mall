package com.mandy.springbootmall.dao;

import com.mandy.springbootmall.dto.UserRegisterRequest;
import com.mandy.springbootmall.model.User;

public interface UserDao {
    User getUserById(Integer userId);
    Integer register(UserRegisterRequest userRegisterRequest);
}
