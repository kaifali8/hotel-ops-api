package com.example.hotel_ops.service.impl;

import com.example.hotel_ops.dto.request.UserRegistrationRequest;
import com.example.hotel_ops.dto.response.UserResponse;
import com.example.hotel_ops.entity.User;
import com.example.hotel_ops.repository.UserRepository;
import com.example.hotel_ops.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, UserRepository userRepository1){
        this.userRepository = userRepository1;
    }

    @Override
    public UserResponse registerUser(UserRegistrationRequest request) {
        if (userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exists");
        }

        User user=new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); //Temporary plain, will encode later
        user.setPhoneNumber(request.getPhoneNumber());
        user.setActive(true);
        user.setRole(request.getRole());

        User savedUser=userRepository.save(user);

        return UserResponse.builder()
                .id(savedUser.getId())
                .fullName(savedUser.getFullName())
                .email(savedUser.getEmail())
                .phoneNumber(savedUser.getPhoneNumber())
                .active(savedUser.getActive())
                .role(savedUser.getRole())
                .build();
    }
}
