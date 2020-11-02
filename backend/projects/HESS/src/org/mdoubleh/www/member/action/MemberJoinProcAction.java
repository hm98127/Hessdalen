package org.mdoubleh.www.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdoubleh.www.common.Action;
import org.mdoubleh.www.common.ActionForward;
import org.mdoubleh.www.common.BCrypt;
import org.mdoubleh.www.common.LoginManager;
import org.mdoubleh.www.common.RegExp;
import org.mdoubleh.www.member.service.MemberService;
import org.mdoubleh.www.member.vo.MemberVo;

import static org.mdoubleh.www.common.RegExp.*;

import java.io.PrintWriter;

public class MemberJoinProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginManager lm = LoginManager.getInstance();
		if (lm.getMemberId(request.getSession()) != null) {
			ActionForward forward = new ActionForward();
			forward.setPath("/main.jsp");
			forward.setRedirect(true);
			return forward;
		}
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String pwd_confirm = request.getParameter("pwd_confirm");
		if (name == null || name.equals("") || !RegExp.checkString(MEMBER_NAME, name) 
				|| id == null || id.equals("") || !RegExp.checkString(MEMBER_ID, id) 
				|| pwd == null || pwd.equals("") || !RegExp.checkString(MEMBER_PWD, pwd)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/main.jsp';</script>");
			out.close();
			return null;
		}
		
		if (!pwd.equals(pwd_confirm)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/main.jsp';</script>");
			out.close();
			return null;
		}
		
		MemberVo vo = new MemberVo();
		vo.setMember_name(name);
		vo.setMember_id(id);
		vo.setMember_pwd(BCrypt.hashpw(pwd, BCrypt.gensalt(12)));
		
		MemberService svc = new MemberService();
		if (!svc.joinMember(vo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원 가입에 실패하였습니다.');location.href='/main.jsp';</script>");
			out.close();
			return null;
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/main.jsp");
		forward.setRedirect(true);
		return forward;
	}

}
