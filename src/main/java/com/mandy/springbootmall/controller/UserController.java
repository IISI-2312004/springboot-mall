package com.mandy.springbootmall.controller;

import com.mandy.springbootmall.dto.UserLoginRequest;
import com.mandy.springbootmall.dto.UserRegisterRequest;
import com.mandy.springbootmall.model.User;
import com.mandy.springbootmall.service.UserService;
import com.mandy.springbootmall.service.impl.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
        public ResponseEntity<User> register(
                @RequestBody @Valid UserRegisterRequest userRegisterRequest
    ){
        Integer userId = userService.register(userRegisterRequest);
        User user = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/user/login")
        public ResponseEntity<User> login(
                @RequestBody @Valid UserLoginRequest userLoginRequest
    ){
        User user = userService.login(userLoginRequest);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
