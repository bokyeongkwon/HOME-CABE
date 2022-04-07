package com146.HOME.CA.BE.domain.login.dao;


import java.lang.reflect.Member;

public interface LoginDAO {

  /**
   * 아이디 찾기(전화번호)
   * @param name
   * @param tel
   * @return
   */
  Member findTelID(String name, String tel);

  /**
   * 아이디 찾기(이메일)
   * @param name
   * @param email
   * @return
   */
  Member findEmailID(String name, String email);

  /**
   * 비밀번호 찾기(전화번호)
   * @param id
   * @param tel
   * @return
   */
  Member findTelPW(String id, String tel);

  /**
   * 비밀번호 찾기(이메일)
   * @param id
   * @param email
   * @return
   */
  Member findEmailPW(String id, String email);

}
