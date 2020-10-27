<%@ page import="org.mdoubleh.www.common.Paging"%>
<%@ page import="org.mdoubleh.www.board.vo.BoardVo"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	ArrayList<BoardVo> list = (ArrayList<BoardVo>) request.getAttribute("list");
	Paging paging = (Paging) request.getAttribute("paging");
	String nowPage = request.getParameter("pn");
%>
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
	function goDetail(num) {
		location.href = "/detail.do?pn=" + <%=nowPage%> + "&num=" + num;
	}
</script>
</head>
<body>
<table>
	<tr>
		<td>번호</td>
		<td>제목</td>
		<td>조회수</td>
		<td>작성자</td>
		<td>작성일</td>
	</tr>
	<%
		if (list.size() > 0) {
	%>
		<%
			for (BoardVo vo : list) {
		%>
			<tr onclick="goDetail(<%=vo.getBd_sq()%>)">
				<td><%=vo.getBd_sq()%></td>
				<td><%=vo.getSj()%></td>
				<td><%=vo.getHit()%></td>
				<td><%=vo.getId()%></td>
				<td><%=vo.getDttm()%></td>
			</tr>
		<%
			}
		%>
	<%
		} else {
	%>
		<tr>
			<td colspan="5">등록된 글이 없습니다.</td>
		</tr>
	<%
		}
	%>
</table>
<span> 
	<a href="/list.do?pn=<%=paging.getStartPage() - 1%>"><</a>
</span>
<%
	for (int i = paging.getStartPage(); i <= paging.getEndPage(); i++) {
%>
	<span> 
	<a href="/list.do?pn=<%=i%>"><%=i%></a>
	</span>
<%
	}
%>
<span> 
	<a href="/list.do?pn=<%=paging.getEndPage() + 1%>">></a>
</span>
<div>
	<button onclick="location.href='/write.do'">글쓰기</button>
</div>
</body>
</html>