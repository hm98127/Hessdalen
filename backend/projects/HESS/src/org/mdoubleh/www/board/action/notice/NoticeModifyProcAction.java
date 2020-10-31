package org.mdoubleh.www.board.action.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdoubleh.www.board.service.BoardService;
import org.mdoubleh.www.board.vo.BoardVo;
import org.mdoubleh.www.common.Action;
import org.mdoubleh.www.common.ActionForward;
import org.mdoubleh.www.common.LoginManager;
import org.mdoubleh.www.common.Parser;
import org.mdoubleh.www.common.RegExp;

import static org.mdoubleh.www.common.RegExp.*;

import java.io.PrintWriter;

public class NoticeModifyProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginManager lm = LoginManager.getInstance();
		String id = lm.getMemberId(request.getSession());
		if (id == null) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그인이 필요한 서비스 입니다.');location.href='/login.do';</script>");
            out.close();
            return null;
		}
		
		String sj = request.getParameter("sj");
		String cn = request.getParameter("cn");
		if (sj == null || sj.equals("")
				|| !RegExp.checkString(BOARD_SUBJECT, sj)
				|| cn == null || cn.equals("")
				|| !RegExp.checkString(BOARD_CONTENT, cn)) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
            out.close();
            return null;
		}
		
		String num = request.getParameter("num");
		if (num == null || num.equals("")
				|| !RegExp.checkString(BOARD_NUM, num)) {
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
		
		BoardService svc = new BoardService();
		String writerId = svc.getWriterId(buff);
		if (writerId == null || !id.equals(writerId)) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
            out.close();
            return null;
		}
		
		BoardVo vo = new BoardVo();
		vo.setBd_sq(buff);
		vo.setSj(Parser.chgToStr(sj));
		vo.setCn(Parser.chgToStr(cn));
		
		if (!svc.modifyBoard(vo)) {
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
            out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
            out.close();
            return null;
		}
		
		int page = Integer.parseInt(pageNum);
		if (page < 1) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
            out.close();
            return null;
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/detail.do?pn=" + page + "&num=" + buff);
		forward.setRedirect(true);
		return forward;
	}

}
