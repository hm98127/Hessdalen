package com.kb.www.board.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kb.www.board.member.service.MemberService;
import com.kb.www.board.member.vo.MemberVo;
import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;
import com.kb.www.common.BCrypt;
import com.kb.www.common.LoginManager;

public class MemberLoginProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		if (id == null || id.equals("") || pwd == null || pwd.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
            out.close();
            return null;
		}
		
		MemberService svc = new MemberService();
		MemberVo vo = svc.getMemberInfo(id);
		if (vo == null || !BCrypt.checkpw(pwd, vo.getPwd())) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('아이디 또는 비밀번호를 확인해 주세요.');history.back();</script>");
            out.close();
            return null;
		}
		
		LoginManager lm = LoginManager.getInstance();
		lm.setSession(request.getSession(), id);
		
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		String callback = (String) session.getAttribute("callback"); 
		if (callback != null) {
			forward.setPath(callback);
			session.removeAttribute("callback");
		} else {
			forward.setPath("/");
		}
		
	
		forward.setRedirect(true);
		return forward;
	}
}















