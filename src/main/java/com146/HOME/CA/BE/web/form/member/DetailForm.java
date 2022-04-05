package com146.HOME.CA.BE.web.form.member;

import lombok.Data;

@Data
public class DetailForm {

    private Long memberNum;          //number(10)
    private String name;                //varchar2(40)
    private String id;                  //varchar2(40)
    private String pw;                  //varchar2(10)
    private String tel;                 //varchar2(13)
    private String email;               //varchar2(40)
    private String birth;               //DATE
    private int  gender;             //CHAR(1) default '1'
    private String nickname;            //varchar2(40)
    private int showList;           //CHAR(1) default '1'

}
