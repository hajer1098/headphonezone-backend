package com.hajer.Headphone.Dto;


import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;
    private String password;
}
