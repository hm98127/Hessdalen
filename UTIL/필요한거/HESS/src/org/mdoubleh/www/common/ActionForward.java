package org.mdoubleh.www.common;

//페이지 이동을 처리하기 위한 클래스
public class ActionForward {

	private String path;
	private boolean redirect;

	public ActionForward() {

	}

	/**
	 * Redirect 사용여부, false이면 Forward 사용
	 * 
	 * @return isRedirect
	 */
	public ActionForward(String path, boolean redirect) {
		this.path = path;
		this.redirect = redirect;
	}

	// get & set Attributes
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRedirect() {
		return redirect;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
}
