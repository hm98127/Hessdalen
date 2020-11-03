package com.kb.www.board.home.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kb.www.board.home.action.HomeAction;
import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/index.jsp")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String command = requestURI.substring(contextPath.length());
    	ActionForward forward = null;
    	Action action = new HomeAction();
    	
    	try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			forward = new ActionForward();
			forward.setPath("/views/error.jsp");
			forward.setRedirect(true);
		}
    	
    	if (forward.isRedirect()) {
    		// 리다이렉트
    		response.sendRedirect(forward.getPath());
    	} else {
    		// 디스패치
    		RequestDispatcher dispatcher
    		= request.getRequestDispatcher(forward.getPath());
    		dispatcher.forward(request, response);
    	}
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

}
