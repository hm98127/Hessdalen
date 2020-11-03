package org.mdoubleh.www.common;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.mdoubleh.www.member.action.LogoutProcAction;

import java.util.Enumeration;
import java.util.Hashtable;

public class LoginManager implements HttpSessionBindingListener {
	// 로그인한 접속자를 담기위한 Hashtable
	private static Hashtable loginUsers = new Hashtable();

	private LoginManager() {
		super();
	}

	// Singleton Pattern
	public static LoginManager getInstance() {
		return LoginManager.LazyHolder.INSTANCE;
	}

	private static class LazyHolder {
		private static final LoginManager INSTANCE = new LoginManager();
	}

	/*
	 * 이 메소드는 세션이 연결될때 호출된다. (session.setAttribute("login", this))
	 * Hashtable에 세션과 접속자 아이디를 저장
	 */
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		loginUsers.put(event.getSession(), event.getName());
	}

	/*
	 * 이 메소드는 세션이 끊겻을때 호출된다. (invalidate)
	 * Hashtable에 저장된 로그인한 정보를 제거
	 */
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		Action action = new LogoutProcAction();
		((LogoutProcAction) action).logoutProc(getMemberId(event.getSession()));
		loginUsers.remove(event.getSession());
	}

	/**
	 * 입력받은 아이디를 Hashtable에서 삭제
	 * 
	 * @param id 사용자 아이디
	 * @return void
	 */
	public void removeSession(String id) {
		Enumeration e = loginUsers.keys();
		HttpSession session = null;
		while (e.hasMoreElements()) {
			session = (HttpSession) e.nextElement();
			if (loginUsers.get(session).equals(id)) {
				session.invalidate();
			}
		}
	}

	/**
	 * 해당 세션에서 이미 로그인을 했는지 안했는지를 체크
	 * 
	 * @param sessionId 사용자 아이디
	 * @return boolean
	 */
	public boolean isLogin(String sessionId) {
		boolean isLogin = false;
		Enumeration e = loginUsers.keys();
		String key = "";
		while (e.hasMoreElements()) {
			key = (String) e.nextElement();
			if (sessionId.equals(key)) {
				isLogin = true;
			}
		}
		return isLogin;
	}
	
	// 세션 생성
	public void setSession(HttpSession session, String id) {
		session.setAttribute(id, this);
	}

	// 세션 ID로 현재 로그인한 ID를 구분해 낸다.
	public String getMemberId(HttpSession session) {
		return (String) loginUsers.get(session);
	}
}
