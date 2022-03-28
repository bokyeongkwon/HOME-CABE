package com146.HOME.CA.BE.domain.board.dao;


import com146.HOME.CA.BE.domain.board.Board;

import java.util.List;

public interface BoardDAO {

    /**
     * 등록
     * @param board 등록내용
     * @return  게시글 번호
     */
    Long boardUpload(Board board);

    /**
     * 상세조회
     * @param board_num 게시글 번호
     * @return  게시글
     */
    Board findByBoard_num(Long board_num);

    /**
     * 수정
     * @param board_num 게시글 번호
     * @param board 수정내용
     * @return  수정건수
     */
    int boardUpdate(Long board_num, Board board);

    /**
     * 삭제
     * @param board_num 게시글 번호
     * @return  삭제건수
     */
    int deleteByBoard_num(Long board_num);

    /**
     * 전체조회
     * @return
     */
    List<Board> findAll();
    List<Board> findAll(Long cate_code);

    /**
     * 조회수 증가
     * @param board_num 게시글 번호
     * @return  수정건수
     */
    int increaseHitCount(Long board_num);

    /**
     * 전체건수
     * @return 게시글 전체건수
     */
    int totalCount();


}
