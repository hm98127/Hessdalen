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
<title>INFO</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&display=swap" rel="stylesheet">
<link rel="stylesheet" href="/design/css/navbar.css">
<link rel="stylesheet" href="/design/css/info.css">
<link rel="icon" type="image/png" href="/design/imgs/Logo.png">
<script src="/design/js/category.js" defer></script>
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
				<a href="/">HESSDALEN</a>
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

		<!-- Info -->
		<div id="info">
			<div class="info__title">
				<h2>TEAM</h2>
			</div>
			<!-- https://www.notion.so/TeamProject-5f4e4beff59444aca328c2d2a5427520 -->
			<div class="info__description">


				<div class="info__individual">
					<div class="info__img">
						<a href=""><img src="/design/imgs/furniutreTeamPark.jpg" alt="info__Park"></a>
					</div>
					<div class="info__component">
						<h2>Name : Park Young Min</h2>
						<h3>
							Part : Front-end <a href="https://github.com/"><i class="fab fa-git-alt"></i></a>
						</h3>
						<div class="process">
							<a href="https://www.notion.so/TeamProject-5f4e4beff59444aca328c2d2a5427520">UI & UX Design Process</a>
						</div>
					</div>

				</div>
				<div class="info__individual">

					<div class="info__img">
						<a href=""> <img src="/design/imgs/furniutreTeamJo.jpg" alt="info__Jo"></a>
					</div>
					<div class="info__component">
						<h2>Name : Cho Sung Hun</h2>
						<h3>
							Part : Back-end <a href=""><i class="fab fa-git-alt"></i></a>
						</h3>

					</div>
				</div>
				<div class="info__individual">

					<div class="info__img">
						<a href=""><img src="/design/imgs/furniutreTeamKim.jpg" alt="info__Kim"></a>
						<!-- href에다가 포폴 링크넣으시면 될거같네용 -->
					</div>
					<div class="info__component">
						<h2>Name : Kim Jeong han</h2>
						<h3>
							Part : DevOps<a href=""><i class="fab fa-git-alt"></i></a>
						</h3>

					</div>
				</div>
			</div>
		</div>
</body>
</html>