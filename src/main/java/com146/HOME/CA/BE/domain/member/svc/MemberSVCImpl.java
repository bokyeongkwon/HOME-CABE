package com146.HOME.CA.BE.domain.member.svc;


import com146.HOME.CA.BE.domain.member.Member;
import com146.HOME.CA.BE.domain.member.dao.MemberDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MemberSVCImpl implements MemberSVC {

    private final MemberDAO memberDAO;

    @Override
    public Member joinMember(Member member) {
        return null;
    }

    @Override
    public void editMember(Member member) {
        memberDAO.updateMember(member);
    }

    @Override
    public Member findById(String id) {
        return memberDAO.selectMemberById(id);
    }

    @Override
    public Member findByMemberNum(Long member_num) {
        return memberDAO.selectMemberByMemberNum(member_num);
    }

    @Override
    public List<Member> findAll() {
        return memberDAO.selectAll();
    }

    @Override
    public void outMember(Long member_num) {
        memberDAO.deleteMember(member_num);
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
