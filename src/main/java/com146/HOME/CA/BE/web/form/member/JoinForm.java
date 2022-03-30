package com146.HOME.CA.BE.web.form.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class JoinForm {
    @NotNull
    private Long memberNum;             //회원번호 NUMBER(10)
    @NotBlank
    @Size(min=6, max=20)
    private String id;                  //아이디 VARCHAR2(40)
    @NotBlank
    @Size(min=2, max=20)
    private String name;                //이름 VARCHAR2(40)
    @NotBlank
    @Size(min=8, max=15)
    private String pw;                  //비밀번호 VARCHAR2(15)
    @NotBlank
    @Size(min=10, max=11)
    private String tel;                 //전화번호 VARCHAR2(13)
    @NotBlank
    @Size(min=6, max=20)
    private String email;               //이메일 VARCHAR2(40)
    @NotBlank
    @Size(min=8, max=8)
    private String birth;               //생년월일 VARCHAR2(10)
    private int gender;                 //성별 CHAR(1) DEFAULT '여'
    @NotBlank
    @Size(min=1, max=20)
    private String nickname;            //별칭 VARCHAR2(40)
    private int showList;               //관심리스트 공개 여부 CHAR(1) DEFAULT '공개'
    private LocalDateTime cdate;        //생성일시 TIMESTAMP
}
