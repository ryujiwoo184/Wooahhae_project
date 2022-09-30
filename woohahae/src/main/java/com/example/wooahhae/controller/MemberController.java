package com.example.wooahhae.controller;

import com.example.wooahhae.dto.MemberDto.MemberSignupRequestDto;
import com.example.wooahhae.dto.SuccessDto;
import com.example.wooahhae.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/members/singup")
    public ResponseEntity<SuccessDto> memberCreate(@RequestBody MemberSignupRequestDto memberSignupRequestDto){
        return memberService.createMember(memberSignupRequestDto);
    }

    @GetMapping("/members/email-check")
    public ResponseEntity<SuccessDto> emailDuplicateCheck(String email){
        return memberService.emailDuplicateCheck(email);
    }
}
