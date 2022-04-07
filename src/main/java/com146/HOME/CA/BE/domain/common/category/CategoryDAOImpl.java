package com146.HOME.CA.BE.domain.common.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CategoryDAOImpl implements CategoryDAO {
  //  DB연동
  private final JdbcTemplate jdbcTemplate;

  /**
   * 상위 분류 > 하위 분류
   * @param pcate 상위 분류 ex) 10
   * @return 하위 ex) 11, 12, 13, 14...
   */
  @Override
  public List<Category> category(int pcate) {
    StringBuffer sql = new StringBuffer();
    sql.append(" SELECT t1.cate_num cate, t1.cate_name cateName ");
    sql.append(" FROM category t1, category t2 ");
    sql.append(" where t1.pcate_num = t2.cate_num ");
    sql.append(" and t1.useyn = 'Y' ");
    sql.append(" and t1.pcate_num = ? ");

    List<Category> categories = jdbcTemplate.query(
        sql.toString(),
        new BeanPropertyRowMapper<>(Category.class),
        pcate
    );
    return categories;
  }

  /**
   * 모든 하위 게시판 분류
   * @return 11~52
   */
  @Override
  public List<Category> category() {
    StringBuffer sql = new StringBuffer();
    sql.append(" SELECT t1.cate_num cate, t1.cate_name cateName ");
    sql.append(" FROM category t1, category t2 ");
    sql.append(" where t1.pcate_num = t2.cate_num ");
    sql.append(" and t1.useyn = 'Y' ");
    sql.append(" and t1.pcate_num < 60 ");

    List<Category> boards = jdbcTemplate.query(
        sql.toString(),
        new BeanPropertyRowMapper<>(Category.class)
    );
    return boards;
  }

  /**
   * 카테고리 테이블의 모든 사용중 레코드 반환
   * @return 90번대 첨부파일 분류까지 반환
   */
  @Override
  public List<CategoryAll> categoryAll() {
    StringBuffer sql = new StringBuffer();
    sql.append(" select t1.pcate_num pcate, t2.cate_name pcateName, t1.cate_num ccate, t1.cate_name ccateName ");
    sql.append(" from category t1, category t2 ");
    sql.append(" where t1.pcate_num = t2.cate_num ");
    sql.append(" and t1.useyn = 'Y' ");

    List<CategoryAll> categoryAll = jdbcTemplate.query(
        sql.toString(),
        new BeanPropertyRowMapper<>(CategoryAll.class)
    );
    return categoryAll;
  }
}