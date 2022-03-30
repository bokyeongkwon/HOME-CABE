package com146.HOME.CA.BE.web.Controller;


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
            @PathVariable Long memberNum,
            Model model
    ){
        Member member = memberSVC.findByMemberNum(memberNum);
        DetailForm detailForm = new DetailForm();
        BeanUtils.copyProperties(member, detailForm);
        model.addAttribute("detailForm", detailForm);
        return "member/editMember";
    }

    //수정처리
    @PostMapping("/{member_num}/edit")
    public String edit(@PathVariable Long memberNum,
                       @ModelAttribute EditForm editForm,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes
    ) {
        memberSVC.findByMemberNum(memberNum);

        if(bindingResult.hasErrors()){
            return "member/editMember";
        }

        Member member = new Member(memberNum,null,null,editForm.getPw(),editForm.getTel(),editForm.getEmail(),null,editForm.getGender(),editForm.getNickname(),editForm.getShow_list(),null, LocalDateTime.now());

        memberSVC.editMember(member);

        redirectAttributes.addAttribute("memberNum",memberNum);
        return "redirect:/member/{memberNum}/edit";
    }

    //회원탈퇴
    @GetMapping("/{memberNum}/del")
    public String delete(@PathVariable Long memberNum){
        memberSVC.outMember(memberNum);
        return "redirect:/main";
    }

}
