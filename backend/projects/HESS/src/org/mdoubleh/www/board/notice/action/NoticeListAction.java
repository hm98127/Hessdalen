package org.mdoubleh.www.board.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdoubleh.www.board.notice.service.BoardService;
import org.mdoubleh.www.board.notice.vo.BoardVo;
import org.mdoubleh.www.common.Action;
import org.mdoubleh.www.common.ActionForward;

import java.util.ArrayList;

public class NoticeListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardService svc = new BoardService();
		ArrayList<BoardVo> list = svc.getBoardList();

		request.setAttribute("list", list);

		ActionForward forward = new ActionForward();
		forward.setPath("/views/board/notice/noticeListForm.jsp");
		return forward;
	}

}
