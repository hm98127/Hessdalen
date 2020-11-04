package org.mdoubleh.www.member.action;

import static org.mdoubleh.www.common.RegExp.MEMBER_PWD;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdoubleh.www.common.Action;
import org.mdoubleh.www.common.ActionForward;
import org.mdoubleh.www.common.BCrypt;
import org.mdoubleh.www.common.LoginManager;
import org.mdoubleh.www.common.RegExp;
import org.mdoubleh.www.member.service.MemberService;
import org.mdoubleh.www.member.vo.MemberVo;

public class GetYourPwdAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginManager lm = LoginManager.getInstance();
		String id = lm.getMemberId(request.getSession());
		if (id != null) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
            out.close();
            return null;
		}
		
		String pwd = request.getParameter("pwd");
		String confirm_pwd = request.getParameter("confirm_pwd");
		String num = request.getParameter("num");
		
		if (pwd == null || pwd.equals("") || !RegExp.checkString(MEMBER_PWD, pwd)
				|| confirm_pwd == null || confirm_pwd.equals("")
				|| num == null || num.equals("")) {
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
		
		MemberVo vo = new MemberVo();
		vo.setMember_postnum(Integer.parseInt(num));
		vo.setMember_pwd(BCrypt.hashpw(pwd, BCrypt.gensalt(12)));
		
		MemberService svc = new MemberService();
		if (!svc.getMemberPwd(vo)) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('비밀번호 변경에 실패하였습니다.');history.back();</script>");
            out.close();
            return null;
		}
		
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('비밀번호가 변경 되었습니다.');location.replace('/main.jsp');</script>");
        out.close();
        return null;
	}

}
