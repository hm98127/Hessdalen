<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>LOGIN</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous">
	
</script>

<%-- design --%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&display=swap" rel="stylesheet" />
<link rel="icon" type="image/png" href="/design/imgs/Logo.png" />
<link rel="stylesheet" href="/design/css/navbar.css" />
<link rel="stylesheet" href="/design/css/login.css" />
<script src="/design/js/category.js" defer></script>
<script src="/design/js/category.js" defer></script>
<script src="/design/js/stop.js" defer></script>
<style>
#form {
	display: flex;
	flex-direction: column;
	align-items: center;
}

.double__check {
	position: relative;
}

#id__double__check {
	position: absolute;
	top: 14px;
	right: -80px;
}

.info {
	position: relative;
	text-align: center;
	margin-bottom: 40px;
}

.info::before {
	position: absolute;
	content: ' ';
	display: block;
	top: 8px;
	left: 30%;
	width: 10%;
	height: 1px;
	background: rgba(234, 234, 234, 1);
}

.info::after {
	position: absolute;
	content: ' ';
	display: block;
	top: 8px;
	left: 60%;
	width: 10%;
	height: 1px;
	background: rgba(234, 234, 234, 1);
}

input {
	width: 400px;
	height: 36px;
	font-size: 15px;
	margin: 10px 0px;
}

input::placeholder {
	opacity: 0.7;
	padding-left: 5px;
}

.login__search {
	display: flex;
	margin-top: 10px;
}

#pw__search, #id__search {
	outline: none;
	border: none;
	background-color: transparent;
	margin: 0px 12px;
	cursor: pointer
}

.external__btn {
	display: flex;
}

.kakao__btn, .naver__btn {
	margin: 20px 10px;
	cursor: pointer;
}

.kakao__btn img, .naver__btn img {
	width: 190px;
	height: 2.5rem;
}
</style>
<script>
	function validateCheck() {
		var id = $('#id').val();
		var pwd = $('#pwd').val();

		if (!id) {
			alert('아이디를 입력해 주세요.');
			$('#id').focus();
			return false;
		}

		if (!pwd) {
			alert('비밀번호를 입력해 주세요.');
			$('#pwd').focus();
			return false;
		}
	}
</script>
</head>
<body>
	<!-- Login -->
	<div id="login">
		<span class="login__btn">
			<a href="/memberLogin.do">Login</a>
		</span>
		<span class="login__btn">
			<a href="/memberJoin.do">Sign up</a>
		</span>
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
	<h1 style="text-align: center; margin: 40px 0 5px 0">로그인</h1>
	<p style="text-align: center; font-weight: 300; font-size: 1rem; margin-top: 5px; margin-bottom: 30px;">─</p>
	<p class="info">"네이버로그인, 카카오로그인 가능"</p>
	<form id="form" action="/memberLoginProc.do" method="post" onsubmit="return validateCheck()">
		<div class="double__check">
			<input type="text" name="id" id="id" maxlength="20" placeholder="아이디 " />
		</div>
		<div class="input__container">
			<input type="password" name="pwd" id="pwd" maxlength="30" placeholder="비밀번호 " />
		</div>
		<input type="submit" value="로그인" />
		<div class="login__search">
			<button id="id__search" type="button" onclick="location.href='/getMemberId.do'">Find ID</button>
			<button id="pw__search" type="button" onclick="location.href='/getMemberPwd.do'">Find PWD</button>
		</div>


		<form action="">
			<div class="external__btn">
				<div class="kakao__btn">
					<img src="/design/imgs/kakaoLogin.png" />
				</div>
				<div class="naver__btn">
					<img src="/design/imgs/naverLogin.png" />
				</div>
			</div>
		</form>
</body>
</html>
