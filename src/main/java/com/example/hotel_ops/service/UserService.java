package com.example.hotel_ops.service;


import com.example.hotel_ops.dto.request.UserRegistrationRequest;
import com.example.hotel_ops.dto.response.UserResponse;

public interface UserService {
    UserResponse registerUser(UserRegistrationRequest request);
}
