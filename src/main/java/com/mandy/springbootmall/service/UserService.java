package com.mandy.springbootmall.service;

import com.mandy.springbootmall.dto.UserRegisterRequest;
import com.mandy.springbootmall.model.User;

public interface UserService {
    User getUserById(Integer userId);
    User getUserByEmail(String email);
    Integer register(UserRegisterRequest userRegisterRequest);
}
