package com146.HOME.CA.BE.web.form.login;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FindID {

  @NotBlank(message = "이름을 입력하세요")
  private String name;          //이름 VARCHAR2(40)

  //전화번호 or 이메일
  private String tel;           //전화번호 VARCHAR2(13)
  private String email;         //이메일 VARCHAR2(40)
}
