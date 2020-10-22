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

public class RegisterAction implements Action {
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
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		
		HessService service = new HessService();
		ArticleVo vo = new ArticleVo();
		vo.setSj(subject);
		vo.setCn(content);
		vo.setMb_sq(service.getMemberSequence(id));
		
		if (!service.insertArticle(vo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('글을 저장한는데 실패하였습니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/list.do?pn=1");
		forward.setRedirect(true);
		return forward;
	}
}
