package com.kb.www.board.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kb.www.board.member.service.MemberService;
import com.kb.www.board.member.vo.MemberVo;
import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;
import com.kb.www.common.LoginManager;

public class SearchPwdProcAction implements Action {
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
		
		String name = request.getParameter("nm");
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
		vo.setNm(name);
		vo.setId(id);
		
		MemberService svc = new MemberService();
		int mb_sq = svc.getMemberSequence(vo);
		if (mb_sq == 0) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('회원 정보를 찾을 수 없습니다.');history.back();</script>");
            out.close();
            return null;
		}
		
		request.setAttribute("sq", mb_sq);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/member/modifyPwdForm.jsp");
		return forward;
	}
}














