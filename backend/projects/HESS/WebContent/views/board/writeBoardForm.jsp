<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
function checkValidate() {
	var sj = $('#sj');
	var cn = $('#cn');
	
	if (!sj.val()) {
		alert("제목을 입력해 주세요.");
		sj.focus();
		return;
	}
	
	if (!cn.val()) {
		alert("내용를 입력해 주세요.");
		cn.focus();
		return;
	}
	
	var regExpSj = new RegExp("^.{1,50}$", "g");
	if (regExpSj.exec(sj.val()) == null) {
		alert("제목은 50자 이내로 입력해 주세요.");
		sj.val('');
		sj.focus();
		return;
	}
	
	$('#registerForm').submit();
}
</script>
</head>
<body>
<form action="/registerBoard.do" method="post" id="registerForm">
	제목 : <input type="text" name="sj" id="sj" maxlength="50"/>
	내용 : <textarea name="cn" id="cn" rows="10" cols="30"></textarea>
	<input type="button" value="등록" onclick="checkValidate()"/>
</form>
</body>
</html>