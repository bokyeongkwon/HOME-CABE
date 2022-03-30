package com146.HOME.CA.BE.domain.board.dao;

import com146.HOME.CA.BE.domain.board.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

    /**
     * 게시글 작성 > 등록
     * @param board 게시글
     * @return 게시글 시퀀스(boardNum)
     */
    @Override
    public Long insertBoard(Board board) {
        StringBuffer sql = new StringBuffer();
        sql.append(" INSERT INTO board ( ");
        sql.append("     board_num, ");
        sql.append("     cate_code, ");
        sql.append("     board_title, ");
        sql.append("     member_num, ");
        sql.append("     nickname, ");
        sql.append("     board_content ");
        sql.append(" ) VALUES ( ");
        sql.append("     BOARD_board_num_SEQ.nextval, ");
        sql.append("     ?, ");
        sql.append("     ?, ");
        sql.append("     ?, ");
        sql.append("     ?, ");
        sql.append("     ? ) ");

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql.toString(), new String[]{"board_num"});
                pstmt.setInt(1, board.getCateCode());
                pstmt.setString(2, board.getBoardTitle());
                pstmt.setLong(3, board.getMemberNum());
                pstmt.setString(4, board.getNickname());
                pstmt.setString(5, board.getBoardContent());

                return pstmt;
            }
        }, keyHolder);

//    게시글 번호를 반환.
        return Long.valueOf(keyHolder.getKeys().get("board_num").toString());
    }

    /**
     * 본문 상세 조회
     * @param boardNum 게시글 시퀀스
     * @return 게시글 상세페이지
     */
    @Override
    public Board selectByNum(Long boardNum) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append("     board_num, ");
        sql.append("     cate_code, ");
        sql.append("     board_title, ");
        sql.append("     member_num, ");
        sql.append("     nickname, ");
        sql.append("     board_date, ");
        sql.append("     board_hit, ");
        sql.append("     board_content, ");
        sql.append("     board_map_address, ");
        sql.append("     board_picture ");
        sql.append(" FROM ");
        sql.append("     board ");
        sql.append(" where board_num = ? ");

        Board foundItem = null;
        try {
            foundItem = jdbcTemplate.queryForObject(sql.toString(),
                new BeanPropertyRowMapper<>(Board.class),
                boardNum);
        }catch (Exception e){
            foundItem = null;
        }
//    찾았으면 반환
        return foundItem;
    }

    /** 게시글 수정
     * 
     * @param boardNum 게시글 시퀀스
     * @param board 덮어 쓸(수정) 게시물 내용
     * @return 수정 성공 1
     */
    @Override
    public int updateByNum(Long boardNum, Board board) {
        StringBuffer sql = new StringBuffer();
        sql.append(" UPDATE board ");
        sql.append(" SET ");
        sql.append("     cate_code = ?, ");
        sql.append("     board_title = ?, ");
        sql.append("     board_content = ? ");
        sql.append(" WHERE board_num = ? ");

        int updateCnt = jdbcTemplate.update(sql.toString(),
            board.getCateCode(),
            board.getBoardTitle(),
            board.getBoardContent(),
            boardNum
        );
//    성공했으면 1 반환
        return updateCnt;
    }

    /**
     * 게시물 삭제
     * @param boardNum 게시물 시퀀스
     * @return 삭제 성공 횟수 1
     */
    @Override
    public int deleteByNum(Long boardNum) {
        String sql = " delete from board where board_num = ? ";
        int deleteCnt = jdbcTemplate.update(sql, boardNum);

        return deleteCnt;
    }

    /**
     * 조회수 증가
     * @param boardNum 게시물 시퀀스
     * @return 해당 게시물 조회수 +1
     */
    @Override
    public int increaseHit(Long boardNum) {
        StringBuffer sql = new StringBuffer();
        sql.append(" UPDATE board ");
        sql.append(" SET board_hit = board_hit+1 ");
        sql.append(" WHERE board_num = ? ");

        int updateHit = jdbcTemplate.update(sql.toString(), boardNum);
        return updateHit;
    }

    /**
     * 페이징 적용한 카테고리별 게시판 목록
     * @param cateCode 게시판 분류
     * @param startRec 시작 레코드
     * @param endRec 마지막 레코드
     * @return 목록
     */
    @Override
    public List<Board> selectBoard(int cateCode, int startRec, int endRec) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select t1.* from( ");
        sql.append("     SELECT ROW_NUMBER() OVER (ORDER BY board_num desc) no, ");
        sql.append("     board_num, ");
        sql.append("     cate_code, ");
        sql.append("     board_title, ");
        sql.append("     member_num, ");
        sql.append("     nickname, ");
        sql.append("     board_hit, ");
        sql.append("     board_date ");
        sql.append("     FROM ");
        sql.append("     board ");
        sql.append("     where cate_code = ? ) t1 ");
        sql.append(" where t1.no between ? and ? ");

        List<Board> list = jdbcTemplate.query(
            sql.toString(),
            new BeanPropertyRowMapper<>(Board.class),
            cateCode, startRec, endRec
        );
        return list;
    }

    /**
     * 카테고리별 게시물 총 개수
     * @param cateCode 카테고리
     * @return 해당 게시판 총 게시물 수
     */
    @Override
    public int totalCount(int cateCode) {
        String sql = " select count(*) from board where cate_code = ? ";

        Integer itemCnt = 0;
        try {
            itemCnt = jdbcTemplate.queryForObject(sql, Integer.class, cateCode);
        }catch (Exception e){
            itemCnt = 0;
        }
        return itemCnt;
    }
}
