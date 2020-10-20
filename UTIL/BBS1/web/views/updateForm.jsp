<%@ page import="com.kb.www.vo.ArticleVo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArticleVo vo = (ArticleVo) request.getAttribute("vo");
    String nowPage = request.getParameter("pn");
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>글쓰기</title>
    <script
            src="https://code.jquery.com/jquery-3.5.1.slim.js"
            integrity="sha256-DrT5NfxfbHvMHux31Lkhxg42LY6of8TaYyK50jnxRnM="
            crossorigin="anonymous"></script>
    <script>

        var content = '<%=vo.getContent()%>';

        function checkData() {
            var subject = $('#subject').val();
            if (!subject) {
                alert("제목을 입력하세요.");
                $('#subject').focus();
                return false;
            }
            // var content = $('#content').val();
            // if (!content) {
            //     alert("내용을 입력하세요.");
            //     $('#content').focus();
            //     return false;
            // }
            saveContent();
        }
    </script>
</head>
<body>
<form id="editorForm" action="/updateProc.do?pn=<%=nowPage%>&num=<%=vo.getNum()%>" method="post">
    제목 <input type="text" name="subject" id="subject" maxlength="100"
value="<%=vo.getSubject()%>">
    내용 <jsp:include page="/editor/editorSkinForModify.jsp" flush="false"/>
    <input type="button" onclick="checkData()" value="수정">
</form>
</body>
</html>
