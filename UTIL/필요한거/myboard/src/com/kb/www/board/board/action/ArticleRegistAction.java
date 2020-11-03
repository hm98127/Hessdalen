package com.kb.www.board.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kb.www.board.board.service.ArticleService;
import com.kb.www.board.board.vo.ArticleVo;
import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;
import com.kb.www.common.LoginManager;

public class ArticleRegistAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		LoginManager lm = LoginManager.getInstance();
		String id = lm.getMemberId(request.getSession());
		if (id == null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("callback", "/write.do");
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그인이 필요한 서비스 입니다.');location.href='login.do';</script>");
            out.close();
            return null;
		}
		
		String sub = request.getParameter("sub");
		String cntnt = request.getParameter("cntnt");
		if (sub == null || sub.equals("") || sub.length() > 50
				|| cntnt == null || cntnt.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
            out.close();
            return null;
		}
		
		ArticleVo vo = new ArticleVo();
		vo.setWriter(id);
		vo.setSub(sub);
		vo.setCntnt(cntnt);
		
		ArticleService svc = new ArticleService();
		if(!svc.registerArticle(vo)) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('글 등록에 실패하였습니다.');history.back();</script>");
            out.close();
            return null;
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/list.do");
		forward.setRedirect(true);
		return forward;
	}
}











