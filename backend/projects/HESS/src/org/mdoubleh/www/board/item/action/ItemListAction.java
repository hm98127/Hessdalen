package org.mdoubleh.www.board.item.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdoubleh.www.board.item.service.BoardService;
import org.mdoubleh.www.board.item.vo.BoardVo;
import org.mdoubleh.www.common.Action;
import org.mdoubleh.www.common.ActionForward;

public class ItemListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardService svc = new BoardService();
		ArrayList<BoardVo> list = svc.getBoardList();

		request.setAttribute("list", list);

		ActionForward forward = new ActionForward();
		forward.setPath("/views/board/item/itemListForm.jsp");
		return forward;
	}

}
