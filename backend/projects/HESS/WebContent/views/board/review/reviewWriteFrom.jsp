<%@ page import="org.mdoubleh.www.common.LoginManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	LoginManager lm = LoginManager.getInstance();
	String id = lm.getMemberId(session);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>REVIEW_WRITE_FROM</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&display=swap" rel="stylesheet">
<link rel="stylesheet" href="/design/write/css/writeForm.css">
<link rel="stylesheet" href="/design/css/navbar.css">
<link rel="icon" type="image/png" href="/design/imgs/Logo.png">
<script src="/design/js/category.js" defer></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.js" integrity="sha256-DrT5NfxfbHvMHux31Lkhxg42LY6of8TaYyK50jnxRnM=" crossorigin="anonymous"></script>
<script>
	function checkData() {
		var title__input = $('#title__input').val();
		if (!title__input) {
			alert("제목을 입력하세요.");
			$('#title__input').focus();
			return false;
		}

		var price = $('#price').val();
		if (!price) {
			alert("가격을 입력하세요.");
			$('#price').focus();
			return false;
		}

		var content = $('#content').val();
		if (!content) {
			alert("내용을 입력하세요.");
			$('#content').focus();
			return false;
		}

		var image = $('#image').val();
		if (!image) {
			alert("이미지를 넣으세요.");
			$("#image").focus();
			return;
		}
		
		var regExpPrice = new RegExp('^.{1,30}$', 'g');
		if (regExpPrice.exec(price) == null) {
			alert('잘못된 가격 입니다.');
			$('#price').val('');
			$('#price').focus();
			return false;
		}
	}
</script>
</head>
<body>
	<!-- Login -->
	<div id="login">
		<%
			if (id == null) {
		%>
		<span class="login__btn">
			<a href="/memberLogin.do">LOGIN</a>
		</span>
		<span class="login__btn">
			<a href="/memberJoin.do">JOIN</a>
		</span>
		<%
			} else {
		%>
		<span class="login__btn">
			<a href="/memberLogout.do">LOGOUT</a>
		</span>
		<span class="login__btn">
			<a href="/memberPage.do">MEMBERPAGE</a>
		</span>
		<%
			}
		%>
	</div>
	<!-- Navbar -->
	<div id="navbar">
		<div class="logo">
			<div class="logo__title">
				<a href="/main.jsp">HESSDALEN</a>
			</div>
			<div class="square__point">
				<div class="squareA">
					<i class="fas fa-square-full"></i>
				</div>
				<div class="squareB">
					<i class="fas fa-square-full"></i>
				</div>
			</div>
			<ul class="navbar__menu">
				<li class="navbar__component"><a href="/info.do">INFO</a></li>
				<li class="navbar__component"><a href="/itemList.do?pn=1">ITEM</a></li>
				<li class="navbar__component"><a href="/noticeList.do">SERVICE</a></li>
				<li>
					<form id="input__form" action="">
						<input type="text" placeholder="Search" id="search__input" />
						<i class="fas fa-search"></i>
					</form>
				</li>
			</ul>
		</div>
		<div class="category">
			<i class="fas fa-bars"></i>
		</div>
	</div>
	<div class="category__click">
	
		<!-- WriteForm -->
		<div id="write__container">
			<div class="write__title">
				<h2>Creating a post</h2>
			</div>
			<div class="form__box">
				<form action="/reviewRegister.do" method="post" onsubmit="return checkData()" enctype="multipart/form-data">
					<div class="write__box">
						<div class="input__name">Title</div>
						<input type="text" name="title" id="title__input" maxlength="100" placeholder="Please enter the author." />
						<div class="input__name">Content</div>
						<textarea name="content" id="content" cols="30" rows="10"></textarea>
						
						<input type="file" name="image" id="image" />
						<input type="submit" value="Insert" id="insert" />
					</div>
				</form>
			</div>
		</div>
</body>
</html>