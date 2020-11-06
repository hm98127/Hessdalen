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
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
            integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
            crossorigin="anonymous">
</script>
<script>
	function checkDelete(url) {
		var result;
		console.log(url);
		
		result = confirm("글 삭제를 하겠습니까?")
		
		if (result == true) {
			location.href = url;
		}
	}
</script>
</head>
<body>
	<div>
		제목 : <%=vo.getNotice_title()%>
	</div>
	<div>
		내용 : <%= vo.getNotice_content() %>
	</div>
	<button onclick="location.href='/noticeList.do'">뒤로가기</button>
	<%
		if (id != null && id.equals(vo.getMember_id())) {
	%>
	<button onclick="location.href='/noticeModify.do?num=<%=vo.getNotice_postnum()%>'">수정</button>
	<button onclick="javascript:checkDelete('/noticeDelete.do?num=<%=vo.getNotice_postnum()%>')">삭제</button>
	<%
		}
	%>
</body>
</html>