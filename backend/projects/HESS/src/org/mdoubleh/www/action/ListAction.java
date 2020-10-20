package org.mdoubleh.www.action;

import java.io.PrintWriter;
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
		String pageNum = request.getParameter("pn");
		if (pageNum == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/index.jsp';</script>");
			out.close();
			return null;
		}
		
		int page = Integer.parseInt(pageNum);
		if (page < 1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/index.jsp';</script>");
			out.close();
			return null;
		}
		
		String filter = request.getParameter("filter");
		String keyword = request.getParameter("keyword");
		String query = "";
		if (filter == null || filter.equals("")) {
			filter = "all";
		}
		
		if (keyword != null && !keyword.equals("")) {
			query = makeSearchQuery(filter, keyword);
		}
		
		HessService service = new HessService();
		Pa
		ArrayList<ArticleVo> list = service.getArticleList();
		
		ActionForward forward = new ActionForward();
		request.setAttribute("list", list);
		forward.setPath("/views/list.jsp");
		return forward;
	}
}
