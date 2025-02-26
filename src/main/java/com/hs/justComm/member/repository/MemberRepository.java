package com.hs.justComm.member.repository;

import com.hs.justComm.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
