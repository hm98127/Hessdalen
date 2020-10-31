<%@page import="org.mdoubleh.www.board.item.vo.ItemVo"%>
<%@ page import="org.mdoubleh.www.common.Paging"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	ArrayList<ItemVo> list = (ArrayList<ItemVo>) request.getAttribute("list");
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
		location.href = "/itemDetail.do?pn=" + <%=nowPage%> + "&num=" + num;
	}
</script>
<style>
	#lmg {
		width: 150px;
		height: 150px;
		border:none;
	}
</style>
</head>
<body>
<%
	if (list.size() > 0) {
%>
<table>
	
		<%
			for (ItemVo vo : list) {
		%>
			<tr>
				<td onclick="goDetail(<%=vo.getItem_sq()%>)">
					<img src="images/a.png" id="lmg"/>
					상품명 : <%=vo.getCont()%><br>
					가격 : <%=vo.getPrice()%><br>
				</td>
			</tr>
		<%
			}
		%>
	
</table>
<%
	} else {
%>
	<h1>등록된 글이 없습니다.</h1>
<%
	}
%>
<span> 
	<a href="/itemList.do?pn=<%=paging.getStartPage() - 1%>"><</a>
</span>
<%
	for (int i = paging.getStartPage(); i <= paging.getEndPage(); i++) {
%>
	<span> 
	<a href="/itemList.do?pn=<%=i%>"><%=i%></a>
	</span>
<%
	}
%>
<span> 
	<a href="/itemList.do?pn=<%=paging.getEndPage() + 1%>">></a>
</span>
<div>
	<button onclick="location.href='/itemWrite.do'">상품 등록</button>
	<button onclick="location.href='/'">메인</button>
</div>
</body>
</html>