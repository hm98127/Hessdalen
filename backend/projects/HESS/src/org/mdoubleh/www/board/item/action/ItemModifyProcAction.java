package org.mdoubleh.www.board.item.action;

import static org.mdoubleh.www.common.RegExp.*;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdoubleh.www.board.item.service.BoardService;
import org.mdoubleh.www.board.item.vo.BoardVo;
import org.mdoubleh.www.common.Action;
import org.mdoubleh.www.common.ActionForward;
import org.mdoubleh.www.common.LoginManager;
import org.mdoubleh.www.common.Parser;
import org.mdoubleh.www.common.RegExp;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ItemModifyProcAction implements Action {
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
        
        String realFolder = "";

		String saveFolder = "/images";
		String encType = "UTF-8";
		int maxSize = 5 * 1024 * 1024;

		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);
		MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType,
				new DefaultFileRenamePolicy());
		String image = multi.getFilesystemName("image");
		String group = multi.getParameter("group");
		String title = multi.getParameter("title");
		String price = multi.getParameter("price");
		String content = multi.getParameter("content");
		if (group == null || group.equals("") || title == null || title.equals("")
				|| !RegExp.checkString(BOARD_TITLE, title) 
				|| price == null || price.equals("") || !RegExp.checkString(BOARD_NUM, price)
				|| content == null
				|| content.equals("") || !RegExp.checkString(BOARD_CONTENT, content)
				|| image == null || image.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/main.jsp';</script>");
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
		
		int buffPrice = Integer.parseInt(price);
		if (buffPrice <= 0) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다.');location.href='/main.jsp';</script>");
            out.close();
            return null;
		}
		
		int buffNum = Integer.parseInt(num);
		if (buffNum <= 0) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다.');location.href='/main.jsp';</script>");
            out.close();
            return null;
		}
		
		BoardService svc = new BoardService();
        String writerId = svc.getWriterId(buffNum);
        if (writerId == null || !id.equals(writerId)) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다.');location.href='/main.jsp';</script>");
            out.close();
            return null;
        }

		BoardVo vo = new BoardVo();
		vo.setItem_postnum(buffNum);
		vo.setItem_title(Parser.chgToStr(title));
		vo.setItem_price(buffPrice);
		vo.setItem_content(content);
		vo.setItem_group(group);
		vo.setItem_img(image);
        
        if (!svc.modifyBoard(vo)) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('글을 수정하는데 실패하였습니다.');location.href='/main.jsp';</script>");
            out.close();
            return null;
        }
        
        String pageNum = request.getParameter("pn");
        if (pageNum == null
                || !RegExp.checkString(IS_NUMBER, pageNum)) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다.');location.href='/main.jsp';</script>");
            out.close();
            return null;
        }

        int page = Integer.parseInt(pageNum);
        if (page < 1) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다.');location.href='/main.jsp';</script>");
            out.close();
            return null;
        }
        
        ActionForward forward = new ActionForward();
        forward.setPath("/itemDetail.do?pn=" + page + "&num=" + buffNum);
        forward.setRedirect(true);
        return forward;
	}

}
