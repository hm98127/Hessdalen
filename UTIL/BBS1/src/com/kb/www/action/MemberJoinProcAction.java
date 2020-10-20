package com.kb.www.action;

import com.kb.www.common.*;
import com.kb.www.service.BoardService;
import com.kb.www.vo.ArticleVo;
import com.kb.www.vo.MemberHistoryVo;
import com.kb.www.vo.MemberVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static com.kb.www.common.RegExp.*;
import static com.kb.www.constants.Constants.MEMBER_HISTORY_EVENT_JOIN;

public class MemberJoinProcAction implements Action {
    @Override
    public ActionForward execute
            (HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LoginManager lm = LoginManager.getInstance();
        if(lm.getMemberId(request.getSession()) != null) {
            ActionForward forward = new ActionForward();
            forward.setPath("/");
            forward.setRedirect(true);
            return forward;
        }
        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");
        String pwd_confirm = request.getParameter("pwd_confirm");
        if (id == null || id.equals("")
                || !RegExp.checkString(MEMBER_ID, id)
                || pwd == null || pwd.equals("")
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

        MemberVo memberVo = new MemberVo();
        memberVo.setId(id);
        memberVo.setPwd(BCrypt.hashpw(pwd, BCrypt.gensalt(12)));

        MemberHistoryVo memberHistoryVo = new MemberHistoryVo();
        memberHistoryVo.setEvt_type(MEMBER_HISTORY_EVENT_JOIN);

        BoardService service = new BoardService();
        if (!service.joinMember(memberVo, memberHistoryVo)) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('회원 가입에 실패하였습니다.');location.href='/';</script>");
            out.close();
            return null;
        }

        ActionForward forward = new ActionForward();
        forward.setPath("/");
        forward.setRedirect(true);
        return forward;
    }
}
