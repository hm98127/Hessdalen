<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	crossorigin="anonymous"></script>
<script>
	function validateCheck() {
		var id = $('#id').val();
		var pwd = $('#pwd').val();

		if (!id) {
			alert("아이디를 입력해 주세요.");
			$('#id').focus();
			return false;
		}

		if (!pwd) {
			alert("비밀번호를 입력해 주세요.");
			$('#pwd').focus();
			return false;
		}
	}
</script>
</head>
<body>
<form action="/loginProc.do" method="post"
	onsubmit="return validateCheck()">
	아이디 : <input type="text" name="id" id="id"/>
	비밀번호 : <input type="password" name="pwd" id="pwd"/> 
	<input type="submit" value="로그인"/>
	<button type="button" onclick="location.href='/'">취소</button>
</form>
</body>
</html>