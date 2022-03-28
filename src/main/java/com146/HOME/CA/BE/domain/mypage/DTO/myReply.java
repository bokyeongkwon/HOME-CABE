package com146.HOME.CA.BE.domain.mypage.DTO;

import lombok.Data;

import java.sql.Clob;
import java.time.LocalDateTime;

@Data
public class myReply {
    private Long replyNum;      //댓글 번호
    private Long boardNum;      //게시글 번호
    private Long memberNum;     //회원번호
    private LocalDateTime replyDate;  //작성일
    private Clob replyContent;  //댓글 내용
    private Long replyGroup;    //댓글 그룹
    private Long replyStep;     //댓글 단계
    private Long replyIndent;   //댓글 들여쓰기
    private Long preplyNum;     //부모댓글번호
    
}
