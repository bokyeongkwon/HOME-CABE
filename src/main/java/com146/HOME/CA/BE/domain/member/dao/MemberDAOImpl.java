package com146.HOME.CA.BE.domain.member.dao;


import com146.HOME.CA.BE.domain.member.Member;
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
public class MemberDAOImpl implements MemberDAO{

    private final JdbcTemplate jdbcTemplate;

    // 등록
    @Override
    public Member insertMember(Member member) {
        StringBuffer sql = new StringBuffer();
        sql.append(" insert into member ( ");
        sql.append("     member_num, ");
        sql.append("     id, ");
        sql.append("     name, ");
        sql.append("     pw, ");
        sql.append("     tel, ");
        sql.append("     email, ");
        sql.append("     birth, ");
        sql.append("     gender, ");
        sql.append("     nickname, ");
        sql.append("     show_list ");
        sql.append(" ) VALUES ( ");
        sql.append("     member_member_num_SEQ.nextval, ");
        sql.append("     ?, ");
        sql.append("     ?, ");
        sql.append("     ?, ");
        sql.append("     ?, ");
        sql.append("     ?, ");
        sql.append("     ?, ");
        sql.append("     ?, ");
        sql.append("     ?, ");
        sql.append("     ? ");


        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql.toString(), new String[]{"member_num"});
                pstmt.setString(1, member.getId());
                pstmt.setString(2, member.getName());
                pstmt.setString(3, member.getPw());
                pstmt.setString(4, member.getTel());
                pstmt.setString(5, member.getEmail());
                pstmt.setString(6, member.getBirth());
                pstmt.setInt(7, member.getGender());
                pstmt.setString(8, member.getNickname());
                pstmt.setInt(9, member.getShowList());


                return pstmt;
            }
        }, keyHolder);

        long memberNum = keyHolder.getKey().longValue();

        return selectMemberByMemberNum(memberNum);
    }

    //수정
    @Override
    public void updateMember(Member member) {


        StringBuffer sql = new StringBuffer();
        sql.append("update member ");
        sql.append("   set pw = ?, ");
        sql.append("       tel = ?, ");
        sql.append("       email = ?, ");
        sql.append("       gender = ?, ");
        sql.append("       nickname = ?, ");
        sql.append("       show_list = ?, ");
        sql.append("       udate = systimestamp ");
        sql.append(" where member_num = ? ");

        jdbcTemplate.update(
                sql.toString(),
                member.getPw(),
                member.getTel(),
                member.getEmail(),
                member.getGender(),
                member.getNickname(),
                member.getShowList(),
                member.getMemberNum());
    }

    //ID로찾기
    @Override
    public Member selectMemberById(String id) {

        StringBuffer sql = new StringBuffer();
        sql.append("select member_num, ");
        sql.append("       id, ");
        sql.append("       name, ");
        sql.append("       pw, ");
        sql.append("       tel, ");
        sql.append("       email, ");
        sql.append("       birth, ");
        sql.append("       gender, ");
        sql.append("       nickname, ");
        sql.append("       show_list, ");
        sql.append("       cdate, ");
        sql.append("       udate ");
        sql.append("  from member ");
        sql.append(" where id = ? ");

        Member member = jdbcTemplate.queryForObject(
                sql.toString(),
                new BeanPropertyRowMapper<>(Member.class),
                id
        );
        return member;
    }

    //멤버넘버로 찾기
    @Override
    public Member selectMemberByMemberNum(Long member_num) {

        StringBuffer sql = new StringBuffer();
        sql.append("select member_num, ");
        sql.append("       id, ");
        sql.append("       name, ");
        sql.append("       pw, ");
        sql.append("       tel, ");
        sql.append("       email, ");
        sql.append("       birth, ");
        sql.append("       gender, ");
        sql.append("       nickname, ");
        sql.append("       show_list, ");
        sql.append("       cdate, ");
        sql.append("       udate ");
        sql.append("  from member ");
        sql.append(" where member_num = ? ");

        Member member = jdbcTemplate.queryForObject(
                sql.toString(),
                new BeanPropertyRowMapper<>(Member.class),
                member_num
        );
        return member;
    }

    @Override
    public List<Member> selectAll() {
        StringBuffer sql = new StringBuffer();

        sql.append("select member_num, ");
        sql.append("       id, ");
        sql.append("       name, ");
        sql.append("       pw, ");
        sql.append("       tel, ");
        sql.append("       email, ");
        sql.append("       birth, ");
        sql.append("       gender, ");
        sql.append("       nickname, ");
        sql.append("       show_list, ");
        sql.append("       cdate, ");
        sql.append("       udate ");
        sql.append("  from member ");
        sql.append(" order by member_num desc ");

        List<Member> list = jdbcTemplate.query(
                sql.toString(),
                new BeanPropertyRowMapper<>(Member.class)
        );

        return list;
    }

    @Override
    public void deleteMember(Long member_num) {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from member ");
        sql.append(" where member_num = ? ");

        jdbcTemplate.update(sql.toString(), member_num);
    }

    @Override
    public boolean exitMember(String id) {
        return false;
    }

    @Override
    public Member login(String id, String pw) {
        return null;
    }

    @Override
    public boolean isMember(String id, String pw) {
        return false;
    }

    @Override
    public String findEmailByNickname(String nickname) {
        return null;
    }
}
