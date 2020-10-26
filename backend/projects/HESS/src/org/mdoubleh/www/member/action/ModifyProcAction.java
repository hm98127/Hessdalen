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

public class ModifyProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginManager lm = LoginManager.getInstance();
		String nm = request.getParameter("nm");
		String id = lm.getMemberId(request.getSession());
		if (nm == null || id == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인이 필요한 서비스 입니다.');location.href='/login.do';</script>");
			out.close();
			return null;
		}

		String pwd = request.getParameter("pwd");
		String pwd_confirm = request.getParameter("pwd_confirm");
		if (pwd == null || pwd.equals("") || !RegExp.checkString(MEMBER_PWD, pwd)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}

		if (!pwd.equals(pwd_confirm)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}

		MemberService svc = new MemberService();
		MemberVo memberVo = new MemberVo();
		memberVo.setNm(nm);
		memberVo.setId(id);
		memberVo.setPwd(BCrypt.hashpw(pwd, BCrypt.gensalt(12)));

		if (!svc.modifyMember(memberVo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원 정보 수정에 실패하였습니다.');location.href='/';</script>");
			out.close();
			return null;
		}

		ActionForward forward = new ActionForward();
		forward.setPath("/");
		forward.setRedirect(true);
		return forward;
	}

}
