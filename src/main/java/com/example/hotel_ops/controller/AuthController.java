package com.example.hotel_ops.controller;

import com.example.hotel_ops.dto.request.LoginRequest;
import com.example.hotel_ops.dto.request.UserRegistrationRequest;
import com.example.hotel_ops.dto.response.LoginResponse;
import com.example.hotel_ops.dto.response.UserResponse;
import com.example.hotel_ops.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRegistrationRequest request){
        UserResponse response = authenticationService.register(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        LoginResponse loginResponse=authenticationService.login(request);
        return ResponseEntity.ok(loginResponse);
    }
}
