package org.mdoubleh.www.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdoubleh.www.board.item.action.ItemDeleteAction;
import org.mdoubleh.www.board.item.action.ItemDetailAction;
import org.mdoubleh.www.board.item.action.ItemListAction;
import org.mdoubleh.www.board.item.action.ItemModifyAction;
import org.mdoubleh.www.board.item.action.ItemModifyProcAction;
import org.mdoubleh.www.board.item.action.ItemRegisterAction;
import org.mdoubleh.www.board.item.action.ItemWriteAction;
import org.mdoubleh.www.board.notice.action.NoticeDeleteAction;
import org.mdoubleh.www.board.notice.action.NoticeDetailAction;
import org.mdoubleh.www.board.notice.action.NoticeListAction;
import org.mdoubleh.www.board.notice.action.NoticeModifyAction;
import org.mdoubleh.www.board.notice.action.NoticeModifyProcAction;
import org.mdoubleh.www.board.notice.action.NoticeRegisterAction;
import org.mdoubleh.www.board.notice.action.NoticeWriteAction;
import org.mdoubleh.www.board.review.action.ReviewWriteAction;
import org.mdoubleh.www.common.Action;
import org.mdoubleh.www.common.ActionForward;
import org.mdoubleh.www.main.action.InfoAction;
import org.mdoubleh.www.member.action.GetMemberIdAction;
import org.mdoubleh.www.member.action.GetYourIdAction;
import org.mdoubleh.www.member.action.GetYourPwdAction;
import org.mdoubleh.www.member.action.GetMemberPwdAction;
import org.mdoubleh.www.member.action.GetMemberPwdProcAction;
import org.mdoubleh.www.member.action.MemberDeleteAction;
import org.mdoubleh.www.member.action.MemberDeleteProcAction;
import org.mdoubleh.www.member.action.MemberJoinAction;
import org.mdoubleh.www.member.action.MemberJoinProcAction;
import org.mdoubleh.www.member.action.MemberLoginAction;
import org.mdoubleh.www.member.action.MemberLoginProcAction;
import org.mdoubleh.www.member.action.MemberLogoutAction;
import org.mdoubleh.www.member.action.MemberModifyPwdAction;
import org.mdoubleh.www.member.action.MemberModifyPwdProcAction;
import org.mdoubleh.www.member.action.MemberPageAction;
import org.mdoubleh.www.member.navar.NaverCallbackAction;

@WebServlet("*.do")
public class BoardController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// POST 방식 한글 처리
		request.setCharacterEncoding("UTF-8");
		// GET Context Path
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;
		
		// Member
		if (command.equals("/memberJoin.do")) { // 회원가입
			action = new MemberJoinAction();
		} else if (command.equals("/memberJoinProc.do")) { // 회원가입 처리
			action = new MemberJoinProcAction();
		} else if (command.equals("/memberLogin.do")) { // 로그인
			action = new MemberLoginAction();
		} else if (command.equals("/memberLoginProc.do")) { // 로그인 처리
			action = new MemberLoginProcAction();
		} else if (command.equals("/memberLogout.do")) { // 로그아웃
			action = new MemberLogoutAction();
		} else if (command.equals("/memberPage.do")) { // 회원 페이지
			action = new MemberPageAction();
		} else if (command.equals("/memberModifyPwd.do")) { // 로그인 후 비밀번호 변경
			action = new MemberModifyPwdAction();
		} else if (command.equals("/memberModifyPwdProc.do")) { // 로그인 후 비밀번호 변경 처리
			action = new MemberModifyPwdProcAction();
		} else if (command.equals("/memberDelete.do")) { // 회원 탈퇴
			action = new MemberDeleteAction();
		} else if (command.equals("/memberDeleteProc.do")) { // 회원 탈퇴
			action = new MemberDeleteProcAction();
		} else if (command.equals("/getMemberId.do")) { // 아이디 찾기
			action = new GetMemberIdAction();
		} else if (command.equals("/getYourId.do")) { // 아이디 찾기 처리
			action = new GetYourIdAction();
		} else if (command.equals("/getMemberPwd.do")) {  // 비밀번호 찾기
			action = new GetMemberPwdAction();
		} else if (command.equals("/getMemberPwdProc.do")) { // 비밀번호 찾기 처리
			action = new GetMemberPwdProcAction();
		} else if (command.equals("/getYourPwd.do")) { // 비밀번호 변경
			action = new GetYourPwdAction();
		} else if (command.equals("/naverCallback.do")) { // 비밀번호 변경
			action = new NaverCallbackAction();
		} 
		
		// Board
		else if (command.equals("/info.do")) {
			action = new InfoAction();
		} else if (command.equals("/noticeList.do")) {
			action = new NoticeListAction();
		} else if (command.equals("/noticeWrite.do")) {
			action = new NoticeWriteAction();
		} else if (command.equals("/noticeRegister.do")) {
			action = new NoticeRegisterAction();
		} else if (command.equals("/noticeDetail.do")) {
			action = new NoticeDetailAction();
		} else if (command.equals("/noticeModify.do")) {
			action = new NoticeModifyAction();
		} else if (command.equals("/noticeModifyProc.do")) {
			action = new NoticeModifyProcAction();
		} else if (command.equals("/noticeDelete.do")) {
			action = new NoticeDeleteAction();
		} else if (command.equals("/itemList.do")) {
			action = new ItemListAction();
		} else if (command.equals("/itemWrite.do")) {
			action = new ItemWriteAction();
		} else if (command.equals("/itemRegister.do")) {
			action = new ItemRegisterAction();
		} else if (command.equals("/itemDetail.do")) {
			action = new ItemDetailAction();
		} else if (command.equals("/itemModify.do")) {
			action = new ItemModifyAction();
		} else if (command.equals("/itemRegisterProc.do")) {
			action = new ItemModifyProcAction();
		} else if (command.equals("/itemDelete.do")) {
			action = new ItemDeleteAction();
		} else if (command.equals("/reviewWrite.do")) {
			action = new ReviewWriteAction();
		}
		
		
		
		
		
		
		
		// GET Error
		if (action != null) {
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				forward = new ActionForward();
				forward.setPath("/views/error.jsp");
				forward.setRedirect(true);
			}

			if (forward != null) {
				if (forward.isRedirect()) {
					response.sendRedirect(forward.getPath());
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
					dispatcher.forward(request, response);
				}
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
