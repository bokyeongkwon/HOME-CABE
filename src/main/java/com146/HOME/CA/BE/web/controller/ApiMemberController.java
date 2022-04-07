package com146.HOME.CA.BE.web.controller;


import com146.HOME.CA.BE.domain.member.Member;
import com146.HOME.CA.BE.domain.member.svc.MemberSVC;
import com146.HOME.CA.BE.web.api.ApiResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
public class ApiMemberController {
    private final MemberSVC memberSVC;

    //멤버전체 조회
    @ResponseBody //http응답 메세지 바디에 직접 쓰기
    //(반환타입이 객체이면 java객체=>json포맷 문자열로 변환후)
    @GetMapping("/api/members")
    public ApiResult<List<Member>> members(){
        List<Member> list = memberSVC.findAll();
        ApiResult<List<Member>> result = new ApiResult<>("00","success",list);
        return result;
    }

    //멤버 하나조회
    @ResponseBody
    @GetMapping("/api/member/{id}")
    public ApiResult<Member> member(@PathVariable String id){
        Member member = memberSVC.findById(id);
        ApiResult<Member> result = new ApiResult<>("00","success",member);
        return result;
    }

    //멤버수정
    @ResponseBody
    @PatchMapping("/api/member/{id}")
    public ApiResult<Member> update(@PathVariable Long id, @RequestBody Member member){

        memberSVC.editMember(member);

        ApiResult<Member> result = new ApiResult<>("00","success",member);
        return result;
    }

    //회원탈퇴
    @ResponseBody
    @DeleteMapping("/api/member/{member_num}")
    public void delete(@PathVariable Long member_num){
        memberSVC.outMember(member_num);
    }

}
