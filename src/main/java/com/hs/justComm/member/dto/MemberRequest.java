package com.hs.justComm.member.dto;

import com.hs.justComm.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberRequest {

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
