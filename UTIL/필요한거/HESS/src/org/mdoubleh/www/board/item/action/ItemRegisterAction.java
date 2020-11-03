package org.mdoubleh.www.board.item.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mdoubleh.www.board.item.service.BoardService;
import org.mdoubleh.www.board.item.vo.ItemVo;
import org.mdoubleh.www.common.Action;
import org.mdoubleh.www.common.ActionForward;
import org.mdoubleh.www.common.LoginManager;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ItemRegisterAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginManager lm = LoginManager.getInstance();
		String id = lm.getMemberId(request.getSession());
		if (id == null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("callback", "/write.do");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인이 필요한 서비스 입니다.');location.href='login.do';</script>");
			out.close();
			return null;
		}
		
		
		// 파일 업로드될 서버 상의 물리적인 경로
		String realFolder = "";
		
		String saveFolder = "/imgs";
		String encType = "UTF-8";
		// 한 번에 업로드할 수 있는 파일의 크기
		int maxSize = 5 * 600 * 600;
		
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);
		MultipartRequest multi = new MultipartRequest
				(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
		String img = multi.getFilesystemName("img");
		//String img = multi.getOriginalFileName("img");
		ItemVo vo = new ItemVo();
		vo.setPrice(Integer.parseInt(multi.getParameter("price")));
		vo.setNm(multi.getParameter("nm"));
		vo.setCont(multi.getParameter("cont"));
		vo.setId(id);
		vo.setItem_img(img);
		
		BoardService svc = new BoardService();
		if (!svc.registerBoard(vo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('글 등록에 실패하였습니다.');history.back();</script>");
			out.close();
			return null;
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/itemList.do?pn=1");
		forward.setRedirect(true);
		return forward;
	}

}
