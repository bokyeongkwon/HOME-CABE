package com146.HOME.CA.BE.domain.board.dao;


import com146.HOME.CA.BE.domain.board.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BoardDAOImpl implements BoardDAO {

    private final JdbcTemplate jdbcTemplate;

    //등록
    @Override
    public Long boardUpload(Board board) {

        StringBuffer sql = new StringBuffer();
        sql.append(" insert into board (board_num,cate_code,board_title,member_num,board_content,board_map_address) ");
        sql.append(" values(board_board_num_seq.nextval,?,?,?,?,?) ");

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql.toString(), new String[]{"board_num"});
                pstmt.setLong(1,board.getCate_code());
                pstmt.setString(2, board.getBoard_title());
                pstmt.setLong(3, board.getMember_num());
                pstmt.setString(4, board.getBoard_content());
                pstmt.setString(5, board.getBoard_map_address());
                return pstmt;
            }
        },keyHolder);



        return Long.valueOf(keyHolder.getKeys().get("board_num").toString());
    }

    //상세조회
    @Override
    public Board findByBoard_num(Long board_num) {
        return null;
    }

    //수정
    @Override
    public int boardUpdate(Long board_num, Board board) {

        StringBuffer sql = new StringBuffer();
        sql.append(" update board ");
        sql.append("   SET cate_code = ?, ");
        sql.append("       board_title = ?, ");
        sql.append("       board_content = ?, ");
        sql.append("       board_map_address = ?, ");
        sql.append("       board_date = systimestamp ");
        sql.append(" WHERE board_num = ? ");

        int updatedItemCount = jdbcTemplate.update(
                sql.toString(),
                board.getCate_code(),
                board.getBoard_title(),
                board.getBoard_content(),
                board.getBoard_map_address(),
                board_num
        );

        return updatedItemCount;

    }

    //삭제
    @Override
    public int deleteByBoard_num(Long board_num) {
        StringBuffer sql = new StringBuffer();
        sql.append("DELETE FROM board ");
        sql.append(" WHERE board_num = ? ");

        int updateItemCount = jdbcTemplate.update(sql.toString(), board_num);

        return updateItemCount;
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

    //조회수 증가
    @Override
    public int increaseHitCount(Long board_num) {
        StringBuffer sql = new StringBuffer();
        sql.append("update board  ");
        sql.append("set board_hit = board_hit + 1 ");
        sql.append("where board_num = ? ");

        int affectedRows = jdbcTemplate.update(sql.toString(), board_num);

        return affectedRows;
    }

    //전체건수
    @Override
    public int totalCount() {
        return 0;
    }
}
