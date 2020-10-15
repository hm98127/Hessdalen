package org.mdoubleh.www.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdoubleh.www.common.Action;
import org.mdoubleh.www.common.ActionForward;
import org.mdoubleh.www.service.HessService;
import org.mdoubleh.www.vo.ArticleVo;

public class ListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HessService service = new HessService();
		ArrayList<ArticleVo> list = service.getArticleList();
		
		ActionForward forward = new ActionForward();
		request.setAttribute("list", list);
		forward.setPath("/views/list.jsp");
		return forward;
	}
}