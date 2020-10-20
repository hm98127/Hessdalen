package com.kb.www.action;

import com.kb.www.common.*;
import com.kb.www.service.BoardService;
import com.kb.www.vo.MemberHistoryVo;
import com.kb.www.vo.MemberVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static com.kb.www.common.RegExp.MEMBER_ID;
import static com.kb.www.common.RegExp.MEMBER_PWD;
import static com.kb.www.constants.Constants.MEMBER_HISTORY_EVENT_JOIN;
import static com.kb.www.constants.Constants.MEMBER_HISTORY_EVENT_UPDATE;

public class MemberUpdateProcAction implements Action {
    @Override
    public ActionForward execute
            (HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LoginManager lm = LoginManager.getInstance();
        String id = lm.getMemberId(request.getSession());
        if(id == null) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그인이 필요한 서비스 입니다.');location.href='/login.do';</script>");
            out.close();
            return null;
        }

        String pwd = request.getParameter("pwd");
        String pwd_confirm = request.getParameter("pwd_confirm");
        if (pwd == null || pwd.equals("")
                || !RegExp.checkString(MEMBER_PWD, pwd)) {
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

        BoardService service = new BoardService();

        MemberVo memberVo = new MemberVo();
        memberVo.setId(id);
        memberVo.setPwd(BCrypt.hashpw(pwd, BCrypt.gensalt(12)));

        MemberHistoryVo memberHistoryVo = new MemberHistoryVo();
        memberHistoryVo.setMb_sq(service.getMemberSequence(id));
        memberHistoryVo.setEvt_type(MEMBER_HISTORY_EVENT_UPDATE);

        if (!service.updateMember(memberVo, memberHistoryVo)) {
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
