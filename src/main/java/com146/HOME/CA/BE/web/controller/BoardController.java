package com146.HOME.CA.BE.web.controller;

import com146.HOME.CA.BE.domain.board.Board;
import com146.HOME.CA.BE.domain.board.svc.BoardSVC;
import com146.HOME.CA.BE.domain.common.category.Category;
import com146.HOME.CA.BE.domain.common.category.CategoryDAO;
import com146.HOME.CA.BE.domain.common.paging.PageCriteria;
import com146.HOME.CA.BE.web.form.board.ListForm;
import com146.HOME.CA.BE.web.form.member.DetailForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

  private final BoardSVC boardSVC;
  private final CategoryDAO categoryDAO;

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
  public List<Category> classifier(){
//    13개 일반 게시판 분류 전부 반환
    return categoryDAO.category();
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
//    작성 양식
  @GetMapping("/add")
  public String boardAdd(){

    return "/board/boardAdd";
  }

  //    작성 처리.
  @PostMapping("/add")
  public String add(){

    return "redirect:/board/{num}/detail";
  }


  //    상세 조회
  @GetMapping("/{num}/detail")
  public String boardDetail(
      @PathVariable Long num,
      Model model
  ){
    Board detail = boardSVC.selectByNum(num);
    DetailForm detailForm = new DetailForm();
    BeanUtils.copyProperties(detail, detailForm);
    model.addAttribute("detailForm", detailForm);

    return "/board/boardDetail";
  }


  //    수정 양식
  @GetMapping("/{num}/edit")
  public String boardEdit(){

    return  "/board/boardEdit";
  }

  //    수정 처리
  @PostMapping("/{num}/edit")
  public String edit(){

    return  "redirect:/board/{num}/detail";
  }

  //    삭제
  @GetMapping("/{num}/delete")
  public String delete(){

    return "redirect:/board";
  }





}
