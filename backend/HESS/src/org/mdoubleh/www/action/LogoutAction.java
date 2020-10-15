package org.mdoubleh.www.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mdoubleh.www.common.Action;
import org.mdoubleh.www.common.ActionForward;
import org.mdoubleh.www.common.LoginManager;
import org.mdoubleh.www.service.HessService;
import org.mdoubleh.www.vo.MemberVo;

public class LogoutAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String id = lm.getMemberId(session);
		if (id != null) {
			lm.removeSession(id);
		}
		ActionForward forward = new ActionForward();
		forward.setPath("/views/index.jsp");
		forward.setRedirect(true);
		return forward;
	}
	
	public void logoutProc(String id) {
		MemberVo memberVo = new MemberVo();
		memberVo.setId(id);
		memberVo.setDel_fl(false);
		
		HessService service = new HessService();
		if (!service.logoutMember(memberVo)) {
			System.out.println(id + " 회원의 로그아웃 처리에 실패하였습니다.");
		}
	}
	
}
