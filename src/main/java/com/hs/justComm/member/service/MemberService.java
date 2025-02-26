package com.hs.justComm.member.service;

import com.hs.justComm.member.dto.MemberRequest;
import com.hs.justComm.member.entity.Member;
import com.hs.justComm.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    /**
     * 회원가입
     * @param memberRequest
     * @return
     */
    @Transactional
    public boolean signup(MemberRequest memberRequest) {
        memberRepository.save(memberRequest.toEntity());
        return true;
    }
}
