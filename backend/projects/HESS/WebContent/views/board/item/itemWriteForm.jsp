<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	var nm = $('#nm');
	var cont = $('#cont');
	var price = $('#price');
	
	if (!nm.val()) {
		alert("상품 이름을 입력해 주세요.");
		nm.focus();
		return;
	}
	
	if (!cont.val()) {
		alert("상품 내용를 입력해 주세요.");
		cont.focus();
		return;
	}
	
	if (!price.val()) {
		alert("상품 가격를 입력해 주세요.");
		price.focus();
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
<h1>상품 등록</h1>
<form action="/itemRegister.do" method="post" id="registerForm" enctype="multipart/form-data">
	상품 이름 : <input type="text" name="nm" id="nm"/>
	상품 가격 : <input type="text" name="price" id="price"/>
	상품 내용 : <textarea name="cont" id="cont" rows="10" cols="30"></textarea>
	상품 이미지 : <input type="file" name="img" id="img"/>
	<input type="button" value="등록" onclick="checkValidate()"/>
	<button onclick="location.href='/'">메인</button>
</form>
</body>
</html>