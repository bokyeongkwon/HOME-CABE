package com146.HOME.CA.BE.domain.board;

import lombok.Data;

import java.sql.Blob;

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
}
