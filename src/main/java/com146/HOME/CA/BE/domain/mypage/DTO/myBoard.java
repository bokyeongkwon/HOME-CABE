package com146.HOME.CA.BE.domain.mypage.DTO;

import lombok.Data;

import java.sql.Blob;
import java.time.LocalDateTime;

@Data
public class myBoard {
    private Long boardNum;      //게시판 게시글 번호
    private Long cateCode;      //분류코드
    private String boardTitle;  //게시글 제목
    private Long memberNum;     //회원번호
    private LocalDateTime boardDate;  //작성일
    private Long boardHit;      //조회수
    private Blob boardPicture;  //대표 사진
}
