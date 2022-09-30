package com.example.wooahhae.repository;

import com.example.wooahhae.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Long countByEmail(String email);
}
