package org.mdoubleh.www.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdoubleh.www.common.Action;
import org.mdoubleh.www.common.ActionForward;
import org.mdoubleh.www.member.service.MemberService;

public class AjaxCheckIdAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		
		MemberService svc = new MemberService();
		request.setAttribute("count", svc.getMemberCount(id));
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/member/ajax/CheckId.jsp");
		return forward;
	}

}
