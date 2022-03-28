package com146.HOME.CA.BE.domain.member.dao;



import com146.HOME.CA.BE.domain.member.Member;

import java.util.List;

public interface MemberDAO {

  /**
   * 가입
   * @param member
   * @return
   */
  Member insertMember(Member member);

  /**
   * 수정
   * @param member
   */
  void updateMember(Member member);

  /**
   * 조회 by id
   * @param id
   * @return
   */
  Member selectMemberById(String id);

  /**
   * 조회 by member_num
   * @param member_num
   * @return
   */
  Member selectMemberByMemberNum(Long member_num);

  /**
   * 전체조회
   * @return
   */
  List<Member> selectAll();

  /**
   * 탈퇴
   * @param member_num
   */
  void deleteMember(Long member_num);

  /**
   * 회원유무 체크
   * @param id
   * @return
   */
  boolean exitMember(String id);

  /**
   * 로그인 인증
   * @param id
   * @param pw
   * @return
   */
  Member login(String id, String pw);

  /**
   * 비밀번호 일치여부 체크
   * @param id
   * @param pw
   * @return
   */
  boolean isMember(String id, String pw);

  /**
   * 아이디 찾기
   * @param nickname
   * @return
   */
  String findEmailByNickname(String nickname);

}
