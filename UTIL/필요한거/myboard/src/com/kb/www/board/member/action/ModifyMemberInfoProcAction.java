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
import com.kb.www.common.LoginManager;
import com.kb.www.common.RegExp;

public class ModifyMemberInfoProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginManager lm = LoginManager.getInstance();
		String id = lm.getMemberId(request.getSession());
		if (id == null) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
            out.close();
            return null;
		}
		
		String name = request.getParameter("nm");
		RegExp reg = new RegExp();
		
		if (name == null || name.equals("") || !reg.isValid(MEMBER_NAME, name)) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
            out.close();
            return null;
		}
		
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setNm(name);
		
		MemberService svc = new MemberService();
		if(!svc.modifyMemberInfo(vo)) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('회원 정보 수정에 실패하였습니다.');location.href='/';</script>");
            out.close();
            return null;
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/memberDetail.do");
		forward.setRedirect(true);
		return forward;
	}
}














