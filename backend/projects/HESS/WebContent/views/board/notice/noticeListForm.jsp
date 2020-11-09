<%@ page import="org.mdoubleh.www.board.notice.vo.BoardVo"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="org.mdoubleh.www.common.LoginManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
LoginManager lm = LoginManager.getInstance();
String id = lm.getMemberId(session);
ArrayList<BoardVo> list = (ArrayList<BoardVo>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NOTICELIST</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link rel="icon" type="image/png" href="/design/imgs/Logo.png">
<link rel="stylesheet" href="/design/css/navbar.css">
<link rel="stylesheet" href="/design/service/css/service.css">
<script src="/design/service/js/service.js" defer></script>
<script src="/design/js/category.js" defer></script>
<script>
function goDetail(num) {
    location.href =
        "/noticeDetail.do?num=" + num;
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

	<div class="category__click">

		<!-- Service Title -->
		<div id="service">
			<div class="service__title">
				<h2>Notice</h2>
			</div>

			<div class="select__service">
				<ul class="service__menu">
					<li class="service__notice">Notice</li>
					<li class="service__event">Event</li>
				</ul>
				<div class="write__form">
					<p>
						<a href="/noticeWrite.do">Writing</a>
					</p>
				</div>
			</div>


			<!-- Notice board -->


			<ul class="notice__board">
				<li class="board__list">No</li>
				<li class="board__list">Title</li>
				<li class="board__list">ID</li>
				<li class="board__list">Date</li>
				<li class="board__list">Hits</li>
			</ul>
			<%
				if (list.size() > 0) {
			%>
			<%
				for (int i = 0; i < list.size(); i++) {
			%>
			<ul class="notice__board" onclick="goDetail(<%=list.get(i).getNotice_postnum()%>)">
				<li class="board__list"><%=list.get(i).getNotice_postnum()%></li>
				<li class="board__list"><%=list.get(i).getNotice_title()%></li>
				<li class="board__list"><%=list.get(i).getMember_id()%></li>
				<li class="board__list"><%=list.get(i).getNotice_regdate()%></li>
				<li class="board__list"><%=list.get(i).getNotice_hit()%></li>
			</ul>
			<%
				}
			%>
			<%
				} else {
			%>
			<ul class="notice__board">
				<li class="board__list">등록된 글이 없습니다.</li>
			</ul>
			<%
				}
			%>

		</div>

		<!-- Footer -->

		<div id="footer">
			<div class="footer__description">
				<div class="team__name">Article 4 Project</div>
				<h1>Jeonghan</h1>
				<h1>Yoengmin</h1>
				<h1>Sung Heon</h1>
				<p>Copyright 2020.All rights reserved</p>
			</div>
		</div>
	</div>
	<!--  Category click </div>-->
</body>
</html>