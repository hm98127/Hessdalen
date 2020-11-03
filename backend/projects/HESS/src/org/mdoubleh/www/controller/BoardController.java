package org.mdoubleh.www.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdoubleh.www.common.Action;
import org.mdoubleh.www.common.ActionForward;
import org.mdoubleh.www.member.action.MemberJoinAction;
import org.mdoubleh.www.member.action.MemberJoinProcAction;
import org.mdoubleh.www.member.action.MemberLoginAction;
import org.mdoubleh.www.member.action.MemberLoginProcAction;
import org.mdoubleh.www.member.action.MemberLogoutAction;
import org.mdoubleh.www.member.action.MemberModifyPwdAction;
import org.mdoubleh.www.member.action.MemberPageAction;

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
		if (command.equals("/memberJoin.do")) {
			action = new MemberJoinAction();
		} else if (command.equals("/memberJoinProc.do")) {
			action = new MemberJoinProcAction();
		} else if (command.equals("/memberLogin.do")) {
			action = new MemberLoginAction();
		} else if (command.equals("/memberLoginProc.do")) {
			action = new MemberLoginProcAction();
		} else if (command.equals("/memberLogout.do")) {
			action = new MemberLogoutAction();
		} else if (command.equals("/memberPage.do")) {
			action = new MemberPageAction();
		} else if (command.equals("/memberModifyPwd.do")) {
			action = new MemberModifyPwdAction();
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
