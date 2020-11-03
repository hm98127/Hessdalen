<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			var name = $('#name');
			var id = $('#id');
			var pwd = $('#pwd');
			var confirm_pwd = $('#confirm_pwd');
			
			if (!name.val()) {
				alert("이름을 입력해 주세요.");
				name.focus();
				return;
			}
			
			if (!id.val()) {
				alert("아이디를 입력해 주세요.");
				id.focus();
				return;
			}
			
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
			
			var regExpName = new RegExp("^[가-힣a-z]{1,10}$", "g");
			console.log(name.val());
			if (regExpName.exec(name.val()) == null) {
				alert("이름은 1~10자의 한글 또는 영문 소문자로 입력해 주세요.");
				name.val('');
				name.focus();
				return;
			}
			
			var regExpId = new RegExp("^[a-z0-9]{4,20}$", "g");
			if (regExpId.exec(id.val()) == null) {
				alert("아이디는 2~20자의 영문 소문자, 숫자로 입력해 주세요.");
				id.val('');
				id.focus();
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
			
			$('#joinForm').submit();
		}
	</script>
</head>
<body>
	<form action="memberJoinProc.do" method="post" id="joinForm">
		<div>
			이름 <input type="text" name="nm" id="name" maxlength="10"/>
		</div>
		<div>
			아이디 <input type="text" name="id" id="id" maxlength="20"/>
		</div>
		<div>
			비밀번호 <input type="password" name="pwd" id="pwd" maxlength="20"/>
		</div>
		<div>
			비밀번호확인 <input type="password"
			 name="confirm_pwd" id="confirm_pwd" maxlength="10"/>
		</div>
		<button type="button" onclick="checkValidate()">가입</button>
		<button type="button">취소</button>
	</form>
</body>
</html>