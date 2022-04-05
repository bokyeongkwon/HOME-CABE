package com146.HOME.CA.BE.domain.common.category;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class CategoryDAOImplTest {

  @Autowired
  private CategoryDAO categoryDAO;

  @Test
  @DisplayName("특정 상위 카테고리에 소속된 하위 카테고리 반환")
  void category() {
    Integer pcate = 30;

    List<Category> sub = categoryDAO.category(pcate);
    Assertions.assertThat(sub.size()).isEqualTo(2);
    log.info("sub={}", sub);
  }

  @Test
  @DisplayName("하위 카테고리가 소속된 상위 카테고리 반환")
  void categorySuper() {
    Integer ccateNum = 32;
    
    List<Category> aSuper = categoryDAO.superCategory(ccateNum);
    log.info("aSuper={}", aSuper);
  }

  @Test
  @DisplayName("첨부파일까지 포함한 테이블의 모든 카테고리 반환")
  void categoryAll() {
    List<CategoryAll> categoryAlls = categoryDAO.categoryAll();
    log.info("categoryAlls={}",categoryAlls);
  }
}