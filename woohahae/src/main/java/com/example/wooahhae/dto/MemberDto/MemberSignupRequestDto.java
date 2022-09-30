package com.example.wooahhae.dto.MemberDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberSignupRequestDto {
    private String email;
    private String nickname;
    private String password;
}
