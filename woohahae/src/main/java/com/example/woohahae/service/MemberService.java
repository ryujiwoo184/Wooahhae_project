package com.example.woohahae.service;

import com.example.woohahae.dto.MemberDto.MemberSignupRequestDto;
import com.example.woohahae.dto.SuccessDto;
import com.example.woohahae.model.Member;
import com.example.woohahae.repository.MemberRepository;
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
}
