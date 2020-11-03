<%@page import="com.kb.www.board.member.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	MemberVo vo = (MemberVo) request.getAttribute("memberVo");
%>
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
		var name = $('#name');

		if (!name.val()) {
			alert("이름을 입력해 주세요.");
			name.focus();
			return;
		}

		var regExpName = new RegExp("^[가-힣a-z]{1,10}$", "g");
		if (regExpName.exec(name.val()) == null) {
			alert("이름은 1~10자의 한글 또는 영문 소문자로 입력해 주세요.");
			name.val('');
			name.focus();
			return;
		}

		$('#modifyForm').submit();
	}
</script>
</head>
<body>
	<form action="ModifyMemberInfoProc.do" method="post" id="modifyForm">
		<div>
			아이디 <%=vo.getId() %>
		</div>
		<div>
			이름 <input type="text" name="nm" id="name"
			 maxlength="10" value="<%=vo.getNm() %>" />
		</div>
		<button type="button" onclick="checkValidate()">수정</button>
		<button type="button">취소</button>
	</form>
</body>
</html>







