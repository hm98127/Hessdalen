<%@ page import="org.mdoubleh.www.board.vo.EvBoardVo"%>
<%@ page import="org.mdoubleh.www.common.LoginManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	EvBoardVo vo = (EvBoardVo) request.getAttribute("vo");
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

<button onclick="location.href='/listEventBoard.do?pn=<%=nowPage%>'">뒤로가기</button>
<%
	if (id != null && id.equals(vo.getId())) {
%>
	<button onclick="location.href='/modifyEventBoard.do?pn=<%=nowPage%>&num=<%=vo.getEv_sq()%>'">수정</button>
	<button onclick="location.href='/deleteEventBoard.do?pn=<%=nowPage%>&num=<%=vo.getEv_sq()%>'">삭제</button>
<%
	}
%>
</body>
</html>