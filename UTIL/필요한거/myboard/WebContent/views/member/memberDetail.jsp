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
</head>
<body>
<div>
	이름 : <%=vo.getNm() %>
</div>
<div>
	아이디 : <%=vo.getId() %>
</div>
<div>
	<button onclick="location.href='modifyMemberInfo.do'">회원정보수정</button>
	<button onclick="location.href='/'">홈으로</button>
</div>
</body>
</html>









