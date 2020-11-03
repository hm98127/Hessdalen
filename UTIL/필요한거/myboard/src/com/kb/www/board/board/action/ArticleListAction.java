package com.kb.www.board.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kb.www.board.board.service.ArticleService;
import com.kb.www.board.board.vo.ArticleVo;
import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;

public class ArticleListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ArticleService svc = new ArticleService();
		ArrayList<ArticleVo> list = svc.getArticleList();
		
		request.setAttribute("list", list);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/article/list.jsp");
		return forward;
	}
}
