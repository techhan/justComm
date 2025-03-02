package com.hs.justComm.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class LoginResponseDto {

    private Long id;
    private String userId;
    private String nickname;
    private String email;
    private String job;
    private int age;

}
