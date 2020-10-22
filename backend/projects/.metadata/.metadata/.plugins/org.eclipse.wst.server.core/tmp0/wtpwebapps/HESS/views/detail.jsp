<%@page import="org.mdoubleh.www.common.LoginManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.mdoubleh.www.vo.ArticleVo" %>
<%
	ArticleVo vo = (ArticleVo) request.getAttribute("vo");
	LoginManager lm = LoginManager.getInstance();
	String id = lm.getMemberId(session);
	String nowPage = request.getParameter("pn");
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

<button onclick="location.href='/list.do?pn=<%= nowPage %>'">뒤로가기</button>
<% 
	if (id != null && id.equals(vo.getId())) { 
%>
<button onclick="location.href='/update.do?pn=<%= nowPage %>&num=<%= vo.getArticl_sq() %>'">수정</button>
<button onclick="location.href='/delete.do?pn=<%= nowPage %>&num=<%= vo.getArticl_sq() %>'">삭제</button>
<%
	}
%>
</body>
</html>