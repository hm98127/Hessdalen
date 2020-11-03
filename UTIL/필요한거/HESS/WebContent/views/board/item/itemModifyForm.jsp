<%@ page import="org.mdoubleh.www.board.item.vo.ItemVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ItemVo vo = (ItemVo) request.getAttribute("vo");
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
<script>
	var cn = '<%=vo.getCont()%>';
	
	function checkData() {
		var nm = $('#nm');
		var price = $('#price');
		var cont = $('#cont');
		
		if (!nm.val()) {
			alert("상품 이름을 입력해 주세요.");
			nm.focus();
			return;
		}
		
		if (!price.val()) {
			alert("상품 가격를 입력해 주세요.");
			price.focus();
			return;
		}
		
		if (!cont.val()) {
			alert("상품 내용를 입력해 주세요.");
			cont.focus();
			return;
		}
		
		var regExpNm = new RegExp("^.{1,50}$", "g");
		if (regExpNm.exec(nm.val()) == null) {
			alert("제목은 50자 이내로 입력해 주세요.");
			nm.val('');
			nm.focus();
			return;
		}
	}
</script>
</head>
<body>
<h1>상품 등록</h1>
<form action="/itemModifyProc.do?pn=<%=nowPage%>&num=<%=vo.getImg_sq()%>" method="post" enctype="multipart/form-data" onsubmit="return checkData()">
	상품 이름 : <input type="text" name="nm" id="nm" value="<%=vo.getNm()%>"/>
	상품 가격 : <input type="text" name="price" id="price" value="<%=vo.getPrice()%>"/>
	상품 내용 : <textarea name="cont" id="cont" rows="10" cols="30"><%=vo.getCont()%></textarea>
	상품 이미지 : <input type="file" name="img" id="img" value="<%=vo.getItem_img()%>"/>
	<input type="submit" value="수정"/>
	<button onclick="location.href='/'">메인</button>
</form> 
</body>
</html>