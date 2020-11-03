package org.mdoubleh.www.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdoubleh.www.common.Action;
import org.mdoubleh.www.common.ActionForward;
import org.mdoubleh.www.common.BCrypt;
import org.mdoubleh.www.common.LoginManager;
import org.mdoubleh.www.member.service.MemberService;
import org.mdoubleh.www.member.vo.MemberVo;

public class MemberLoginProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		if (id == null || id.equals("") || pwd == null || pwd.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/main.jsp';</script>");
			out.close();
			return null;
		}
		
		MemberService svc = new MemberService();
		MemberVo vo = svc.getMember(id);
		if (vo == null || !BCrypt.checkpw(pwd, vo.getMember_pwd())) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 정보를 확인해 주세요.');location.href='/main.jsp';</script>");
			out.close();
			return null;
		}
		
		vo.setCheck_login(true);
		if (!svc.getLogin(vo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 처리에 실패하였습니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		
		LoginManager lm = LoginManager.getInstance();
		lm.setSession(request.getSession(), vo.getMember_id());

		ActionForward forward = new ActionForward();
		forward.setPath("/main.jsp");
		forward.setRedirect(true);
		return forward;
	}

}
