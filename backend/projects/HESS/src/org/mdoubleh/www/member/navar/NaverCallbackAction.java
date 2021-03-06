package org.mdoubleh.www.member.navar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.mdoubleh.www.common.Action;
import org.mdoubleh.www.common.ActionForward;
import org.mdoubleh.www.common.LoginManager;
import org.mdoubleh.www.member.service.MemberService;
import org.mdoubleh.www.member.vo.NaverVo;

public class NaverCallbackAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String clientId = "Pazq2RsPC1SWGaGjeEku"; // 애플리케이션 클라이언트 아이디값";
		String clientSecret = "_gxBi_FPRa"; // 애플리케이션 클라이언트 시크릿값";
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		String redirectURI = URLEncoder.encode("http://localhost:8080/naverCallback.do", "UTF-8");
		String apiURL;
		apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		apiURL += "client_id=" + clientId;
		apiURL += "&client_secret=" + clientSecret;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&code=" + code;
		apiURL += "&state=" + state;
		String access_token = "";
		String refresh_token = "";
//		System.out.println("apiURL=" + apiURL);
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			BufferedReader br;
//			System.out.print("responseCode=" + responseCode);
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			if (responseCode == 200) {
				System.out.println(res.toString());
				JSONParser parsing = new JSONParser();
				Object obj = parsing.parse(res.toString());
				JSONObject jsonObj = (JSONObject) obj;

				access_token = (String) jsonObj.get("access_token");
				refresh_token = (String) jsonObj.get("refresh_token");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		String header = "Bearer " + access_token; // Bearer 다음에 공백 추가
		
		try {
            apiURL = "https://openapi.naver.com/v1/nid/me";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", header);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));

            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response2 = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response2.append(inputLine);
            }
            JSONParser parsing = new JSONParser();
            JSONObject jsonObject = (JSONObject) parsing.parse(response2.toString());
            JSONObject dataObject = (JSONObject) jsonObject.get("response");

            String id = (String) dataObject.get("id");
            id += "@naver";

            br.close();

            NaverVo vo = new NaverVo();
            vo.setNaver_id(id);

            // 아이디 존재 유무 검사
            // 아이디가 존재하면 통과
            // 아이디가 없으면 저장하고 통과
            MemberService svc = new MemberService();
            int count = svc.getNaverCount(id);
           
            if (count == 0) {
            	svc.getNaverId(vo);
            }
            br.close();
            
            LoginManager lm = LoginManager.getInstance();
            lm.setSession(request.getSession(), vo.getNaver_id());
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		ActionForward forward = new ActionForward();
		forward.setPath("/main.jsp");
		return forward;
	}

}
