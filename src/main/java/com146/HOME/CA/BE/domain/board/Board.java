package com146.HOME.CA.BE.domain.board;

import lombok.Data;

import java.sql.Blob;
import java.time.LocalDate;

@Data
public class Board {
    private Long boardNum;	                    //게시물 시퀀스 NUMBER(10,0)
    private int cateNum;	                    //게시판 분류 NUMBER(10,0)
    private String boardTitle;	                //제목 VARCHAR2(150 BYTE)
    private Long memberNum;	                  //회원 시퀀스 번호 NUMBER(10,0)
    private String nickname;	                  //회원 별칭 varchar2(40)
    private LocalDate boardDate;	                //작성일 TIMESTAMP(6)
    private Long boardHit;	                    //조회수 NUMBER(5,0)
    private String boardContent;	              //내용 CLOB
    private String boardMapAddress;	          //지도 주소 VARCHAR2(300 BYTE)
    private Blob boardPicture;                 //이미지

    private Long ReplyNum;                    //댓글번호 NUMBER(10)
    private LocalDate replyDate;              //작성일 TIMESTAMP
    private String replyContent;              //댓글내용 CLOB
    private int replyGroup;                   //댓글그룹 NUMBER(5)
    private int replyStep;                    //댓글의 단계 NUMBER(5)
    private int replyIndent;                  //댓글의 들여쓰기 NUMBER(5)
    private int preplyNum;                    //부모댓글번호 NUMBER(10)

}
