
package com146.HOME.CA.BE.web.form.board;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReplyForm {
    Long ReplyNum;                    //댓글번호 NUMBER(10)
    LocalDate replyDate;              //작성일 TIMESTAMP
    String replyContent;              //댓글내용 CLOB
    int replyGroup;                   //댓글그룹 NUMBER(5)
    int replyStep;                    //댓글의 단계 NUMBER(5)
    int replyIndent;                  //댓글의 들여쓰기 NUMBER(5)
    int preplyNum;                    //부모댓글번호 NUMBER(10)
}
