package com.hs.justComm.auth.service;

import com.hs.justComm.auth.dto.LoginRequestDto;
import com.hs.justComm.auth.dto.LoginResponseDto;
import com.hs.justComm.auth.dto.SignupRequestDto;
import com.hs.justComm.auth.entity.Member;
import com.hs.justComm.auth.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final Map<String, String> tokenStore = new HashMap<>(); // 토큰 저장소

    /**
     * 회원가입
     * @param signupRequestDto
     * @return
     */
    @Transactional
    public boolean signup(SignupRequestDto signupRequestDto) {
        String encodedPassword = passwordEncoder.encode(signupRequestDto.getPassword());
        authRepository.save(signupRequestDto.toEntity(encodedPassword));
        return true;
    }

    /**
     * 로그인
     * @param loginRequestDto
     * @return
     */
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Member findMember = authRepository.findByUserId(loginRequestDto.getUserId())
                        .orElseThrow(() -> new IllegalArgumentException("회원 정보가 없습니다."));

        boolean passwordFlag = validatePassword(loginRequestDto.getPassword(), findMember.getPassword());

        String token = null;
        if(passwordFlag) {
            token = generateToken(findMember);
        } else {
            throw new IllegalArgumentException("비밀번호를 확인하세요.");
        }

        return LoginResponseDto.builder()
                .id(findMember.getId())
                .userId(findMember.getUserId())
                .email(findMember.getEmail())
                .job(findMember.getJob())
                .age(findMember.getAge())
                .nickname(findMember.getNickname())
                .token(token)
                .build();
    }

    /**
     * token 생성 후 반환
     * @param member
     * @return token
     */
    public String generateToken(Member member) {
        String token = UUID.randomUUID().toString(); // 간단한 토큰 생성
        tokenStore.put(token, member.getUserId());
        return token;
    }

    /**
     * 비밀번호 검사
     * @param inputPassword
     * @param memberPassword
     * @return boolean
     */
    public boolean validatePassword(String inputPassword, String memberPassword) {
        return passwordEncoder.matches(inputPassword, memberPassword);
    }

    /**
     * 토큰 유효성 검사
     * @param token
     * @return boolean
     */
    public boolean validateToken(String token) {
        return tokenStore.containsKey(token);
    }
}