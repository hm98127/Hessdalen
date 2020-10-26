package org.mdoubleh.www.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mdoubleh.www.common.Action;
import org.mdoubleh.www.common.ActionForward;
import org.mdoubleh.www.common.LoginManager;
import org.mdoubleh.www.member.service.MemberService;
import org.mdoubleh.www.member.vo.MemberVo;

public class LogoutProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String id = lm.getMemberId(session);
		if (id != null) {
			lm.removeSession(id);
		}

		ActionForward forward = new ActionForward();
		forward.setPath("/");
		forward.setRedirect(true);
		return forward;
	}
	
	public void logoutProc(String id) {
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setLgn_fl(false);
		
		MemberService svc = new MemberService();
		if (!svc.logoutMember(vo)) {
			System.out.println(id + " 회원의 로그아웃 처리에 실패하였습니다.");
		}
	}

}
