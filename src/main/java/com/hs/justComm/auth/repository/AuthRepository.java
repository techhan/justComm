package com.hs.justComm.auth.repository;

import com.hs.justComm.auth.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUserIdAndPassword(String id, String password);
    Optional<Member> findByUserId(String id);
}
