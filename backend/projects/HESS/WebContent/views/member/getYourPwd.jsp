<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	int num = (int) request.getAttribute("num");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>YOUR_PWD</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	crossorigin="anonymous">
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
</style>
<script>
	function checkValidate() {
		var pwd = $('#pwd');
		var confirm_pwd = $('#confirm_pwd');
	
		if (!pwd.val()) {
			alert('비밀번호를 입력해 주세요.');
			pwd.focus();
			return;
		}
	
		if (!confirm_pwd.val()) {
			alert("비밀번호 확인을 입력해 주세요.");
			confirm_pwd.focus();
			return;
		}
		
		if (pwd.val() != confirm_pwd.val()) {
			alert("비밀번호가 다릅니다.");
			pwd.val('');
			confirm_pwd.val('');
			pwd.focus();
			return;
		}
		
		var regExpPwd = new RegExp("^.{4,30}$", "g");
		if (regExpPwd.exec(pwd.val()) == null) {
			alert("비밀번호는 4~30자로 입력해 주세요.");
			pwd.val('');
			confirm_pwd.val('');
			pwd.focus();
			return;
		}
	}
</script>
</head>
<body>
<!-- Login -->
<div id="login">
	<span class="login__btn"><a href="/memberLogin.do">Login</a></span>
	<span class="login__btn"><a href="/memberJoin.do">Sign up</a></span>
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
			<li class="navbar__component"><a href="/itemList.do">ITEM</a></li>
			<li class="navbar__component"><a href="/noticeList.do">SERVICE</a>
			</li>
			<form id="input__form" action="">
				<input type="text" placeholder="Search" id="search__input" />
				<i class="fas fa-search"></i>
			</form>
		</ul>
	</div>
	<div class="category">
		<i class="fas fa-bars"></i>
	</div>
</div>
<h1 style="text-align: center; margin: 40px 0 5px 0">비밀번호 변경</h1>
<p style="text-align: center; font-weight: 300; font-size: 1rem; margin-top: 5px; margin-bottom: 30px;">─</p>
<p class="info">"비밀번호를 입력해주세요."</p>
<form id="form" action="/getYourPwd.do" method="post" onsubmit="return checkValidate()">
	<div class="input__container">
		<input type="password" name="pwd" id="pwd" maxlength="30" placeholder="비밀번호 " />
	</div>
	<div class="input__container">
		<input type="password" name="confirm_pwd" id="confirm_pwd" maxlength="30" placeholder="비밀번호 확인 " />
	</div>
	<input type="submit" value="비밀번호 변경" />
	<input type="hidden" value="<%=num%>" name="num" />
</form>
</body>
</html>
