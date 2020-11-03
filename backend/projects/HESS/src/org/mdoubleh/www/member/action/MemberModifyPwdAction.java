package org.mdoubleh.www.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdoubleh.www.common.Action;
import org.mdoubleh.www.common.ActionForward;
import org.mdoubleh.www.common.LoginManager;
import org.mdoubleh.www.member.service.MemberService;
import org.mdoubleh.www.member.vo.MemberVo;

public class MemberModifyPwdAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginManager lm = LoginManager.getInstance();
		String id = lm.getMemberId(request.getSession());
		if (id != null) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다.');location.href='/main.jsp';</script>");
            out.close();
            return null;
		}
		
		String name = request.getParameter("name");
		id = request.getParameter("id");
		if (name == null || name.equals("")
				|| id == null || id.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
            out.close();
            return null;
		}
		
		MemberVo vo = new MemberVo();
		vo.setMember_name(name);
		vo.setMember_id(id);
		
		MemberService svc = new MemberService();
		int num = svc.getMemberNumber(vo);
		if (num == 0) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('회원 정보를 찾을 수 없습니다.');history.back();</script>");
            out.close();
            return null;
		}
		
		request.setAttribute("num", num);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/member/memberModifyPwdForm.jsp");
		return forward;
	}

}
