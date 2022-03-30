package com146.HOME.CA.BE.domain.board;

import lombok.Data;

import java.sql.Blob;
import java.time.LocalDate;

@Data
public class Board {
    Long board_num;	            //NUMBER(10,0)
    Long cate_code;	            //NUMBER(10,0)
    String board_title;	        //VARCHAR2(150 BYTE)
    Long member_num;	          //NUMBER(10,0)
    String board_date;	        //TIMESTAMP(6)
    Long board_hit;	            //NUMBER(5,0)
    String board_content;	    //CLOB
    String board_map_address;	//VARCHAR2(300 BYTE)
    Blob board_picture;

    Long boardNum;	                    //게시물 시퀀스 NUMBER(10,0)
    int cateCode;	                    //게시판 분류 NUMBER(10,0)
    String boardTitle;	                //제목 VARCHAR2(150 BYTE)
    Long memberNum;	                  //회원 시퀀스 번호 NUMBER(10,0)
    String nickname;	                  //회원 별칭 varchar2(40)
    LocalDate boardDate;	                //작성일 TIMESTAMP(6)
    Long boardHit;	                    //조회수 NUMBER(5,0)
    String boardContent;	              //내용 CLOB
    String boardMapAddress;	          //지도 주소 VARCHAR2(300 BYTE)
    Blob boardPicture;                 //이미지

}
