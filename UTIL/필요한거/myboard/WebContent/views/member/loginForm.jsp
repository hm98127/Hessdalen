<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"
            integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
            crossorigin="anonymous">
	</script>
         
<script>
	function checkValidate() {
		var id = $('#id');
		var pwd = $('#pwd');
		
		if (!id.val()) {
			alert('아이디를 입력해 주세요.');
			id.focus();
			return false;
		}
		
		if (!pwd.val()) {
			alert('비밀번호를 입력해 주세요.');
			pwd.focus();
			return false;
		}
	}
</script>
</head>
<body>
	<form action="loginProc.do" onsubmit="return checkValidate()">
		<div>
			아이디 <input type="text" name="id" id="id" />
		</div>
		<div>
			비밀번호 <input type="password" name="pwd" id="pwd" />
		</div>
		<div>
			<input type="submit" value="로그인" />
		</div>
		<div>
			<input type="button" onclick="location.href='searchId.do'" value="아이디찾기" />
			<input type="button" onclick="location.href='searchPwd.do'" value="비밀번호찾기" />
		</div>
	</form>
</body>
</html>





















