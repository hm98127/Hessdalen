package com.kb.www.board.member.action;

import static com.kb.www.common.RegExp.MEMBER_ID;
import static com.kb.www.common.RegExp.MEMBER_NAME;
import static com.kb.www.common.RegExp.MEMBER_PASSWORD;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kb.www.board.member.service.MemberService;
import com.kb.www.board.member.vo.MemberVo;
import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;
import com.kb.www.common.BCrypt;
import com.kb.www.common.LoginManager;
import com.kb.www.common.RegExp;

public class ModifyPwdProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
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
		String confirmPwd = request.getParameter("confirm_pwd");
		String mb_sq = request.getParameter("mb_sq");
		
		RegExp reg = new RegExp();
		
		if (pwd == null || pwd.equals("") || !reg.isValid(MEMBER_PASSWORD, pwd)
				|| confirmPwd == null || confirmPwd.equals("")
				|| mb_sq == null || mb_sq.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
            out.close();
            return null;
		}
		
		if (!pwd.equals(confirmPwd)) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
            out.close();
            return null;
		}
		
		MemberVo vo = new MemberVo();
		vo.setMb_sq(Integer.parseInt(mb_sq));
		vo.setPwd(BCrypt.hashpw(pwd, BCrypt.gensalt(12)));
		
		MemberService svc = new MemberService();
		if (!svc.updatePwd(vo)) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('비밀번호 변경에 실패하였습니다.');history.back();</script>");
            out.close();
            return null;
		}
		
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('비밀번호가 변경 되었습니다.');location.replace('login.do');</script>");
        out.close();
        return null;
	}
}





















