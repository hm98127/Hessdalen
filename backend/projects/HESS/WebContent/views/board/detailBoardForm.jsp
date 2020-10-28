<%@ page import="org.mdoubleh.www.board.vo.BoardVo"%>
<%@ page import="org.mdoubleh.www.common.LoginManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardVo vo = (BoardVo) request.getAttribute("vo");
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
제목 : <%=vo.getSj()%>
내용 : <%=vo.getCn()%>

<button onclick="location.href='/list.do?pn=<%=nowPage%>'">뒤로가기</button>
<%
	if (id != null && id.equals(vo.getId())) {
%>
	<button onclick="location.href='/modifyBoard.do?pn=<%=nowPage%>&num=<%=vo.getBd_sq()%>'">수정</button>
	<button onclick="location.href='/deleteNoticeBoard.do?pn=<%=nowPage%>&num=<%=vo.getBd_sq()%>'">삭제</button>
<%
	}
%>
</body>
</html>