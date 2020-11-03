<%@ page import="org.mdoubleh.www.member.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	MemberVo vo = (MemberVo) request.getAttribute("vo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script 
	src="https://code.jquery.com/jquery-3.5.1.min.js"
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	crossorigin="anonymous">
</script>
<script>
	function validateCheck() {
		var pwd = $('#pwd').val();
		var pwd_confirm = $('#pwd_confirm').val();

		if (!pwd) {
			alert("비밀번호를 입력해 주세요.");
			$('#pwd').focus();
			return false;
		}
		
		if (!pwd_confirm) {
			alert("비밀번호확인을 입력해 주세요.");
			$('#pwd_confirm').focus();
			return false;
		}
		
		if (pwd != pwd_confirm) {
			 alert("비밀번호가 다릅니다.");
             $('#pwd').val("");
             $('#pwd_confirm').val("");
             $('#pwd').focus();
             return false;
		}
		
		var regExpPwd = new RegExp("^.{4,30}$", "g");
		if (regExpPwd.exec(pwd) == null) {
			alert("잘못된 비밀번호 형식입니다.");
            $('#pwd').val("");
            $('#pwd_confirm').val("");
            $('#pwd').focus();
            return false;
		}
	}
</script>
</head>
<body>
<h1>비밀번호를 입력하시면 회원탈퇴가 완료됩니다.</h1>
<form action="/deleteProc.do" method="post" onsubmit="return validateCheck()">
	이름 : <input type="text" name="nm" id="nm" minlength="1" maxlength="10" value="<%=vo.getNm()%>" readonly/>
	아이디 : <input type="text" name="id" id="id" minlength="4" maxlength="20" value="<%=vo.getId()%>" readonly/> 
	비밀번호 : <input	type="password" name="pwd" id="pwd" minlength="4" maxlength="30"/>
	비밀번호확인 : <input type="password" name="pwd_confirm" id="pwd_confirm" minlength="4" maxlength="30"/>
	<input type="submit" value="삭제"/>
	<button type="button" onclick="location.href='/'">취소</button>
</form>
</body>
</html>