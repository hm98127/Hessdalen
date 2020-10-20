package com.kb.www.action;

import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;
import com.kb.www.common.LoginManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class MemberJoinFormAction implements Action {
    @Override
    public ActionForward execute
            (HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LoginManager lm = LoginManager.getInstance();
        ActionForward forward = new ActionForward();
        if(lm.getMemberId(request.getSession()) != null) {
            forward.setPath("/");
            forward.setRedirect(true);
        } else {
            forward.setPath("/views/joinForm.jsp");
        }
        return forward;
    }
}
