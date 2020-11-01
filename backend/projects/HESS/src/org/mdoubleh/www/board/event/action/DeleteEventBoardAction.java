package org.mdoubleh.www.board.event.action;

import static org.mdoubleh.www.common.RegExp.BOARD_NUM;
import static org.mdoubleh.www.common.RegExp.IS_NUMBER;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdoubleh.www.board.notice.service.BoardService;
import org.mdoubleh.www.common.Action;
import org.mdoubleh.www.common.ActionForward;
import org.mdoubleh.www.common.LoginManager;
import org.mdoubleh.www.common.RegExp;

public class DeleteEventBoardAction implements Action {
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
		String writerId = svc.getWriterEventId(buff);
		if (writerId == null || !id.equals(writerId)) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
            out.close();
            return null;
		}
		
		if (!svc.deleteEventBoard(buff)) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('글을 삭제하는데 실패하였습니다.');location.href='/';</script>");
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
		forward.setPath("/listEventBoard.do?pn=" + page);
		forward.setRedirect(true);
		return forward;
	}

}
