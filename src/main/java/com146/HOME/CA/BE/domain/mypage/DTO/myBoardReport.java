package com146.HOME.CA.BE.domain.mypage.DTO;

import java.sql.Clob;
import java.time.LocalDateTime;

public class myBoardReport {
    private Long reportNum;         //신고번호
    private Long memberNUm;         //회원번호
    private LocalDateTime reportDate;   //신고일
    private Clob reportContent;     //신고 내용
    private Long boardNum;          //게시글 번호
    private int reportProceed;      //신고 진행 사항
    private int result;             //신고 결과
    private Clob resultReason;      //신고 결과 이유
    private LocalDateTime resultDate;   //신고 결과 처리일
}
