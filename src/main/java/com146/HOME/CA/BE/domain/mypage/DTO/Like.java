package com146.HOME.CA.BE.domain.mypage.DTO;

import lombok.Data;

import java.sql.Blob;
import java.time.LocalDateTime;

@Data
public class Like {
    private Long likeNum;       //관심리스트 번호
    private Long memberNum;     //회원번호
    private int likeChk;        //관심리스트 체크 여부
    private LocalDateTime likeDate;   //찜한 날짜
    private Long boardNum;      //게시판 게시글 번호
    private Blob boardPicture;  //대표 사진
    private String boardTitle;  //게시글 제목
}
