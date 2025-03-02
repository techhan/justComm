package com.hs.justComm.auth.dto;

import com.hs.justComm.auth.entity.Member;
import lombok.Getter;

@Getter
public class SignupRequestDto {

    private String userId;
    private String password;
    private String nickname;
    private String email;
    private String job;
    private int age;


    public Member toEntity() {
        return Member.builder()
                .userId(this.userId)
                .password(this.password)
                .nickname(this.nickname)
                .email(this.email)
                .job(this.job)
                .age(this.age)
                .build();
    }
}
