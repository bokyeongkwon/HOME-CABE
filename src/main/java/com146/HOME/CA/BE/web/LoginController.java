package com146.HOME.CA.BE.web;

import com146.HOME.CA.BE.domain.member.Member;
import com146.HOME.CA.BE.domain.member.svc.MemberSVC;
import com146.HOME.CA.BE.web.form.login.LoginForm;
import com146.HOME.CA.BE.web.form.login.LoginMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

  private final MemberSVC memberSVC;

  /**
   * 로그인
   * @param loginForm
   * @param request
   * @return
   */
  @GetMapping("/login")
  public String loginForm(
          @ModelAttribute LoginForm loginForm,
          HttpServletRequest request){
    HttpSession httpSession = request.getSession(false);

    return "login/loginForm";

  }

  /**
   * 로그인처리
   * @param loginForm
   * @param bindingResult
   * @param redirectUrl
   * @return
   */
  @PostMapping("/login")
  public String login(
          @Valid
          @ModelAttribute LoginForm loginForm,
          BindingResult bindingResult,
          @RequestParam(name="redirectUrl",defaultValue = "/") String redirectUrl,
          Model model,
          HttpServletRequest request){

    //필드유효성 체크
    if(bindingResult.hasErrors()){
      log.info("loginError={}", bindingResult);
      return "login/loginForm";
    }
    //회원유무 체크
    if(!memberSVC.exitMember(loginForm.getId())){
      bindingResult.reject("loginFail.id");
      return "login/loginForm";
    }
    //로그인 체크
    Member member = memberSVC.login(loginForm.getId(),loginForm.getPw());
    if(member == null){
      bindingResult.reject("loginFail.pw");
      return "login/loginForm";
    }

    //로그인 성공
    HttpSession httpSession = request.getSession(true);
    //로그인 정보
    LoginMember loginMember = new LoginMember(member.getId(),member.getNickname());
    httpSession.setAttribute("loginMember", loginMember);
    //URL 재요청
    return "redirect:"+redirectUrl;

    //자동로그인설정
    //아직안함
  }

  /**
   * 로그아웃
   * @param request
   * @return
   */
  @GetMapping("logout")
  public String logout(HttpServletRequest request){

    //있으면 반환하고 없으면 null 반환
    HttpSession httpSession = request.getSession(false);
    if(httpSession != null){
      httpSession.invalidate();
      log.info("세션없응");
    }
    return "redirect:/";
  }

}
