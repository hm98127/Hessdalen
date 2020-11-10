package org.mdoubleh.www.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdoubleh.www.common.Action;
import org.mdoubleh.www.common.ActionForward;
import org.mdoubleh.www.common.LoginManager;
import org.mdoubleh.www.member.service.MemberService;
import org.mdoubleh.www.member.vo.MemberVo;
import org.mdoubleh.www.member.vo.NaverVo;

public class MemberLogoutAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginManager lm = LoginManager.getInstance();
		String id = lm.getMemberId(request.getSession());
		if (id != null) {
			lm.removeSession(id);
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/main.jsp");
		forward.setRedirect(true);
		return forward;
	}
	
	public void memberLogoutProc(String id) {
		NaverVo naverVo = new NaverVo();
		naverVo.setNaver_id(id);
		
		MemberVo vo = new MemberVo();
		vo.setMember_id(id);
		
		
		MemberService svc = new MemberService();
		if (!svc.getLogout(vo)) {
			System.out.println(id + " 회원의 로그아웃 처리에 실패하였습니다.");
		}
		
		if (!svc.getNaverLogout(naverVo)) {
			System.out.println(id + " 회원의 로그아웃 처리에 실패하였습니다.");
		}
	}

}
