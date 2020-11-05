<%@ page import="org.mdoubleh.www.member.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	MemberVo vo = (MemberVo) request.getAttribute("vo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>MEMBERPAGE</title>
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
.userContainer{
margin-bottom: 20px;

}
.user__name,
.user__id{
	font-size:14px;
}
.user__name__box,
.user__id__box{
margin:20px 0px;
}

#name,
#id{
  outline:none;
  border-top:none;
  border-left:none;
  border-right:none;	
  border-bottom:1px solid black;
  
}
.btn__category{
	display:flex;
	
}
.btn__category button{
  margin:20px 16px;
  background-color:transparent;
  outline:none;
  border:none;
  cursor:pointer;

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
	
}

input::placeholder {
	opacity: 0.7;
	padding-left: 5px;
}
</style>
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
<h1 style="text-align: center; margin: 40px 0 5px 0">회원페이지</h1>
<p style="text-align: center; font-weight: 300; font-size: 1rem; margin-top: 5px; margin-bottom: 30px;">─</p>
<p class="info">"기본 회원정보"</p>
<form id="form" action="/memberModifyPwd.do" method="post">
	<div class="userContainer">
	<div class="user__name__box">
	<div class ="user__name">User NAME </div>
	   <input type="text" name="name" id="name" value="<%=vo.getMember_name()%>" readonly />
	</div>
	<div class="user__id__box">
	<div class ="user__id">User ID </div>
	    <input type="text" name="id" id="id"  value="<%=vo.getMember_id()%>" readonly />
	</div>
	</div>
	<input type="submit" value="비밀번호 변경" />
	<div class = "btn__category">
	<button type="button" onclick="location.href='/main.jsp'">장바구니</button>
	<button type="button" onclick="location.href='/main.jsp'">결제 내역</button>
	<button type="button" onclick="location.href='/memberDelete.do'">회원 탈퇴</button>
	</div>
</form>
</body>
</html>
