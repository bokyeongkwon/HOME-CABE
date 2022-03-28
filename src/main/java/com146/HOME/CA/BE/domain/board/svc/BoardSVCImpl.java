package com146.HOME.CA.BE.domain.board.svc;


import com146.HOME.CA.BE.domain.board.Board;
import com146.HOME.CA.BE.domain.board.dao.BoardDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BoardSVCImpl implements BoardSVC {

    private final BoardDAO boardDAO;

    //등록
    @Override
    public Long boardUpload(Board board) {
        return boardDAO.boardUpload(board);
    }

    //등록 - 첨부파일 포함
    @Override
    public Long boardUpload(Board board, List<MultipartFile> files) {
        return null;
    }

    //상세조회
    @Override
    public Board findByBoard_num(Long board_num) {
        return null;
    }

    //수정
    @Override
    public int boardUpdate(Long board_num, Board board) {
        return boardDAO.boardUpdate(board_num,board);
    }

    //삭제
    @Override
    public int deleteByBoard_num(Long board_num) {
        return boardDAO.deleteByBoard_num(board_num);
    }

    //전체조회
    @Override
    public List<Board> findAll() {
        return null;
    }

    //카테고리별 전체조회
    @Override
    public List<Board> findAll(Long cate_code) {
        return null;
    }

    //전체건수
    @Override
    public int totalCount() {
        return 0;
    }
}
