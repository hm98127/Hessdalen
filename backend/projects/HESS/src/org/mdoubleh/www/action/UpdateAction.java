package org.mdoubleh.www.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdoubleh.www.common.Action;
import org.mdoubleh.www.common.ActionForward;
import org.mdoubleh.www.common.LoginManager;
import org.mdoubleh.www.common.Parser;
import org.mdoubleh.www.common.RegExp;
import org.mdoubleh.www.service.HessService;
import org.mdoubleh.www.vo.ArticleVo;

import static org.mdoubleh.www.common.RegExp.ARTICLE_NUM;

import java.io.PrintWriter;

public class UpdateAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginManager lm = LoginManager.getInstance();
		String id = lm.getMemberId(request.getSession());
		if (id == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인이 필요한 서비스 입니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		
		String num = request.getParameter("num");
		if (num == null || num.equals("")
				|| !RegExp.checkString(ARTICLE_NUM, num)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		
		int buff = Integer.parseInt(num);
		if (buff <= 0) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		
		HessService service = new HessService();
		ArticleVo vo = service.getArticle(buff);
		if (vo == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		
		if (!vo.getId().equals(id)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		
		vo.setCn(Parser.chgToHTML(vo.getCn()));
		
		ActionForward forward = new ActionForward();
		request.setAttribute("vo", vo);
		forward.setPath("/views/updateForm.jsp");
		return forward;
	}
}
