package org.mdoubleh.www.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdoubleh.www.common.Action;
import org.mdoubleh.www.common.ActionForward;
import org.mdoubleh.www.common.LoginManager;
import org.mdoubleh.www.common.RegExp;
import org.mdoubleh.www.service.HessService;
import org.mdoubleh.www.vo.ArticleVo;

import static org.mdoubleh.www.common.RegExp.*;

import java.io.PrintWriter;

public class UpdateProcAction implements Action {
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
		
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		if (subject == null || subject.equals("")
				|| !RegExp.checkString(ARTICLE_SUBJECT, subject)
				|| content == null || content.equals("")
				|| !RegExp.checkString(ARTICLE_CONTENT, content)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.1');location.href='/';</script>");
			out.close();
			return null;
		}
		
		String num = request.getParameter("num");
		if (num == null || num.equals("") || !RegExp.checkString(ARTICLE_NUM, num)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.2');location.href='/';</script>");
			out.close();
			return null;
		}
		
		int buff = Integer.parseInt(num);
		if (buff <= 0) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.3');location.href='/';</script>");
			out.close();
			return null;
		}
		
		HessService service = new HessService();
		String writerId = service.getWriterId(buff);
		if (writerId == null || !id.equals(writerId)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.4');location.href='/';</script>");
			out.close();
			return null;
		}
		
		ArticleVo vo = new ArticleVo();
		vo.setArticl_sq(buff);
		vo.setSj(subject);
		vo.setCn(content);
		
		if (!service.updateArticle(vo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('글을 수정하는데 실패하였습니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		
		String pageNum = request.getParameter("pn");
		if (pageNum == null
				|| !RegExp.checkString(IS_NUMBER, pageNum)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.5');location.href='/';</script>");
			out.close();
			return null;
		}
		
		int page = Integer.parseInt(pageNum);
		if (page < 1) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.6');location.href='/';</script>");
			out.close();
			return null;
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/detail.do?pn=" + page +"&num=" + buff);
		forward.setRedirect(true);
		return forward;
	}
}
