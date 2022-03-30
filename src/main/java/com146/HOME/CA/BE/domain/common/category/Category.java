package com146.HOME.CA.BE.domain.common.category;

import lombok.Data;

@Data
public class Category {
  private int cate;          //게시판 분류 코드 NUMBER(10,0)
  private String cateName;      //분류 코드명 VARCHAR2(60 BYTE)
}
