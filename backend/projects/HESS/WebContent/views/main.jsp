<%@ page import="org.mdoubleh.www.common.LoginManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	LoginManager lm = LoginManager.getInstance();
	String id = lm.getMemberId(session);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="/list.do?pn=1">공지사항게시판 이동</a>
<a href="/listEventBoard.do?pn=1">이벤트게시판 이동</a>
<a href="/itemList.do?pn=1">상품게시판 이동</a>
<%
	if (id == null) {
%>
	<a href="/join.do">회원가입</a>
	<a href="/login.do">로그인</a>
<%
	} else {
%>
	<a href="/modify.do">회원정보수정</a>
	<a href="/logout.do">로그아웃</a>
	<a href="/delete.do">회원탈퇴</a>
<%
	}
%>
</body>
</html>