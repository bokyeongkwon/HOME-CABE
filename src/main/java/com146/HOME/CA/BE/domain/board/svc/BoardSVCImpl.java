package com146.HOME.CA.BE.domain.board.svc;


import com146.HOME.CA.BE.domain.board.Board;
import com146.HOME.CA.BE.domain.board.dao.BoardDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardSVCImpl implements BoardSVC {

  private final BoardDAO boardDAO;

  /**
   * 등록
   * @param board
   * @return
   */
  @Override
  public Long insertBoard(Board board) {
    return boardDAO.insertBoard(board);
  }

  /**
   * 상세 조회, 조회수 +1
   * @param boardNum
   * @return
   */
  @Override
  public Board selectByNum(Long boardNum) {
    Board foundItem = boardDAO.selectByNum(boardNum);
    boardDAO.increaseHit(boardNum);
    return foundItem;
  }

  /**
   * 수정
   * @param boardNum
   * @param board
   * @return
   */
  @Override
  public int updateByNum(Long boardNum, Board board) {
    return boardDAO.updateByNum(boardNum, board);
  }

  /**
   * 삭제
   * @param boardNum
   * @return
   */
  @Override
  public int deleteByNum(Long boardNum) {
    return boardDAO.deleteByNum(boardNum);
  }

  /**
   * 페이징 적용된 카테고리별 게시판 목록
   * @param cateCode
   * @param startRec
   * @param endRec
   * @return
   */
  @Override
  public List<Board> selectBoard(int cateCode, int startRec, int endRec) {
    return boardDAO.selectBoard(cateCode, startRec, endRec);
  }

  /**
   * 카테고리별 게시물 총 개수
   * @param cateCode
   * @return
   */
  @Override
  public int totalCount(int cateCode) {
    return boardDAO.totalCount(cateCode);
  }

  /**
   * 댓글 작성
   * @param board
   * @return
   */
  @Override
  public Long insertReply(Board board) {
    return boardDAO.insertReply(board);
  }

  /**
   * 댓글 조회
   * @param boardNum
   * @param startRec
   * @param endRec
   * @return
   */
  @Override
  public List<Board> replyList(long boardNum, int startRec, int endRec) {
    return boardDAO.replyList(boardNum, startRec, endRec);
  }

  /**
   * 댓글 수정
   * @param replyNum
   * @param board
   * @return
   */
  @Override
  public int updateReply(Long replyNum, Board board) {
    return boardDAO.updateReply(replyNum, board);
  }

  /**
   * 댓글 삭제
   * @param replyNum
   * @return
   */
  @Override
  public int deleteReply(Long replyNum) {
    return boardDAO.deleteReply(replyNum);
  }
}
