package org.mdoubleh.www.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdoubleh.www.common.Action;
import org.mdoubleh.www.common.ActionForward;
import org.mdoubleh.www.common.LoginManager;

public class JoinFormAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginManager lm = LoginManager.getInstance();
		ActionForward forward = new ActionForward();
		if (lm.getMemberId(request.getSession()) != null) {
			forward.setPath("/");
			forward.setRedirect(true);
		} else {
			forward.setPath("/views/member/joinForm.jsp");
		}
		return forward;
	}

}
