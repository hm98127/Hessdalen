package org.mdoubleh.www.board.action.notice;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mdoubleh.www.board.service.BoardService;
import org.mdoubleh.www.board.vo.BoardVo;
import org.mdoubleh.www.common.Action;
import org.mdoubleh.www.common.ActionForward;
import org.mdoubleh.www.common.LoginManager;
import org.mdoubleh.www.common.Parser;

public class NoticeRegisterAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

		String sj = request.getParameter("sj");
		String cn = request.getParameter("cn");
		if (sj == null || sj.equals("") || sj.length() > 50
				|| cn == null || cn.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		
		BoardVo vo = new BoardVo();
		vo.setId(id);
		vo.setSj(Parser.chgToStr(sj));
		vo.setCn(Parser.chgToStr(cn));

		BoardService svc = new BoardService();
		if (!svc.registerBoard(vo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('글 등록에 실패하였습니다.');history.back();</script>");
			out.close();
			return null;
		}

		ActionForward forward = new ActionForward();
		forward.setPath("/list.do?pn=1");
		forward.setRedirect(true);
		return forward;
	}

}
