<%@page import="com.kb.www.board.board.vo.ArticleVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ArrayList<ArticleVo> list = (ArrayList<ArticleVo>) request.getAttribute("list"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>작성일시</td>
			<td>조회수</td>
		</tr>
		<%if (list.size() > 0) {%>
		<%for (ArticleVo vo : list) {%>
		<tr>
			<td><%=vo.getSq() %></td>
			<td><%=vo.getSub() %></td>
			<td><%=vo.getWriter() %></td>
			<td><%=vo.getDt() %></td>
			<td><%=vo.getHit() %></td>
		</tr>
		<%} %>
		
		<%} else {%>
		<tr>
			<td colspan="5">등록된 글이 없습니다.</td>
		</tr>
		<%} %>
	</table>
	<button onclick="location.href='write.do'">글쓰기</button>
</body>
</html>


















