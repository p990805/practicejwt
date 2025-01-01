package com.practice.demo.dto;

import com.practice.demo.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final UserEntity userEntity;

    public CustomUserDetails(UserEntity userEntity) {

        this.userEntity = userEntity;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // userEntity의 role 값이 "ROLE_USER" 또는 "ROLE_ADMIN"으로 보장된다는 전제하에 처리
        String role = userEntity.getRole();
        if (role == null || role.isEmpty()) {
            // 예외 처리 또는 기본값 설정 (필요한 경우)
            role = "ROLE_USER";
        }
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }



    @Override
    public String getPassword(){

        return userEntity.getPassword();

    }

    @Override
    public String getUsername(){

        return userEntity.getUsername();

    }

    @Override
    public boolean isAccountNonExpired(){

        return true;

    }

    @Override
    public boolean isAccountNonLocked(){

        return true;

    }

    @Override
    public boolean isCredentialsNonExpired(){

        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}