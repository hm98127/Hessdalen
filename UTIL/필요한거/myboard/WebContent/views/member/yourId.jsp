<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String id = (String) request.getAttribute("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
당신의 아이디 : <%=id %>
<div>
	<button onclick="location.href='login.do'">로그인</button>
	<button onclick="location.href='searchPwd.do'">비밀번호찾기</button>
</div>
</body>
</html>