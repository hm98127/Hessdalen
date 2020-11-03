package org.mdoubleh.www.board.event.action;

import static org.mdoubleh.www.common.RegExp.IS_NUMBER;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdoubleh.www.board.event.vo.EvBoardVo;
import org.mdoubleh.www.board.notice.service.BoardService;
import org.mdoubleh.www.board.notice.vo.BoardVo;
import org.mdoubleh.www.common.Action;
import org.mdoubleh.www.common.ActionForward;
import org.mdoubleh.www.common.Paging;
import org.mdoubleh.www.common.RegExp;

public class ListEventBoardAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNum = request.getParameter("pn");
		if (pageNum == null || !RegExp.checkString(IS_NUMBER, pageNum)) {
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

		BoardService svc = new BoardService();
		Paging paging = new Paging(page, svc.getEventBoardCount());
		if (page > paging.getTotalPageCount()) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/listEventBoard.do?pn=" + paging.getTotalPageCount() + "';</script>");
			out.close();
			return null;
		}
		
		ArrayList<EvBoardVo> list = svc.getEventBoardList(paging);
		request.setAttribute("paging", paging);
		request.setAttribute("list", list);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/board/event/listEventBoardForm.jsp");
		return forward;
	}
	
}
