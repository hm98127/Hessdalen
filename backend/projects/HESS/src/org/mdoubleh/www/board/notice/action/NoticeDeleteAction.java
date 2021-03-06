package org.mdoubleh.www.board.notice.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdoubleh.www.board.notice.service.BoardService;
import org.mdoubleh.www.common.Action;
import org.mdoubleh.www.common.ActionForward;
import org.mdoubleh.www.common.LoginManager;
import org.mdoubleh.www.common.RegExp;

import static org.mdoubleh.www.common.RegExp.*;

public class NoticeDeleteAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginManager lm = LoginManager.getInstance();
        String id = lm.getMemberId(request.getSession());
        if(id == null) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그인이 필요한 서비스 입니다.');location.href='/memberLogin.do';</script>");
            out.close();
            return null;
        }
        
        String num = request.getParameter("num");
        if (num == null || num.equals("")
                || !RegExp.checkString(BOARD_NUM, num)) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다.');location.href='/main.jsp';</script>");
            out.close();
            return null;
        }
        
        int buff = Integer.parseInt(num);
        if (buff <= 0) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다.');location.href='/main.jsp';</script>");
            out.close();
            return null;
        }
        
        BoardService svc = new BoardService();
        String writerId = svc.getWriterId(buff);
        if (writerId == null || !id.equals(writerId)) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다.');location.href='/main.jsp';</script>");
            out.close();
            return null;
        }
        
        if (!svc.deleteBoard(buff)) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('글을 삭제하는데 실패하였습니다.');location.href='/main.jsp';</script>");
            out.close();
            return null;
        }
        
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('글 삭제가 되었습니다.');location.replace('/noticeList.do');</script>");
        out.close();
        return null;
	}

}
