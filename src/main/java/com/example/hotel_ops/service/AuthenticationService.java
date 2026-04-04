package com.example.hotel_ops.service;

import com.example.hotel_ops.dto.request.LoginRequest;
import com.example.hotel_ops.dto.request.UserRegistrationRequest;
import com.example.hotel_ops.dto.response.LoginResponse;
import com.example.hotel_ops.dto.response.UserResponse;

public interface AuthenticationService {
    UserResponse register(UserRegistrationRequest request);
    LoginResponse login(LoginRequest request);
}
