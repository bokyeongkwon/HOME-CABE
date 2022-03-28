package com146.HOME.CA.BE.domain.mypage.DTO;

import lombok.Data;

@Data
public class Subscribe {
    private Long memberNum;       //구독자 회원번호
    private int subChk;           //구독 설정
    private int alarmChk;         //알림 설정
    private Long subMemberNum;    //구독 된 사람 회원번허
    private Long subNum;          //구독/알림 번호
    private String boardTitle;    //제목
    private Long boardNum;        //게시판 게시글 번호
}
