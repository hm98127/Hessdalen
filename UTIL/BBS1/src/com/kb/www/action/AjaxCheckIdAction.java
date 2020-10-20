package com.kb.www.action;

import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;
import com.kb.www.common.LoginManager;
import com.kb.www.service.BoardService;
import com.kb.www.vo.MemberHistoryVo;
import com.kb.www.vo.MemberVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static com.kb.www.constants.Constants.MEMBER_HISTORY_EVENT_LEAVE;

public class AjaxCheckIdAction implements Action {
    @Override
    public ActionForward execute
            (HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("id");
        // id 정규 표현식으로 검사필요

        BoardService service = new BoardService();
        request.setAttribute("count", service.getMemberCount(id));

        ActionForward forward = new ActionForward();
        forward.setPath("/views/ajax/CheckId.jsp");
        return forward;
    }
}
