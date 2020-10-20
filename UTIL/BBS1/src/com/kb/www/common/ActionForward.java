package com.kb.www.common;

public class ActionForward {
	// define Attributes
	private String path; // 서블릿에서 요청 처리 후 포워딩 될 최종 뷰 페이지의 url 저장
	private boolean redirect; // 포워딩 방식(true : redirect, false : dispatch)

	// construct
	public ActionForward() {
		// TODO Auto-generated constructor stub
	}
	
	public ActionForward(String path, boolean redirect) {
		this.path = path;
		this.redirect = redirect;
	}

	// get & set Attributes
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRedirect() {
		return this.redirect;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
}
