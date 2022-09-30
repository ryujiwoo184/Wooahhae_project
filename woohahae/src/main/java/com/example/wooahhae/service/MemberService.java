package com.example.wooahhae.service;

import com.example.wooahhae.dto.MemberDto.MemberSignupRequestDto;
import com.example.wooahhae.dto.SuccessDto;
import com.example.wooahhae.exception.ex.DuplicateEmailException;
import com.example.wooahhae.model.Member;
import com.example.wooahhae.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

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
}
