package com146.HOME.CA.BE.web.controller;

import com146.HOME.CA.BE.domain.common.paging.PageCriteria;
import com146.HOME.CA.BE.domain.mypage.DTO.*;
import com146.HOME.CA.BE.domain.mypage.SVC.MyPageSVC;
import com146.HOME.CA.BE.web.form.login.LoginMember;
import com146.HOME.CA.BE.web.form.mypage.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/{memberNum}")
public class MyPageController {

    private final MyPageSVC myPageSVC;

    @Autowired
    @Qualifier("pc10")
    private PageCriteria pc10;

    // 마이페이지 화면(관심리스트 목록)
    @GetMapping({"/likelist",
                "/likelist/{reqPage}"})
    public String likeList(HttpServletRequest request,
                           @ModelAttribute LikeListForm likeListForm,
                           @PathVariable(required = false) Integer reqPage,
                           Model model){

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginMember") == null) {
            return "redirect:/login";
        }

        LoginMember loginMember = (LoginMember) session.getAttribute("loginMember");
        String id = loginMember.getId();

    // 페이지 요청이 업으면 1페이지로
        if(reqPage == null) reqPage = 1;
    // 1) 사용자 입력 - 요청 페이지
        pc10.getRc().setReqPage(reqPage);
    //  2) 게시판 타입의 리스트 객체를 생성
        List<Like> list = null;
    // 3) 해당 게시판의 게시물 총 개수를 구하고 페이징 적용 메소드로 게시판의 처음과 끝 페이지 출력
        pc10.setTotalRec(myPageSVC.likeCount(likeListForm.getLikeNum()));
        list = myPageSVC.likeList();

    // 4) ListListForm과 데이터를 대조해 복사
    List<LikeListForm> listForms = new ArrayList<>();
        for(Like like : list){
        LikeListForm likeLists = new LikeListForm();
        BeanUtils.copyProperties(like, likeLists);
        listForms.add(likeLists);

    }

        model.addAttribute("pc10", pc10);
        model.addAttribute("likeList", list);

        return "/mypage";

    }

    // 관심리스트 삭제
    @DeleteMapping("/{likeNum}")
    public String delLike(@PathVariable Long likeNum) {

        myPageSVC.deleteLike(likeNum);

        return "redirect:/mypage/{memberNum}/likelist";
    }

    // 구독/알림 목록
    @GetMapping({"/subscribe",
                 "/subscribe/{reqPage}"})
    public String SubscribeList(HttpServletRequest request,
                                @ModelAttribute SubscribeForm subscribeForm,
                                @PathVariable(required = false) Integer reqPage,
                                Model model) {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginMember") == null) {
            return "redirect:/login";
        }

        LoginMember loginMember = (LoginMember) session.getAttribute("loginMember");
        String id = loginMember.getId();

        // 페이지 요청이 업으면 1페이지로
        if(reqPage == null) reqPage = 1;
        // 1) 사용자 입력 - 요청 페이지
        pc10.getRc().setReqPage(reqPage);
        //  2) 게시판 타입의 리스트 객체를 생성
        List<Subscribe> list = null;
        // 3) 해당 게시판의 게시물 총 개수를 구하고 페이징 적용 메소드로 게시판의 처음과 끝 페이지 출력
        pc10.setTotalRec(myPageSVC.subCount(subscribeForm.getMemberNum()));
        list = myPageSVC.subscribeList();

        // 4) ListListForm과 데이터를 대조해 복사
        List<SubscribeForm> subscribeFormList = new ArrayList<>();
        for(Subscribe subscribe : list){
            SubscribeForm subscribeForm1 = new SubscribeForm();
            BeanUtils.copyProperties(subscribe, subscribeForm1);
            subscribeFormList.add(subscribeForm1);

        }

        model.addAttribute("pc10", pc10);
        model.addAttribute("subscribeList", list);

        return "/mypage/{memberNum}/subscribe";

    }

    // 알림 설정 수정 처리
    @PatchMapping("/subscribe")
    public String alarm(@PathVariable Long subNum,
                        @ModelAttribute int alarm,
                        RedirectAttributes redirectAttributes,
                        HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginMember") == null) {
            return "redirect:/login";
        }

        LoginMember loginMember = (LoginMember) session.getAttribute("loginMember");
        String id = loginMember.getId();

        Subscribe subscribe = new Subscribe();

        BeanUtils.copyProperties(alarm, subscribe);

        int modifiedSubscribe = myPageSVC.setAlarm(alarm);

        redirectAttributes.addAttribute("alarm", subscribe.getAlarmChk());

        return "redirect:/mypage/{memberNum}/subscribe";

    }

    // 구독 취소
    @DeleteMapping("/subscribe/{subNum}")
    public String delSub(@PathVariable Long subNum) {

        myPageSVC.deleteSubscribe(subNum);

        return "redirect:/mypage/{memberNum}/subscribe";
    }


    // 작성한 게시물 목록
    @GetMapping({"/boardList",
                "/boardList/{reqPage}"})
    public String myBoardList(@ModelAttribute MyBoardForm myBoardForm,
                              @PathVariable(required = false) Integer reqPage,
                              Model model){

        // 페이지 요청이 업으면 1페이지로
        if(reqPage == null) reqPage = 1;
        // 1) 사용자 입력 - 요청 페이지
        pc10.getRc().setReqPage(reqPage);
        //  2) 게시판 타입의 리스트 객체를 생성
        List<MyBoard> list = null;
        // 3) 해당 게시판의 게시물 총 개수를 구하고 페이징 적용 메소드로 게시판의 처음과 끝 페이지 출력
        pc10.setTotalRec(myPageSVC.boardCount(myBoardForm.getBoardNum()));
        list = myPageSVC.myBoardList();
        
        // 4) ListForm과 데이터를 대조해 복사
        List<MyBoardForm> myBoardFormList = new ArrayList<>();
        for(MyBoard myBoard : list){
            MyBoardForm myBoardForm1 = new MyBoardForm();
            BeanUtils.copyProperties(myBoard, myBoardForm1);
            myBoardFormList.add(myBoardForm1);
        }

        model.addAttribute("pc10", pc10);
        model.addAttribute("myBoardList", list);

        return "/mypage/{memberNum}/board";

    }

    //내가 작성한 게시물 삭제
    @DeleteMapping("/board/{boardNum}")
    public String delBoard(@PathVariable Long boardNum) {

        myPageSVC.deleteMyBoard(boardNum);

        return "redirect:/mypage/{memberNum}/board}";
    }

    // 내가 작성한 댓글 목록
    @GetMapping("/reply")
    public String myReplyList(HttpServletRequest request,
                              @ModelAttribute MyReplyForm myReplyForm,
                              @PathVariable(required = false) Integer reqPage,
                              Model model){

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginMember") == null) {
            return "redirect:/login";
        }

        LoginMember loginMember = (LoginMember) session.getAttribute("loginMember");
        String id = loginMember.getId();
        log.info(id);

        // 페이지 요청이 업으면 1페이지로
        if(reqPage == null) reqPage = 1;
        // 1) 사용자 입력 - 요청 페이지
        pc10.getRc().setReqPage(reqPage);
        //  2) 게시판 타입의 리스트 객체를 생성
        List<MyReply> list = null;
        // 3) 해당 게시판의 게시물 총 개수를 구하고 페이징 적용 메소드로 게시판의 처음과 끝 페이지 출력
        pc10.setTotalRec(myPageSVC.replyCount(myReplyForm.getReplyNum()));
        list = myPageSVC.myReplyList();

        // 4) ListForm과 데이터를 대조해 복사
        List<MyReplyForm> replyFormList = new ArrayList<>();
        for(MyReply reply : list){
            MyReplyForm replyForm = new MyReplyForm();
            BeanUtils.copyProperties(reply, replyForm);
            replyFormList.add(replyForm);
        }

        model.addAttribute("pc10", pc10);

        model.addAttribute("myReplyList", list);
        return "/mypage/{memberNum}/reply";

    }

    // 내가 작성한 댓글 삭제
    @DeleteMapping("/board/{boardNum}")
    public String delReply(@PathVariable Long replyNum) {

        myPageSVC.deleteSubscribe(replyNum);

        return "redirect:/mypage/{memberNum}/reply}";
    }

    // 내가 신고한 게시물 목록
    @GetMapping("/boardReport")
    public String myBoardReport(HttpServletRequest request,
                                @ModelAttribute BoardReportForm boardReportForm,
                                @PathVariable(required = false) Integer reqPage,
                                Model model) {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginMember") == null) {
            return "redirect:/login";
        }

        LoginMember loginMember = (LoginMember) session.getAttribute("loginMember");
        String id = loginMember.getId();
        log.info(id);

        // 페이지 요청이 업으면 1페이지로
        if(reqPage == null) reqPage = 1;
        // 1) 사용자 입력 - 요청 페이지
        pc10.getRc().setReqPage(reqPage);
        //  2) 게시판 타입의 리스트 객체를 생성
        List<MyBoardReport> list = null;
        // 3) 해당 게시판의 게시물 총 개수를 구하고 페이징 적용 메소드로 게시판의 처음과 끝 페이지 출력
        pc10.setTotalRec(myPageSVC.boardReportCount(boardReportForm.getReportNum(), boardReportForm.getBoardNum()));
        list = myPageSVC.boardReportList();

        // 4) ListForm과 데이터를 대조해 복사
        List<BoardReportForm> boardReportList = new ArrayList<>();
        for(MyBoardReport boardReport : list){
            BoardReportForm boardReportForm1 = new BoardReportForm();
            BeanUtils.copyProperties(boardReport, boardReportForm1);
            boardReportList.add(boardReportForm);
        }

        model.addAttribute("pc10", pc10);

        model.addAttribute("boardReportList", list);
        return "/mypahr/{memberNum}/boardReport";

    }


    // 내가 신고한 댓글 목록
    @GetMapping("/replyReport")
    public String myReplyReport(HttpServletRequest request,
                                @ModelAttribute ReplyReportForm replyReportForm,
                                @PathVariable(required = false) Integer reqPage,
                                Model model){

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginMember") == null) {
            return "redirect:/login";
        }

        LoginMember loginMember = (LoginMember) session.getAttribute("loginMember");
        String id = loginMember.getId();
        log.info(id);

        // 페이지 요청이 업으면 1페이지로
        if(reqPage == null) reqPage = 1;
        // 1) 사용자 입력 - 요청 페이지
        pc10.getRc().setReqPage(reqPage);
        //  2) 게시판 타입의 리스트 객체를 생성
        List<MyReplyReport> list = null;
        // 3) 해당 게시판의 게시물 총 개수를 구하고 페이징 적용 메소드로 게시판의 처음과 끝 페이지 출력
        pc10.setTotalRec(myPageSVC.replyReportCount(replyReportForm.getReportNum(), replyReportForm.getReportNum()));
        list = myPageSVC.replyReportList();

        // 4) ListForm과 데이터를 대조해 복사
        List<ReplyReportForm> replyReportList = new ArrayList<>();
        for(MyReplyReport replyReport : list){
            ReplyReportForm replyReportForm1 = new ReplyReportForm();
            BeanUtils.copyProperties(replyReport, replyReportForm1);
            replyReportList.add(replyReportForm);
        }

        model.addAttribute("pc10", pc10);

        model.addAttribute("replyReportList", list);
        return "/mypage/replyReport";

    }


}
