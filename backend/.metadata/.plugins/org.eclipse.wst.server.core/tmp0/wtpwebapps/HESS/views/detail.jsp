<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.mdoubleh.www.vo.ArticleVo" %>
<%
	ArticleVo vo = (ArticleVo) request.getAttribute("vo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>제목 : <%= vo.getSj() %></div>
<div>내용 : <%= vo.getCn() %></div>

<button onclick="location.href='/list.do'">뒤로가기</button>
<button onclick="location.href='/update.do?num=<%= vo.getArticl_sq() %>'">수정</button>
<button onclick="location.href='/delete.do?num=<%= vo.getArticl_sq() %>'">삭제</button>
</body>
</html>