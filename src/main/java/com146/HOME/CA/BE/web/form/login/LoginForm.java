package com146.HOME.CA.BE.web.form.login;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class LoginForm {

  @NotBlank(message = "아이디를 입력하세요")
  private String id;            //아이디 VARCHAR2(40)
  @NotBlank(message = "비밀번호를 입력하세요")
  @Pattern(regexp = "^.*(?=^.{8,}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$")
  private String pw;            //비밀번호 VARCHAR@(15)

  //private boolean autoLogin;  //자동로그인확인
}
