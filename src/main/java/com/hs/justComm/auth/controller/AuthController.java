package com.hs.justComm.auth.controller;

import com.hs.justComm.auth.dto.LoginRequestDto;
import com.hs.justComm.auth.dto.LoginResponseDto;
import com.hs.justComm.auth.dto.SignupRequestDto;
import com.hs.justComm.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto loginResponseDto = authService.login(loginRequestDto);
        return ResponseEntity.ok(loginResponseDto);
    }
}
