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
	var isChecked = false;
	function validateCheck() {
		var nm = $('#nm').val();
		var id = $('#id').val();
		var pwd = $('#pwd').val();
		var pwd_confirm = $('#pwd_confirm').val();

		if (!nm) {
			alert("이름을 입력해 주세요.");
			$('#nm').focus();
			return false;
		}

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

		var regExpNm = new RegExp("^[가-힣a-z]{1,10}$", "g");
		if (regExpNm.exec(nm) == null) {
			alert("잘못된 이름 형식입니다.");
			$('#nm').val("");
			$('#nm').focus();
			return false;
		}

		var regExpId = new RegExp("^[a-z0-9]{4,20}$", "g");
		if (regExpId.exec(id) == null) {
			alert("잘못된 아이디 형식입니다.");
			$('#id').val("");
			$('#id').focus();
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

		if (!isChecked) {
			alert("아이디 중복 검사를 해주세요.");
			return false;
		}
	}

	function checkId() {
		var id = $('#id').val();

		$.ajax({
			url : "/checkId.ajax",
			type : "post",
			data : {
				id : id
			},
			dataType : "json",
			error : function() {
				alert("통신 실패");
			},
			success : function(data) {
				if (data.id == "true") {
					// 중복된 아이디 없음
					isChecked = true;
					alert("사용할 수 있는 아이디 입니다.");
				} else {
					// 중복된 아이디 있음
					alert("사용할 수 없는 아이디 입니다.");
				}
			}
		});
	}

	function initCheckId() {
		isChecked = false;
	}
</script>
</head>
<body>
<form action="/joinProc.do" method="post"
	onsubmit="return validateCheck()">
	이름 : <input type="text" name="nm" id="nm" minlength="1" maxlength="10"/>
	아이디 : <input type="text" name="id" id="id" minlength="4"
		maxlength="20" oninput="initCheckId()"/>
	<button type="button" onclick="checkId()">중복확인</button>
	비밀번호 : <input type="password" name="pwd" id="pwd" minlength="4" maxlength="30"/> 
	비밀번호확인 : <input type="password" name="pwd_confirm" id="pwd_confirm" minlength="4" maxlength="30"/>
	<input type="submit" value="가입"/>
	<button type="button" onclick="location.href='/'">취소</button>
</form>
</body>
</html>