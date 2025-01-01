package com.practice.demo.service;

import com.practice.demo.dto.CustomUserDetails;
import com.practice.demo.entity.UserEntity;
import com.practice.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userData = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("입력하신 아이디로 가입된 사용자를 찾을 수 없습니다. : " +username));
        return new CustomUserDetails(userData);
    }
}
