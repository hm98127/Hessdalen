<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>글쓰기</title>
    <script
            src="https://code.jquery.com/jquery-3.5.1.slim.js"
            integrity="sha256-DrT5NfxfbHvMHux31Lkhxg42LY6of8TaYyK50jnxRnM="
            crossorigin="anonymous"></script>
    <script>
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
<form id="editorForm" action="/register.do" method="post">
    제목 <input type="text" name="subject" id="subject" maxlength="100">
    내용
    <div style="width: 1000px">
        <jsp:include page="/editor/editorSkinForRegister.jsp" flush="false"/>
    </div>
    <input type="button" onclick="checkData()" value="등록">
</form>
</body>
</html>
