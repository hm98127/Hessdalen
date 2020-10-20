<%@ page import="com.kb.www.vo.ArticleVo" %>
<%@ page import="com.kb.www.vo.MemberVo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MemberVo vo = (MemberVo) request.getAttribute("vo");
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원정보수정</title>
    <script
            src="https://code.jquery.com/jquery-3.5.1.slim.js"
            integrity="sha256-DrT5NfxfbHvMHux31Lkhxg42LY6of8TaYyK50jnxRnM="
            crossorigin="anonymous"></script>
    <script>
        function validateCheck() {
            var pwd = $('#pwd').val();
            var pwd_confirm = $('#pwd_confirm').val();

            if (!pwd) {
                alert("비밀번호를 입력해 주세요.");
                $('#pwd').focus();
                return false;
            }

            if (!pwd_confirm) {
                alert("비밀번호확인을 입력해 주세요.");
                $('#pwd_confirm').focus();
                return false;
            }

            if (pwd != pwd_confirm) {
                alert("비밀번호가 다릅니다.");
                $('#pwd').val("");
                $('#pwd_confirm').val("");
                $('#pwd').focus();
                return false;
            }

            var regExpPwd = new RegExp("^.{4,30}$", "g");
            if (regExpPwd.exec(pwd) == null) {
                alert("잘못된 비밀번호 형식입니다.");
                $('#pwd').val("");
                $('#pwd_confirm').val("");
                $('#pwd').focus();
                return false;
            }
        }
    </script>
</head>
<body>
<form action="/memberUpdateProc.do" method="post" onsubmit="return validateCheck()">
    아이디 : <input type="text" name="id" id="id" minlength="4" maxlength="20" value="<%=vo.getId()%>" readonly>
    비밀번호 : <input type="password" name="pwd" id="pwd" minlength="4" maxlength="30">
    비밀번호확인 : <input type="password" name="pwd_confirm" id="pwd_confirm" minlength="4" maxlength="30">
    <input type="submit" value="수정">
    <button onclick="location.href='/'">취소</button>
</form>
</body>
</html>
