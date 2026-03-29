package com.example.hotel_ops.dto.request;

import com.example.hotel_ops.enums.RoleType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class UserRegistrationRequest {

    @NotBlank(message = "Full Name is required")
    @Size(max = 100, message = "Full name must not exceed 100 characters")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 50, message = "Password must be between 6 and 50 characters")
    private String password;

    @NotBlank(message = "Phone number is required")
    @Size(max = 15, message = "Phone number must not exceed 15 characters")
    private String phoneNumber;

    @NotNull(message = "Role is required")
    private RoleType role;

    public @NotBlank(message = "Full Name is required") @Size(max = 100, message = "Full name must not exceed 100 characters") String getFullName() {
        return fullName;
    }

    public void setFullName(@NotBlank(message = "Full Name is required") @Size(max = 100, message = "Full name must not exceed 100 characters") String fullName) {
        this.fullName = fullName;
    }

    public @NotBlank(message = "Email is required") @Email(message = "Invalid email format") @Size(max = 100, message = "Email must not exceed 100 characters") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is required") @Email(message = "Invalid email format") @Size(max = 100, message = "Email must not exceed 100 characters") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Password is required") @Size(min = 6, max = 50, message = "Password must be between 6 and 50 characters") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required") @Size(min = 6, max = 50, message = "Password must be between 6 and 50 characters") String password) {
        this.password = password;
    }

    public @NotBlank(message = "Phone number is required") @Size(max = 15, message = "Phone number must not exceed 15 characters") String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotBlank(message = "Phone number is required") @Size(max = 15, message = "Phone number must not exceed 15 characters") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public @NotNull(message = "Role is required") RoleType getRole() {
        return role;
    }

    public void setRole(@NotNull(message = "Role is required") RoleType role) {
        this.role = role;
    }
}