package com146.HOME.CA.BE.web.form.login;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FindTelID {

  @NotBlank
  private String name;          //이름 VARCHAR2(40)
  @NotBlank
  private String tel;           //전화번호 VARCHAR2(13)
}
