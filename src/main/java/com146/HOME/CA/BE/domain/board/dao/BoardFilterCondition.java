package com146.HOME.CA.BE.domain.board.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class BoardFilterCondition {

  private String category;        //분류코드
  private int startRec;           //시작레코드번호
  private int endRec;             //종료레코드번호
  private String searchType;      //검색유형
  private String keyword;         //검색어
}
