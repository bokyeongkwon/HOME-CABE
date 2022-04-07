package com146.HOME.CA.BE.web.controller;


import com146.HOME.CA.BE.domain.member.Member;
import com146.HOME.CA.BE.domain.member.svc.MemberSVC;
import com146.HOME.CA.BE.web.form.member.DetailForm;
import com146.HOME.CA.BE.web.form.member.EditForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberSVC memberSVC;

    //수정 페이지
    @GetMapping("/{member_num}/edit")
    public String editMember(
            @PathVariable Long member_num,
            Model model
    ){
        Member member = memberSVC.findByMemberNum(member_num);
        DetailForm detailForm = new DetailForm();
        BeanUtils.copyProperties(member, detailForm);
        model.addAttribute("detailForm", detailForm);
        return "member/editMember";
    }

    수정처리
    @PostMapping("/{member_num}/edit")
    public String edit(@PathVariable Long member_num,
                       @ModelAttribute EditForm editForm,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes
    ) {
        memberSVC.findByMemberNum(member_num);

        if(bindingResult.hasErrors()){
            return "member/editMember";
        }

        Member member = new Member(member_num,null,null,editForm.getPw(),editForm.getTel(),editForm.getEmail(),null,editForm.getGender(),editForm.getNickname(),editForm.getShow_list(),null, LocalDateTime.now());

        memberSVC.editMember(member);

        redirectAttributes.addAttribute("member_num",member_num);
        return "redirect:/member/{member_num}/edit";
    }

    //회원탈퇴
    @GetMapping("/{member_num}/del")
    public String delete(@PathVariable Long member_num){
        memberSVC.outMember(member_num);
        return "redirect:/main";
    }

}
