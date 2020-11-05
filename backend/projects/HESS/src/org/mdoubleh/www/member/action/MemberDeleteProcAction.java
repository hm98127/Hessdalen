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

public class MemberDeleteProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginManager lm = LoginManager.getInstance();
		String id = lm.getMemberId(request.getSession());
		if (id == null) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다.');location.href='/main.jsp';</script>");
            out.close();
            return null;
		}
		
		String pwd = request.getParameter("pwd");
		String confirm_pwd = request.getParameter("confirm_pwd");
		
		if (id == null || id.equals("") || pwd == null || pwd.equals("")
				|| confirm_pwd == null || confirm_pwd.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다.');location.href='/main.jsp';</script>");
            out.close();
            return null;
		}
		
		if (!pwd.equals(confirm_pwd)) {
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
			out.println("<script>alert('잘못된 접근입니다.');location.href='/main.jsp';</script>");
			out.close();
			return null;
		}
		
		if (!svc.deleteMember(id)) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('회원 탈퇴에 실패 하였습니다.');history.back();</script>");
            out.close();
            return null;
		}
		
		lm.removeSession(id);
		
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('회원 탈퇴에 성공 하였습니다.');location.replace('/main.jsp');</script>");
        out.close();
        return null;
	}

}
