package com146.HOME.CA.BE.web.controller;

import com146.HOME.CA.BE.domain.board.Board;
import com146.HOME.CA.BE.domain.board.svc.BoardSVC;
import com146.HOME.CA.BE.domain.common.category.Category;
import com146.HOME.CA.BE.domain.common.category.CategoryAll;
import com146.HOME.CA.BE.domain.common.category.CategoryDAO;
import com146.HOME.CA.BE.domain.common.file.UploadFile;
import com146.HOME.CA.BE.domain.common.file.svc.UploadFileSVC;
import com146.HOME.CA.BE.domain.common.paging.PageCriteria;
import com146.HOME.CA.BE.domain.member.svc.MemberSVC;
import com146.HOME.CA.BE.domain.reply.Reply;
import com146.HOME.CA.BE.domain.reply.svc.ReplySVC;
import com146.HOME.CA.BE.web.form.board.*;
import com146.HOME.CA.BE.web.form.login.LoginMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

  private final MemberSVC memberSVC;
  private final BoardSVC boardSVC;
  private final CategoryDAO categoryDAO;
  private final UploadFileSVC uploadFileSVC;
  private final ReplySVC replySVC;

  //    페이징 구현 (10, 10) - 디폴트?
  @Autowired
  @Qualifier("pc10")
  private PageCriteria pc10;

  //    페이징 구현 (9, 5)
  @Autowired
  @Qualifier("pc5")
  private PageCriteria pc5;

//  게시판 카테고리
  @ModelAttribute("classifier")
  public List<CategoryAll> classifier(){
//    13개 일반 게시판 분류 전부 반환
    return categoryDAO.categoryAll();
  }

  //  각 페이지 소제목, 왼쪽 카테고리 메뉴 하위 분류명 자동 렌더링
  @ModelAttribute("subTitle")
  public List<Category> subTitle(@RequestParam Integer cateNum){
//    log.info("cateNum={}",cateNum);
    int pcateNum = 0;
    switch (cateNum){
      case 11: case 12: case 13: case 14:
        pcateNum = 10;
        break;
      case 21: case 22: case 23: case 24:
        pcateNum = 20;
        break;
      case 31: case 32:
        pcateNum = 30;
        break;
      case 41:
        pcateNum = 40;
        break;
      case 51: case 52:
        pcateNum = 50;
        break;
      default:
        break;
    }
    return categoryDAO.category(pcateNum);
  }

  // 왼쪽 카테고리 메뉴에 상위 분류명 자동 렌더링
  @ModelAttribute("leftMenuTitle")
  public List<Category> leftMenuTitle(@RequestParam Integer cateNum){
//    log.info("cateNum={}", cateNum);
    int ccateNum = cateNum;
    return categoryDAO.superCategory(ccateNum);
  }


//  각 카테고리별 게시판 목록으로 이동
@GetMapping("/{reqPage}")
public String list(
    @PathVariable(required = false) Optional<Integer> reqPage,
    @RequestParam int cateNum,
    Model model
){
//        페이징 (10, 10)/(9,5) 분기
  PageCriteria pc = null;
  if(cateNum == 41 || cateNum == 51 || cateNum == 52){
    pc = pc10;
  }else{
    pc = pc5;
  }
  //페이지 요청이 없으면 1페이지로.
  Integer page = reqPage.orElse(1);
//      1) 사용자 입력 - 요청 페이지
  pc.getRc().setReqPage(page);
//       2) 게시판 타입의 리스트 객체를 생성
  List<Board> list = null;

//       3) 해당 게시판의 게시물 총 개수를 구하고 페이징 적용 메소드로 게시판의 처음과 끝 페이지 출력
  pc.setTotalRec(boardSVC.totalCount(cateNum));
  list = boardSVC.selectBoard(cateNum, pc.getRc().getStartRec(), pc.getRc().getEndRec());

//       4)ListForm과 데이터를 대조해 복사
  List<ListForm> partOfList = new ArrayList<>();
  for (Board board : list) {
    ListForm listForm = new ListForm();
    BeanUtils.copyProperties(board, listForm);
    partOfList.add(listForm);
  }
  model.addAttribute("list", partOfList);
  model.addAttribute("pc", pc);
  model.addAttribute("cateNum", cateNum);

  //   분류별 적절한 게시판으로 이동.
  if( cateNum == 41 ){
    return "/board/bakingClass";
  }else if( cateNum == 51 || cateNum == 52  ){
    return "/board/boardCommu";
  }else{
    return "/board/boardList";
  }
}



//  공통 CRUD
//작성양식
@GetMapping("/add")
public String addForm(
        Model model,
        @RequestParam(required = false) Optional<Integer> category,
        HttpSession session) {
  int cate = getCategory(category);

//    LoginMember loginMember = (LoginMember)session.getAttribute(SessionConst.LOGIN_MEMBER);

  AddForm addForm = new AddForm();
//    addForm.setMemberNum(memberSVC.findById(loginMember.getId()).getMemberNum());
//    addForm.setNickname(loginMember.getNickname());
  model.addAttribute("addForm", addForm);
  model.addAttribute("category", cate);

  return "board/boardUpload";
}

  //작성처리
  @PostMapping("/add")
  public String add(
          //@Valid
          @ModelAttribute AddForm addForm,
          @RequestParam(required = false) Optional<Integer> category,
          BindingResult bindingResult,      // 폼객체에 바인딩될때 오류내용이 저장되는 객체
          HttpSession session,
          RedirectAttributes redirectAttributes) throws IOException {
    log.info("addForm={}",addForm);

    if(bindingResult.hasErrors()){
      log.info("add/bindingResult={}",bindingResult);
      return "board/boardUpload";
    }

    int cate = getCategory(category);

    Board board = new Board();
    BeanUtils.copyProperties(addForm, board);

//    //세션 가져오기
//    LoginMember loginMember = (LoginMember)session.getAttribute(SessionConst.LOGIN_MEMBER);
//    //세션 정보가 없으면 로그인페이지로 이동
//    if(loginMember == null){
//      return "redirect:/login";
//    }
//
//    //세션에서 아이디,별칭가져오기
//    board.setMemberNum(memberSVC.findById(loginMember.getId()).getMemberNum());
//    board.setNickname(loginMember.getNickname());


    Long boardNum = 0l;
    //파일첨부유무
    if(addForm.getFiles().size() == 0) {
      boardNum = boardSVC.boardUpload(board);
    }else{
      boardNum = boardSVC.boardUpload(board, addForm.getFiles());
    }
    redirectAttributes.addAttribute("boardNum", boardNum);
    redirectAttributes.addAttribute("category",cate);
    // <=서버응답 302 get http://서버:port/board/10
    // =>클라이언트요청 get http://서버:port/board/10
    return "redirect:/board/{boardNum}";
  }


  //상세 조회
  @GetMapping("/{cateNum}/{boardNum}")
  public String detail(
          @PathVariable Long boardNum,
          @RequestParam(required = false) Optional<Integer> category,
          Model model,
          HttpServletRequest request) {

    int cate = getCategory(category);

    Board detailBoard = boardSVC.findByBoardNum(boardNum);
    com146.HOME.CA.BE.web.form.board.DetailForm detailForm = new DetailForm();
    BeanUtils.copyProperties(detailBoard, detailForm);
    model.addAttribute("detailForm", detailForm);
    model.addAttribute("category", cate);

    //첨부조회
    List<UploadFile> attachFiles = uploadFileSVC.getFilesByCateNumWithBoardNum(detailBoard.getCateNum(), detailBoard.getBoardNum());
    if(attachFiles.size() > 0){
      log.info("attachFiles={}",attachFiles);
      model.addAttribute("attachFiles", attachFiles);
    }

    //댓글 작성 양식
    ReplyForm replyForm = new ReplyForm();
    model.addAttribute("RelyForm", replyForm);

    //댓글 불러오기
    List<Reply> replyList = replySVC.showReply(boardNum);

    //불러온 댓글 null 체크
    if(replyList.isEmpty()){
      model.addAttribute("ReplyList", null);
    }else {
      model.addAttribute("ReplyList", replyList);
    }

    //댓글
    HttpSession session = request.getSession(false);
    LoginMember loginMember = (LoginMember) session.getAttribute("loginMember");

    return "board/boardDetail";
  }


  //수정양식
  @GetMapping("/{boardNum}/edit")
  public String editForm(
          @PathVariable Long boardNum,
          @RequestParam(required = false) Optional<Integer> category,
          Model model){
    int cate = getCategory(category);
    Board board = boardSVC.findByBoardNum(boardNum);

    EditForm editForm = new EditForm();
    BeanUtils.copyProperties(board,editForm);
    model.addAttribute("editForm", editForm);
    model.addAttribute("category",cate);

    //첨부조회
    List<UploadFile> attachFiles = uploadFileSVC.getFilesByCateNumWithBoardNum(board.getCateNum(), board.getBoardNum());
    if(attachFiles.size() > 0){
      log.info("attachFiles={}",attachFiles);
      model.addAttribute("attachFiles", attachFiles);
    }

    return "board/boardUpdate";
  }

  //수정처리
  @PostMapping("/{boardNum}/edit")
  public String edit(
          @PathVariable Long boardNum,
          @RequestParam(required = false) Optional<Integer> category,
          @Valid @ModelAttribute EditForm editForm,
          BindingResult bindingResult,
          RedirectAttributes redirectAttributes
  ) {

    if(bindingResult.hasErrors()){
      return "board/boardUpdate";
    }

    int cate = getCategory(category);
    Board board = new Board();
    BeanUtils.copyProperties(editForm, board);
    boardSVC.boardUpdate(boardNum,board);

    if(editForm.getFiles().size() == 0) {
      boardSVC.boardUpdate(boardNum, board);
    }else{
      boardSVC.boardUpdate(boardNum, board, editForm.getFiles());
    }
    redirectAttributes.addAttribute("boardNum",boardNum);
    redirectAttributes.addAttribute("category", cate);

    return "redirect:/board/{boardNum}";
  }

  //    삭제
  @GetMapping("/{boardNum}/del")
  public String del(
          @PathVariable Long boardNum,
          @RequestParam(required = false) Optional<Integer> category) {

    boardSVC.deleteByBoardNum(boardNum);
    int cate = getCategory(category);
    return "redirect:/board/list?category="+cate;
  }

  //댓글 작성
  @PostMapping("/{boardNum}/reply")
  public String insertReply(ReplyForm replyForm,
                            @PathVariable Long boardNum,
                            HttpServletRequest request,
                            RedirectAttributes redirectAttributes) {

    Reply reply = new Reply();
    BeanUtils.copyProperties(replyForm, reply);

    reply.setBoardNum(boardNum);

    HttpSession session = request.getSession(false);
    if(session != null && session.getAttribute("loginMember") != null) {
      LoginMember loginMember = (LoginMember)session.getAttribute("loginMember");

      String id = loginMember.getId();
      log.info(id);
    }


    replySVC.writeReply(reply);

    redirectAttributes.addAttribute("boardNum", boardNum);
    return "redirect:/board/{boardNum}";
  }


  //댓글 삭제
  @DeleteMapping("/{boardNum}/{replyNum}/")
  public String delReply(@PathVariable Long replyNum,
                         RedirectAttributes redirectAttributes) throws IllegalAccessException {

    Reply foundReply = replySVC.findParentReply(replyNum);
    Long boardNum = foundReply.getBoardNum();

    replySVC.deleteReply(replyNum);

    redirectAttributes.addAttribute("boardNum", boardNum);

    return "redirect:/board/{boardNum}/reply";
  }





  //쿼리스트링 카테고리 읽기, 없으면 ""반환
  private int getCategory(Optional<Integer> category) {
    int cate = category.isPresent()? category.get():null;
    log.info("category={}", cate);
    return cate;
  }
}
