package com146.HOME.CA.BE.domain.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private Long memberNum;            //number(10)
    private String id;                  //varchar2(40)
    private String name;                //varchar2(40)
    private String pw;                  //varchar2(10)
    private String tel;                 //varchar2(13)
    private String email;               //varchar2(40)
    private String birth;               //DATE
    private String gender;             //CHAR(1) default '1'
    private String nickname;            //varchar2(40)
    private String showList;           //CHAR(1) default '1'
    private LocalDateTime cdate;        //TIMESTAMP default systimestamp
    private LocalDateTime udate;        //TIMESTAMP

    public Member(String id, String name, String pw, String tel, String email, String birth, String nickname){
        this.id = id;
        this.name = name;
        this.pw = pw;
        this.tel = tel;
        this.email = email;
        this.birth = birth;
        this.nickname = nickname;
    }
}
