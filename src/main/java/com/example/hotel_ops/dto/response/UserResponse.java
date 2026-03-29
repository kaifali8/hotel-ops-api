package com.example.hotel_ops.dto.response;

import com.example.hotel_ops.enums.RoleType;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class UserResponse {

    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private boolean active;
    private RoleType role;

}
