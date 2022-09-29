package com.example.woohahae.model;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Member extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

//    @Column(nullable = false)
//    private String profileUrl;

    @Builder
    public Member(String email,String userId, String password, String nickname){
        this.email = email;
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
    }
}
