package com146.HOME.CA.BE.domain.login.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;

@Slf4j
@Repository
@RequiredArgsConstructor
public class LoginDAOImpl implements LoginDAO{

  private final JdbcTemplate jdbcTemplate;


  // 아이디 찾기(전화번호)
  @Override
  public Member findTelID(String name, String tel) {

    StringBuffer sql = new StringBuffer();

    sql.append("select id ");
    sql.append("  from member ");
    sql.append("  where name = ? and tel = ? ");

    Member memberTelID = jdbcTemplate.queryForObject(
            sql.toString(),
            new BeanPropertyRowMapper<>(Member.class),
            name, tel);

    return memberTelID;
  }

  // 아이디 찾기(이메일)
  @Override
  public Member findEmailID(String name, String email) {

    StringBuffer sql = new StringBuffer();

    sql.append("select id ");
    sql.append("  from member ");
    sql.append("  where name = ? and email = ? ");

    Member memberEmailId = jdbcTemplate.queryForObject(
            sql.toString(),
            new BeanPropertyRowMapper<>(Member.class),
            name, email);

    return memberEmailId;
  }

  // 비밀번호 찾기(전화번호)
  @Override
  public Member findTelPW(String id, String tel) {

    StringBuffer sql = new StringBuffer();

    sql.append("select pw ");
    sql.append("  from member ");
    sql.append("  where name = ? and tel = ? ");

    Member memberTelPW = jdbcTemplate.queryForObject(
            sql.toString(),
            new BeanPropertyRowMapper<>(Member.class),
            id, tel);


    return memberTelPW;
  }

  // 비밀번호 찾기(이메일)
  @Override
  public Member findEmailPW(String id, String email) {

    StringBuffer sql = new StringBuffer();

    sql.append("select pw ");
    sql.append("  from member ");
    sql.append("  where name = ? and email = ? ");

    Member memberEmailPW = jdbcTemplate.queryForObject(
            sql.toString(),
            new BeanPropertyRowMapper<>(Member.class),
            id, email);

    return memberEmailPW;
  }
}
