package com146.HOME.CA.BE.domain.board.dao;

import com146.HOME.CA.BE.domain.board.Board;

import java.util.List;

public interface BoardDAO {
    //    num = seq

    //  게시물 등록.
    Long insertBoard(Board board);

    //  상세 조회
    Board selectByNum(Long boardNum);

    //  수정
    int updateByNum(Long boardNum, Board board);

    //  삭제
    int deleteByNum(Long boardNum);

    //  조회수 증가
    int increaseHit(Long boardNum);

    //  전체 조회. 각 게시판 분류별.
    List<Board> selectBoard(int cateCode, int startRec, int endRec);

    //게시판별 전체 게시물 수
    int totalCount(int cateCode);

    //댓글 등록
    Long insertReply(Board board);

    //댓글 조회
    List<Board> replyList(long boardNum, int startRec, int endRec);
    
    //댓글 수정
    int updateReply(Long replyNum, Board board);

    //댓글 삭제
    int deleteReply(Long replyNum);

}
