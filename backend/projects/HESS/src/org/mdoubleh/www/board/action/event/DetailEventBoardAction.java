package org.mdoubleh.www.board.action.event;

import static org.mdoubleh.www.common.RegExp.BOARD_NUM;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdoubleh.www.board.service.BoardService;
import org.mdoubleh.www.board.vo.BoardVo;
import org.mdoubleh.www.board.vo.EvBoardVo;
import org.mdoubleh.www.common.Action;
import org.mdoubleh.www.common.ActionForward;
import org.mdoubleh.www.common.Parser;
import org.mdoubleh.www.common.RegExp;

public class DetailEventBoardAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		EvBoardVo vo = svc.getEventBoard(buff);
		if (vo == null) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
            out.close();
            return null;
		}
		
		vo.setCn(Parser.chgToHTML(vo.getCn()));
		request.setAttribute("vo", vo);

		ActionForward forward = new ActionForward();
		forward.setPath("/views/board/event/detailEventBoardForm.jsp");
		return forward;
	}

}
