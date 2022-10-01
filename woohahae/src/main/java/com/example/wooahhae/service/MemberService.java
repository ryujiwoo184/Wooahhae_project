package com.example.wooahhae.service;

import com.example.wooahhae.dto.MemberDto.MemberLoginDto;
import com.example.wooahhae.dto.MemberDto.MemberResponseDto;
import com.example.wooahhae.dto.MemberDto.MemberSignupRequestDto;
import com.example.wooahhae.dto.MemberDto.TokenDto;
import com.example.wooahhae.dto.ResponseDto;
import com.example.wooahhae.dto.SuccessDto;
import com.example.wooahhae.exception.ex.DuplicateEmailException;
import com.example.wooahhae.exception.ex.MemberNotFoundException;
import com.example.wooahhae.model.Member;
import com.example.wooahhae.repository.MemberRepository;
import com.example.wooahhae.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    public ResponseEntity<SuccessDto> createMember(MemberSignupRequestDto memberSignupRequestDto){
        String uuid = UUID.randomUUID().toString();
        String userId = uuid.substring(0, 8);
        Member member = Member.builder()
                .email(memberSignupRequestDto.getEmail())
                .userId(userId)
                .nickname(memberSignupRequestDto.getNickname())
                .password(memberSignupRequestDto.getPassword())
                .build();
        memberRepository.save(member);
        return ResponseEntity.ok().body(SuccessDto.valueOf("true"));

    }

    @Transactional
    public ResponseEntity<SuccessDto> emailDuplicateCheck(String email){
        if(memberRepository.countByEmail(email)!= 0){
            throw new DuplicateEmailException();
        }
        return ResponseEntity.ok().body(SuccessDto.valueOf("true"));
    }

    @Transactional
    public ResponseDto<MemberResponseDto> login(MemberLoginDto memberLoginDto, HttpServletResponse response){
        Member member = memberRepository.findByEmail(memberLoginDto.getEmail())
                .orElseThrow(MemberNotFoundException::new);

        if(!passwordEncoder.matches(memberLoginDto.getPassword(), member.getPassword())){
            throw new MemberNotFoundException();
        }

        if(refreshTokenRepository.findByMember(member).isPresent()){
            refreshTokenRepository.deleteByMember(member);
        }

        TokenDto tokenDto = tokenProvider.generateTokenDto(member);
        tokenToHeaders(tokenDto, response);

        return ResponseDto.success(new MemberResponseDto(member));
    }
}
