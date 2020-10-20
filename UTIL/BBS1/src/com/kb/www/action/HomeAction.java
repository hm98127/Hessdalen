package com.kb.www.action;

import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeAction implements Action {
    @Override
    public ActionForward execute
            (HttpServletRequest request, HttpServletResponse response) {
        ActionForward forward = new ActionForward();
        forward.setPath("/views/index.jsp");
        return forward;
    }
}
