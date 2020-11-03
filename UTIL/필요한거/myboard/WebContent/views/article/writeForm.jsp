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
			var sub = $('#sub');
			var cntnt = $('#cntnt');
			
			if (!sub.val()) {
				alert("제목을 입력해 주세요.");
				sub.focus();
				return;
			}
			
			if (!cntnt.val()) {
				alert("내용를 입력해 주세요.");
				cntnt.focus();
				return;
			}
			
			var regExpSub = new RegExp("^.{1,50}$", "g");
			if (regExpSub.exec(sub.val()) == null) {
				alert("제목은 50자 이내로 입력해 주세요.");
				sub.val('');
				sub.focus();
				return;
			}
			
			$('#registerForm').submit();
		}
	</script>
</head>
<body>
	<form action="registerArticle.do" method="post" id="registerForm">
		<div>
			제목 <input type="text" name="sub" id="sub" maxlength="50"/>
		</div>
		<div>
			내용
			<textarea name="cntnt" id="cntnt" cols="30" rows="10"></textarea>
		</div>
		<input type="button" value="등록" onclick="checkValidate()" />
	</form>
</body>
</html>