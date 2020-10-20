package com.kb.www.action;

import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberLoginFormAction implements Action {
    @Override
    public ActionForward execute
            (HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward forward = new ActionForward();
        forward.setPath("/views/loginForm.jsp");
        return forward;
    }
}
