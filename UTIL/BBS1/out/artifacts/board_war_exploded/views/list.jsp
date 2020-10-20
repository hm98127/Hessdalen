<%@ page import="java.util.ArrayList" %>
<%@ page import="com.kb.www.vo.ArticleVo" %>
<%@ page import="com.kb.www.common.Pagenation" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<ArticleVo> list = (ArrayList<ArticleVo>) request.getAttribute("list");
    Pagenation pagenation = (Pagenation) request.getAttribute("pagenation");
    String nowPage = request.getParameter("pn");
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>목록</title>
    <script
            src="https://code.jquery.com/jquery-3.5.1.slim.js"
            integrity="sha256-DrT5NfxfbHvMHux31Lkhxg42LY6of8TaYyK50jnxRnM="
            crossorigin="anonymous"></script>
    <script>
        function goDetail(num) {
            location.href =
                "/detail.do?pn=" + <%=nowPage%> +"&num=" + num;
        }

        function searchArticle() {
            var filter = $('#filter option:selected').val();
            var keyword = $('#keyword').val();
            location.href =
                "/list.do?pn=1&filter=" + filter + "&keyword=" + keyword;
        }
    </script>
</head>
<body>

<select name="filter" id="filter">
    <option value="all" selected>전체</option>
    <option value="subject">제목</option>
    <option value="contents">내용</option>
</select>
<input type="text" name="keyword" id="keyword">
<button onclick="searchArticle()">검색</button>

<table>
    <tr>
        <td>번호</td>
        <td>제목</td>
        <td>조회수</td>
        <td>작성자</td>
        <td>작성일</td>
    </tr>
    <%for (int i = 0; i < list.size(); i++) {%>
    <tr onclick="goDetail(<%=list.get(i).getNum()%>)">
        <td><%=list.get(i).getNum()%>
        </td>
        <td><%=list.get(i).getSubject()%>
        </td>
        <td><%=list.get(i).getHit()%>
        </td>
        <td><%=list.get(i).getId()%>
        </td>
        <td><%=list.get(i).getWdate()%>
        </td>
    </tr>
    <%} %>
</table>
<span>
    <a href="/list.do?pn=<%=pagenation.getStartPage() - 1%>"><</a>
</span>
<%for (int i = pagenation.getStartPage(); i <= pagenation.getEndPage(); i++) {%>
<span>
    <a href="/list.do?pn=<%=i%>">
        <%=i%>
    </a>
</span>
<%}%>
<span>
    <a href="/list.do?pn=<%=pagenation.getEndPage() + 1%>">></a>
</span>


<button onclick="location.href='/write.do'">글쓰기</button>
</body>
</html>












