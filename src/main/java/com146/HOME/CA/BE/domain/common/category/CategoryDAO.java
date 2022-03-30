package com146.HOME.CA.BE.domain.common.category;

import java.util.List;

public interface CategoryDAO {

//  상위 분류(매개값) > 하위 분류를 컬렉션으로 반환
  List<Category> category(int pcate);
//  like 연산자로 모든 게시판 카테고리를 출력하는 메소드 오버로딩
  List<Category> category();

  //  모든 카테고리 정보 반환.
  List<CategoryAll> categoryAll();
}
