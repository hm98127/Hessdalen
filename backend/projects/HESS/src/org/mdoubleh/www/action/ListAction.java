package org.mdoubleh.www.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdoubleh.www.common.Action;
import org.mdoubleh.www.common.ActionForward;
import org.mdoubleh.www.common.Pagenation;
import org.mdoubleh.www.common.RegExp;
import org.mdoubleh.www.service.HessService;
import org.mdoubleh.www.vo.ArticleVo;

import static org.mdoubleh.www.common.RegExp.IS_NUMBER;

public class ListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		Pagenation pagenation = new Pagenation(page, service.getArticleCount(query));
		if (page > pagenation.getTotalArticleCount()) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/list.do?pn=" + pagenation.getTotalPageCount() +"';</script>");
			out.close();
			return null;
		}
		
		ArrayList<ArticleVo> list = service.getArticleList(pagenation, query);
		
		ActionForward forward = new ActionForward();
		request.setAttribute("pagenation", pagenation);
		request.setAttribute("list", list);
		forward.setPath("/views/list.jsp");
		return forward;
	}
	
	private String makeSearchQuery(String filter, String keyword) {
		String query = null;
		if (filter.equals("all")) {
			query = " AND (sj LIKE '%" + keyword
					+ "%' OR cn LIKE '%" + keyword + "%')";
		} else if (filter.equals("sj")) {
			query = " AND (sj LIKE '%" + keyword + "%')";
		} else {
			query = " AND (cn LIKE '%" + keyword + "%')";
		}
		
		return query;
	}
}
