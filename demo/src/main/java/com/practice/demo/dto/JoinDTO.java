package com.practice.demo.dto;

import lombok.Data;

@Data
public class JoinDTO {

    private String username;

    private String password;

    private String role;

    public boolean isPasswordEmpty(){
        return password == null || password.trim().isEmpty();
    }
}
