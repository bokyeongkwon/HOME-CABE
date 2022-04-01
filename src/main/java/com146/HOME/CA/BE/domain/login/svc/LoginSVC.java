package com146.HOME.CA.BE.domain.login.svc;


public interface LoginSVC {

  /**
   * 아이디 찾기(전화번호)
   * @param name
   * @param tel
   * @return
   */
  String searchTelID(String name, String tel);

  /**
   * 아이디 찾기(이메일)
   * @param name
   * @param email
   * @return
   */
  String searchEmailID(String name, String email);

  /**
   * 비밀번호 찾기(전화번호)
   * @param id
   * @param tel
   * @return
   */
  String searchTelPW(String id, String tel);

  /**
   * 비밀번호 찾기(이메일)
   * @param id
   * @param email
   * @return
   */
  String searchEmailPW(String id, String email);

}
