package com.kb.www.action;

import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;
import com.kb.www.common.LoginManager;
import com.kb.www.service.BoardService;
import com.kb.www.vo.ArticleVo;
import com.kb.www.vo.MemberHistoryVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MemberHistoryAction implements Action {
    @Override
    public ActionForward execute
            (HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LoginManager lm = LoginManager.getInstance();
        String id = lm.getMemberId(request.getSession());
        if(id == null) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그인이 필요한 서비스 입니다.');location.href='/login.do';</script>");
            out.close();
            return null;
        }

        BoardService service = new BoardService();
        ArrayList<MemberHistoryVo> list = service.getMemberHistory(id);

        ActionForward forward = new ActionForward();
        request.setAttribute("list", list);
        forward.setPath("/views/memberHistory.jsp");
        return forward;
    }
}
