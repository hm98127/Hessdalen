package org.mdoubleh.www.comtroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdoubleh.www.board.action.NoticeDetailAction;
import org.mdoubleh.www.board.action.NoticeListAction;
import org.mdoubleh.www.board.action.NoticeModifyAction;
import org.mdoubleh.www.board.action.NoticeRegisterAction;
import org.mdoubleh.www.board.action.NoticeWriteAction;
import org.mdoubleh.www.common.Action;
import org.mdoubleh.www.common.ActionForward;
import org.mdoubleh.www.member.action.DeleteFormAction;
import org.mdoubleh.www.member.action.DeleteProcAction;
import org.mdoubleh.www.member.action.JoinFormAction;
import org.mdoubleh.www.member.action.JoinProcAction;
import org.mdoubleh.www.member.action.LoginFormAction;
import org.mdoubleh.www.member.action.LoginProcAction;
import org.mdoubleh.www.member.action.LogoutProcAction;
import org.mdoubleh.www.member.action.ModifyFormAction;
import org.mdoubleh.www.member.action.ModifyProcAction;

/**
 * 회원관련 Controller
 *
 */
@WebServlet("*.do")
public class BoardController extends HttpServlet {
	/**
	 * 명령어에 따른 해당 Action을 지정해 준다.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 넘어온 커맨드를 추출하는 과정
		request.setCharacterEncoding("UTF-8");
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		// URI, command 확인
		// System.out.println("requestURI : " + requestURI);
		// System.out.println("contextPath : " + command);
		// System.out.println("command : " + command);
		ActionForward forward = null;
		Action action = null;
		// 화면 전환
		if (command.equals("/join.do")) { // 회원가입화면 이동
			action = new JoinFormAction();
		} else if (command.equals("/joinProc.do")) {
			action = new JoinProcAction();
		} else if (command.equals("/login.do")) { // 로그인화면 이동
			action = new LoginFormAction();
		} else if (command.equals("/loginProc.do")) { // 로그인화면 이동
			action = new LoginProcAction();
		} else if (command.equals("/logout.do")) { // 로그인화면 이동
			action = new LogoutProcAction();
		} else if (command.equals("/modify.do")) { // 회원정보수정화면 이동
			action = new ModifyFormAction();
		} else if (command.equals("/modifyProc.do")) {
			action = new ModifyProcAction();
		} else if (command.equals("/delete.do")) {
			action = new DeleteFormAction();
		} else if (command.equals("/deleteProc.do")) {
			action = new DeleteProcAction();
		} else if (command.equals("/list.do")) {
			action = new NoticeListAction();
		} else if (command.equals("/write.do")) {
			action = new NoticeWriteAction();
		} else if (command.equals("/registerBoard.do")) {
			action = new NoticeRegisterAction();
		} else if (command.equals("/detail.do")) {
			action = new NoticeDetailAction();
		} else if (command.equals("/modifyBoard.do")) {
			action = new NoticeModifyAction();
		} else if (command.equals("/modifyBoardProc.do")) {
			action = new NoticeModifyProcAction();
		} else if (command.equals("/deleteBoard.do")) {
			action = new NoticeDeleteAction();
		} 
		
		if (action != null) {
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				forward = new ActionForward();
				forward.setPath("/views/error.jsp");
				forward.setRedirect(true);
			}
			// 화면이동 - isRedirect() 값에 따라 sendRedirect 또는 forward를 사용
	        // sendRedirect : 새로운 페이지에서는 request와 response객체가 새롭게 생성된다.
	        // forward : 현재 실행중인 페이지와 forward에 의해 호출될 페이지는 request와 response 객체를 공유
			if (forward != null) {
				if (forward.isRedirect()) {
					response.sendRedirect(forward.getPath());
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
					dispatcher.forward(request, response);
				}
			}
		}
	}

	/**
	 * GET 방식일 경우 doGet()
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * POST 방식일 경우 doPost()
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
