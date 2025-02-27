package com.hs.justComm.auth.service;

import com.hs.justComm.auth.dto.LoginRequestDto;
import com.hs.justComm.auth.dto.LoginResponseDto;
import com.hs.justComm.auth.dto.SignupRequestDto;
import com.hs.justComm.auth.entity.Member;
import com.hs.justComm.auth.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;


    /**
     * 회원가입
     * @param signupRequestDto
     * @return
     */
    @Transactional
    public boolean signup(SignupRequestDto signupRequestDto) {
        authRepository.save(signupRequestDto.toEntity());
        return true;
    }

    /**
     * 로그인
     * @param loginRequestDto
     * @return
     */
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Member findMember = authRepository.findByUserIdAndPassword(loginRequestDto.getUserId()
                , loginRequestDto.getPassword()).orElseThrow(() -> new IllegalArgumentException("아이디와 비밀번호를 다시 확인하세요."));

        return LoginResponseDto.builder()
                .id(findMember.getId())
                .userId(findMember.getUserId())
                .email(findMember.getEmail())
                .job(findMember.getJob())
                .age(findMember.getAge())
                .nickname(findMember.getNickname())
                .build();
    }
}