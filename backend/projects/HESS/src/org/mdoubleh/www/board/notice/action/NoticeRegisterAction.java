package org.mdoubleh.www.board.notice.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdoubleh.www.board.notice.service.BoardService;
import org.mdoubleh.www.board.notice.vo.BoardVo;
import org.mdoubleh.www.common.Action;
import org.mdoubleh.www.common.ActionForward;
import org.mdoubleh.www.common.LoginManager;
import org.mdoubleh.www.common.Parser;
import org.mdoubleh.www.common.RegExp;

import static org.mdoubleh.www.common.RegExp.*;

public class NoticeRegisterAction implements Action {
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
        
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        if (title == null || title.equals("")
                || !RegExp.checkString(BOARD_TITLE, title)
                || content == null || content.equals("")
                || !RegExp.checkString(BOARD_CONTENT, content)) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다.');location.href='/main.jsp';</script>");
            out.close();
            return null;
        }
        
        BoardService svc = new BoardService();
        BoardVo vo = new BoardVo();
        vo.setNotice_title(Parser.chgToStr(title));
        vo.setNotice_content(content);
        vo.setMember_id(id);

        if (!svc.registerBoard(vo)) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('글을 저장하는데 실패하였습니다.');location.href='/main.jsp';</script>");
            out.close();
            return null;
        }
        
        ActionForward forward = new ActionForward();
        forward.setPath("/noticeList.do");
        forward.setRedirect(true);
        return forward;
	}

}
