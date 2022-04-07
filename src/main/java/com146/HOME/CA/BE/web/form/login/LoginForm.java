package com146.HOME.CA.BE.web.form.login;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class LoginForm {

  @NotBlank
  private String id;            //아이디 VARCHAR2(40)
  @NotBlank
  private String pw;            //비밀번호 VARCHAR2(15)

  //private boolean autoLogin;  //자동로그인확인
}
