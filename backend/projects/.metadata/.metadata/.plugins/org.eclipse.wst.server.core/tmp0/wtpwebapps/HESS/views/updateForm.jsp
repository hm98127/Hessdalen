<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.mdoubleh.www.vo.ArticleVo"%>
<%
	ArticleVo vo = (ArticleVo) request.getAttribute("vo");
	String nowPage = request.getParameter("pn");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jQuery CDN -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.js"
	integrity="sha256-DrT5NfxfbHvMHux31Lkhxg42LY6of8TaYyK50jnxRnM="
	crossorigin="anonymous"></script>
<!-- jQuery Error handling -->
<script>
	function checkData() {
		var subject = $('#subject').val();
		if (!subject) {
			alert("제목을 입력하세요.");
			$('#subject').focus();
			return false;
		}
		var content = $('#content').val();
		if (!content) {
			alert("내용을 입력하세요.");
			$('#content').focus();
			return false;
		}
	}
</script>
</head>
<body>
<form action="/updateProc.do" method="post" onsubmit="return checkData()">
	제목 <input type="text" name="subject" id="subject" maxlength="100" value="<%= vo.getSj() %>"> 
	내용 <textarea name="content" id="content" cols="30" rows="10"><%= vo.getCn() %></textarea>
	<input type="hidden" value="<%= vo.getArticl_sq() %>" name="num"> 
	<input type="submit" value="수정">
</form>
</body>
</html>