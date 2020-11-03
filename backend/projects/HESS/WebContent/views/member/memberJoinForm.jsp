<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>SIGN UP</title>
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
	var isChecked = false;
	function validateCheck() {
		var name = $('#name').val();
		var id = $('#id').val();
		var pwd = $('#pwd').val();
		var pwd_confirm = $('#pwd_confirm').val();
	
		if (!name) {
			alert('이름을 입력해 주세요.');
			$('#name').focus();
			return false;
		}
	
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
	
		if (!pwd_confirm) {
			alert('비밀번호확인을 입력해 주세요.');
			$('#pwd_confirm').focus();
			return false;
		}
	
		if (pwd != pwd_confirm) {
			alert('비밀번호가 다릅니다.');
			$('#pwd').val('');
			$('#pwd_confirm').val('');
			$('#pwd').focus();
			return false;
		}
	
		var regExpName = new RegExp('^[가-힣a-z]{1,10}$', 'g');
		if (regExpName.exec(nm) == null) {
			alert('잘못된 이름 형식입니다.');
			$('#name').val('');
			$('#name').focus();
			return false;
		}
	
		var regExpId = new RegExp('^[a-z0-9]{4,20}$', 'g');
		if (regExpId.exec(id) == null) {
			alert('잘못된 아이디 형식입니다.');
			$('#id').val('');
			$('#id').focus();
			return false;
		}
	
		var regExpPwd = new RegExp('^.{4,30}$', 'g');
		if (regExpPwd.exec(pwd) == null) {
			alert('잘못된 비밀번호 형식입니다.');
			$('#pwd').val('');
			$('#pwd_confirm').val('');
			$('#pwd').focus();
			return false;
		}
	
		if (!isChecked) {
			alert('아이디 중복 검사를 해주세요.');
			return false;
		}
	}
	
	function checkId() {
		var id = $('#id').val();
	
		$.ajax({
			url : '/checkId.ajax',
			type : 'post',
			data : {
				id : id,
			},
			dataType : 'json',
			error : function() {
				alert('통신 실패');
			},
			success : function(data) {
				if (data.id == 'true') {
					// 중복된 아이디 없음
					isChecked = true;
					alert('사용할 수 있는 아이디 입니다.');
				} else {
					// 중복된 아이디 있음
					alert('사용할 수 없는 아이디 입니다.');
				}
			},
		});
	}
	
	function initCheckId() {
		isChecked = false;
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
			<li class="navbar__component"><a href="/itemList.do">PRODUCTS</a></li>
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
<h1 style="text-align: center; margin: 40px 0 5px 0">회원가입</h1>
<p style="text-align: center; font-weight: 300; font-size: 1rem; margin-top: 5px; margin-bottom: 30px;">─</p>
<p class="info">"아래 정보를 확인하여 주세요."</p>
<form id="form" action="/memberJoinProc.do" method="post" onsubmit="return validateCheck()">
	<div class="input__container">
		<input type="text" name="name" id="name" maxlength="10" placeholder="이름 " />
	</div>
	<div class="double__check">
		<input type="text" name="id" id="id" maxlength="20" oninput="initCheckId()" placeholder="아이디 " />
		<button type="button" onclick="checkId()" id="id__double__check">중복확인</button>
	</div>
	<div class="input__container">
		<input type="password" name="pwd" id="pwd" maxlength="30" placeholder="비밀번호 " />
	</div>
	<div class="input__container">
		<input type="password" name="pwd_confirm" id="pwd_confirm" maxlength="30" placeholder="비밀번호확인 " />
	</div>
	<input type="submit" value="가입" />
</form>
</body>
</html>
