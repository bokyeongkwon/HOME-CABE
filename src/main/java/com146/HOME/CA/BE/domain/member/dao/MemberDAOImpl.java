package com146.HOME.CA.BE.domain.member.dao;


import com146.HOME.CA.BE.domain.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberDAOImpl implements MemberDAO{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Member insertMember(Member member) {
        return null;
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
                member.getShow_list(),
                member.getMember_num());
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
