<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	int sq = (int) request.getAttribute("sq");	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"
            integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
            crossorigin="anonymous">
	</script>
	<script>
		function checkValidate() {
			var pwd = $('#pwd');
			var confirm_pwd = $('#confirm_pwd');
			
			if (!pwd.val()) {
				alert("비밀번호를 입력해 주세요.");
				pwd.focus();
				return;
			}
			
			if (!confirm_pwd.val()) {
				alert("비밀번호 확인을 입력해 주세요.");
				confirm_pwd.focus();
				return;
			}
			
			if (pwd.val() != confirm_pwd.val()) {
				alert("비밀번호가 다릅니다.");
				pwd.val('');
				confirm_pwd.val('');
				pwd.focus();
				return;
			}
			
			var regExpPwd = new RegExp("^.{4,20}$", "g");
			if (regExpPwd.exec(pwd.val()) == null) {
				alert("비밀번호는 4~20자로 입력해 주세요.");
				pwd.val('');
				confirm_pwd.val('');
				pwd.focus();
				return;
			}
			
			$('#modifyForm').submit();
		}
	</script>
</head>
<body>
	<form action="modifyPwdProc.do" method="post" id="modifyForm">
		<div>
			비밀번호 <input type="password" name="pwd" id="pwd" maxlength="20"/>
		</div>
		<div>
			비밀번호확인 <input type="password"
			 name="confirm_pwd" id="confirm_pwd" maxlength="10"/>
		</div>
		<button type="button" onclick="checkValidate()">수정</button>
		<button type="button">취소</button>
		<input type="hidden" value="<%=sq %>" name="mb_sq"/>
	</form>
</body>
</html>









