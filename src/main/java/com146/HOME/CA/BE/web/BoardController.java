package com146.HOME.CA.BE.web;

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

  //  페이징(10, 10)
  @Autowired
  @Qualifier("pc10")
  private PageCriteria pc10;

  //    페이징(9, 5)
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
      @RequestParam int cateCode,
      Model model
  ){
//    게시판 만들기 로직 추출
    makingBoard(reqPage, cateCode, model);
    //   분류별 적절한 게시판으로 이동.
    if( cateCode == 41 ){
      return "/board/bakingClass";
    }else if( cateCode == 51 || cateCode == 52  ){
      return "/board/boardCommu";
    }else{
      return "/board/boardList";
    }
  }

  // 게시판 만드는 메소드
  private void makingBoard(Optional<Integer> reqPage, int cateCode, Model model) {
    //   페이지 컨셉에 맞게 페이징 구분
    PageCriteria pc = null;
    if(cateCode == 41 || cateCode == 51 || cateCode == 52 ){
      pc = pc10;
    }else{
      pc = pc5;
    }

//    페이지 요청이 없으면 1페이지
    Integer page = reqPage.orElse(1);

    pc.getRc().setReqPage(page);
    List<Board> list = null;

    pc.setTotalRec(boardSVC.totalCount(cateCode));
    list = boardSVC.selectBoard(cateCode, pc.getRc().getStartRec(), pc.getRc().getEndRec());

//  ListForm 과 데이터 대조, 복사
    List<ListForm> partOfList = new ArrayList<>();
    for (Board board : list) {
      ListForm listForm = new ListForm();
      BeanUtils.copyProperties(board, listForm);
      partOfList.add(listForm);
    }
    model.addAttribute("list", partOfList);
    model.addAttribute("pc", pc);
    model.addAttribute("catecode", cateCode);
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