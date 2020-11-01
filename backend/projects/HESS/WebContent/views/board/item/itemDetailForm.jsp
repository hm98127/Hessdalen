<%@ page import="java.util.ArrayList"%>
<%@ page import="org.mdoubleh.www.common.LoginManager"%>
<%@ page import="org.mdoubleh.www.board.item.vo.ItemVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ItemVo vo = (ItemVo) request.getAttribute("vo");
	ArrayList<ItemVo> list = (ArrayList<ItemVo>) request.getAttribute("list");
	LoginManager lm = LoginManager.getInstance();
	String id = lm.getMemberId(session);
	String nowPage = request.getParameter("pn");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script 
	src="https://code.jquery.com/jquery-3.5.1.min.js" 
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	crossorigin="anonymous">
</script>
</head>
<body>
<%=vo.getNm()%>
<%=vo.getPrice()%>
<%=vo.getItem_img()%>

<button onclick="location.href='/itemRent.do?pn=<%=nowPage%>&num=<%=vo.getItem_sq()%>'">렌트</button>
<button onclick="location.href='/cartAdd.do?pn=<%=nowPage%>&num=<%=vo.getItem_sq()%>'">장바구니 담기</button>
<button onclick="location.href='/naverPay.do?pn=<%=nowPage%>&num=<%=vo.getItem_sq()%>'">네이버 페이로 구매</button>
<h3>리뷰게시판</h3>
<table border="1">
	<tr>
		<td>Number</td>
		<td>Photo</td>
		<td>Title</td>
		<td>Writer</td>
		<td>Rating</td>
	</tr>
</table>
<%
	if (id != null && id.equals(vo.getId())) {
%>
<button onclick="location.href='/itemModify.do'">수정</button>
<button onclick="location.href='/itemDelete.do'">삭제</button>
<%
	}
%>
<%
	if (list.size() > 0) {
%>
		<%
			for (ItemVo ref : list) {
		%>
			<img src="images/a.png" id="lmg">
		<%
			}
		%>
<%
	} else {
%>
	<h1>등록된 이미지가 없습니다.</h1>
<%
	}
%>

</body>
</html>