package com.hs.justComm.member.controller;

import com.hs.justComm.member.dto.MemberRequest;
import com.hs.justComm.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping()
    public ResponseEntity signup(@RequestBody MemberRequest memberRequest) {
        boolean result = memberService.signup(memberRequest);
        return ResponseEntity.ok(result);
    }
}
