<%@ page import="com.kb.www.vo.ArticleVo" %>
<%@ page import="com.kb.www.common.LoginManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArticleVo vo = (ArticleVo) request.getAttribute("vo");
    LoginManager lm = LoginManager.getInstance();
    String id = lm.getMemberId(session);
    String nowPage = request.getParameter("pn");
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>상세보기</title>
</head>
<body>
<div>제목 : <%=vo.getSubject()%></div>
<div>내용 : <%=vo.getContent()%></div>

<button onclick="location.href='/list.do?pn=<%=nowPage%>'">뒤로가기</button>
<%if (id != null && id.equals(vo.getId())) {%>
<button onclick="location.href='/update.do?pn=<%=nowPage%>&num=<%=vo.getNum()%>'">수정</button>
<button onclick="location.href='/delete.do?pn=<%=nowPage%>&num=<%=vo.getNum()%>'">삭제</button>
<%} %>
</body>
</html>
