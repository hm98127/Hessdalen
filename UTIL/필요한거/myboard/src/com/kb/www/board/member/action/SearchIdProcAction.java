package com.kb.www.board.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kb.www.board.member.service.MemberService;
import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;
import com.kb.www.common.LoginManager;

public class SearchIdProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		LoginManager lm = LoginManager.getInstance();
		String id = lm.getMemberId(request.getSession());
		if (id != null) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
            out.close();
            return null;
		}
		
		String name = request.getParameter("nm");
		if (name == null || name.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
            out.close();
            return null;
		}
		
		MemberService svc = new MemberService();
		id = svc.getMemberId(name);
		if (id == null) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('회원 정보를 찾을 수 없습니다.');history.back();</script>");
            out.close();
            return null;
		}
		request.setAttribute("id", id);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/member/yourId.jsp");
		return forward;
	}
}
















