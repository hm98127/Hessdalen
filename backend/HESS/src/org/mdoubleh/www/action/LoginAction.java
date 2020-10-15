package org.mdoubleh.www.action;

import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdoubleh.www.common.Action;
import org.mdoubleh.www.common.ActionForward;

public class LoginAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String clientId = "Pazq2RsPC1SWGaGjeEku";// 애플리케이션 클라이언트 아이디값";
		String redirectURI = URLEncoder.encode("http://localhost:8080/callback.do", "UTF-8");
		SecureRandom random = new SecureRandom();
		String state = new BigInteger(130, random).toString();
		String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
		apiURL += "&client_id=" + clientId;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&state=" + state;
		request.setAttribute("apiURL", apiURL);

		ActionForward forward = new ActionForward();
		forward.setPath("/views/login.jsp");
		return forward;
	}
}