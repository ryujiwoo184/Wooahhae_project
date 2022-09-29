package com.example.woohahae.controller;

import com.example.woohahae.dto.MemberDto.MemberSignupRequestDto;
import com.example.woohahae.dto.SuccessDto;
import com.example.woohahae.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
}
