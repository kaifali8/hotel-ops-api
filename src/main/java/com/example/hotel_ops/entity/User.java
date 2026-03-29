package com.example.hotel_ops.entity;

import com.example.hotel_ops.enums.RoleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User extends BaseEntity{

    @Column(name= "full_name",nullable = false,length = 100)
    private String fullName;

    @Column(nullable = false,unique = true,length = 100)
    private String email;

    @Column(nullable = false,length = 255)
    private String password;

    @Column(name = "phone_number",nullable = false,length = 15)
    private String phoneNumber;

    @Column(nullable = false)
    private Boolean active=true;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RoleType role;

}
