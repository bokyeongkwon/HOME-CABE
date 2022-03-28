package com146.HOME.CA.BE.domain.mypage.SVC;

import com146.HOME.CA.BE.domain.mypage.DTO.*;

import java.util.List;

public interface MyPageSVC {
    // 구독/알림 목록
    List<Subscribe> subscribeList();
    // 알림 설정
    int setAlarm(int alarm);
    // 구독 취소
    int deleteSubscribe(long subNum);
    // 관심리스트 목록
    List<Like> likeList();
    // 관심리스트 삭제
    int deleteLike(long likeNum);
    // 내가 작성한 게시물 목록
    List<myBoard> myBoardList();
    // 내가 작성한 댓글 목록
    List<myReply> myReplyList();
    // 내가 신고한 게시물 목록
    List<myBoardReport> boardReportList();
    // 내가 신고한 댓글 목록
    List<myReplyReport> replyReportList();

}
