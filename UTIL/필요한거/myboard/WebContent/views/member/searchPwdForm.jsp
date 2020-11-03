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
	function validateData() {
		if (!$('#name').val()) {
			alert("이름을 입력해 주세요.");
			$('#name').focus();
			return;
		}
		
		if (!$('#id').val()) {
			alert("아이디를 입력해 주세요.");
			$('#id').focus();
			return;
		}
		
		$('#searchForm').submit();
	}
</script>
</head>
<body>
	<form action="modifyPwd.do" method="post" id="searchForm">
		이름 <input type="text" name="nm" id="name"/>
		아이디 <input type="text" name="id" id="id"/>
		<input type="button" value="찾기"
			onclick="validateData()" />
	</form>
</body>
</html>















