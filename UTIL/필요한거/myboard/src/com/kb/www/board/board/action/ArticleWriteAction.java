package com.kb.www.board.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;
import com.kb.www.common.LoginManager;

public class ArticleWriteAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		LoginManager lm = LoginManager.getInstance();
		String id = lm.getMemberId(request.getSession());
		if (id == null) {
			String requestURI = request.getRequestURI();
			HttpSession session = request.getSession(true);
			session.setAttribute("callback", requestURI);
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그인이 필요한 서비스 입니다.');location.href='login.do';</script>");
            out.close();
            return null;
		}
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/article/writeForm.jsp");
		return forward;
	}
}
