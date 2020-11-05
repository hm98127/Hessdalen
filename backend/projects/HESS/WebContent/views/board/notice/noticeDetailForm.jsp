<%@ page import="org.mdoubleh.www.common.LoginManager"%>
<%@ page import="org.mdoubleh.www.board.notice.vo.BoardVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%
	BoardVo vo = (BoardVo) request.getAttribute("vo");
	LoginManager lm = LoginManager.getInstance();
	String id = lm.getMemberId(session);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOARD DETAIL</title>
</head>
<body>
	<div>
		제목 :
		<%=vo.getNotice_title()%>
	</div>
	<div>
		내용 : <%= vo.getNotice_content() %>
		<img src="http://localhost:8080/upload/2020/11/05/95345bb0-e2e9-45fb-85b9-f6b024f139f3.png"/>
	</div>
	<button onclick="location.href='/noticeList.do'">뒤로가기</button>
	<%
		if (id != null && id.equals(vo.getMember_id())) {
	%>
	<button onclick="location.href='/noticeModify.do?num=<%=vo.getNotice_postnum()%>'">수정</button>
	<button onclick="location.href='/noticeDelete.do?num=<%=vo.getNotice_postnum()%>'">삭제</button>
	<%
		}
	%>
</body>
</html>