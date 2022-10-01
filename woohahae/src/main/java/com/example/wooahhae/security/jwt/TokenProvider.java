package com.example.wooahhae.security.jwt;

import com.example.wooahhae.repository.RefreshTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

@Slf4j
@Component
public class TokenProvider {
    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24;       // 임시 1일
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;     //7일

    private final Key key;

    private final RefreshTokenRepository refreshTokenRepository;

    private final UserDetailsServiceImpl userDetailsService;

    public TokenProvider(@Value("${jwt.secret}") String secretKey,
                         RefreshTokenRepository refreshTokenRepository,UserDetailServiceImpl userDetailService){
        this.refreshTokenRepository = refreshTokenRepository;
        this.userDetailsService = userDetailService;
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = keys.hmacShaKeyFor(keyBytes);
    }
}
