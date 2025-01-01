package com.practice.demo.service;

import com.practice.demo.dto.JoinDTO;
import com.practice.demo.entity.UserEntity;
import com.practice.demo.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }

    // DTO에 적었던 것을 먼저 getter로 받아준다.
    public void joinProcess(JoinDTO joinDTO){
        String username = joinDTO.getUsername();
        String password = joinDTO.getPassword();

        // 관리자 권한을 어떻게 부여할건지 고민 해야합니다.
        // 보통 회원가입할 때 role 부분은 보여주지 않기 때문에
        // 아이디 비밀번호로만 가입하면 일반 유저로 하게 합니다.
        // 그리고 특수한 가입으로 방법으로 하면 admin유저로 가게 합니다.
        String role = joinDTO.getRole();
        if(role == null || "".equals(role) || role.equals("user") || role.equals("User")){
            role = "ROLE_USER";
        } else if (role.equals("admin")||role.equals("Admin")){
            role = "ROLE_ADMIN";
        }


        Boolean isExist = userRepository.existsByUsername(username);

        if (isExist){
            return;
        }
        UserEntity data = new UserEntity();


        // 아까 DTO에 작성했던 것을 setter로 옮겨 적어준다.
        data.setUsername(username);
        data.setPassword(bCryptPasswordEncoder.encode(password));
        data.setRole(role);

        userRepository.save(data);
    }
}