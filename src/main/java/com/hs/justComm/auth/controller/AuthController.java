package com.hs.justComm.auth.controller;

import com.hs.justComm.auth.dto.LoginRequestDto;
import com.hs.justComm.auth.dto.LoginResponseDto;
import com.hs.justComm.auth.dto.SignupRequestDto;
import com.hs.justComm.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("signup")
    public ResponseEntity signup(@RequestBody SignupRequestDto signupRequestDto) {
        boolean result = authService.signup(signupRequestDto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto loginResponseDto = authService.login(loginRequestDto);
        if(loginResponseDto.getToken() == null) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디와 비밀번호를 확인해주세요.");
        }
        return ResponseEntity.ok(loginResponseDto);
    }
}
