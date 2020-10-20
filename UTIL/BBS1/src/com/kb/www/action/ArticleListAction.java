package com.kb.www.action;

import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;
import com.kb.www.common.Pagenation;
import com.kb.www.common.RegExp;
import com.kb.www.service.BoardService;
import com.kb.www.vo.ArticleVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;

import static com.kb.www.common.RegExp.ARTICLE_SUBJECT;
import static com.kb.www.common.RegExp.IS_NUMBER;

public class ArticleListAction implements Action {
    @Override
    public ActionForward execute
            (HttpServletRequest request, HttpServletResponse response)
            throws Exception {
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

        BoardService service = new BoardService();
        Pagenation pagenation = new Pagenation(page, service.getArticleCount(query));
        if (page > pagenation.getTotalPageCount()) {
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
            query = " and (subject like '%" + keyword
                    + "%' or content like '%" + keyword + "%')";
        } else if (filter.equals("subject")) {
            query = " and (subject like '%" + keyword + "%')";
        } else {
            query = " and (content like '%" + keyword + "%')";
        }

        return query;
    }
}
