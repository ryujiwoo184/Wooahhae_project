package com.example.wooahhae.repository;

import com.example.wooahhae.model.Member;
import com.example.wooahhae.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {


    Optional<RefreshToken> findByMember(Member member);
    void deleteByMember(Member member);
}
