<%@page import="org.mdoubleh.www.common.Pagenation"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.mdoubleh.www.vo.ArticleVo" %>
<%@ page import="org.mdoubleh.www.common.LoginManager" %>
<%
	ArrayList<ArticleVo> list = (ArrayList<ArticleVo>) request.getAttribute("list");
	LoginManager lm = LoginManager.getInstance();
	String id = lm.getMemberId(session);
	Pagenation pagenation = (Pagenation) request.getAttribute("pagenation");
	String nowPage = request.getParameter("pn");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<script 
	src="https://code.jquery.com/jquery-3.5.1.slim.js"
	integrity="sha256-DrT5NfxfbHvMHux31Lkhxg42LY6of8TaYyK50jnxRnM="
	crossorigin="anonymous">
</script>
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&display=swap" rel="stylesheet" />
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" rel="stylesheet" />
<link rel="icon" type="image/png" href="/images/Logo.png">
<link rel="stylesheet" href="/css/navbar.css">
<link rel="stylesheet" href="/css/service.css">
<script src="/js/serviceSelect.js" defer></script>
<script src="/js/sCategory.js" defer></script>
<script>
	function goDetail(num) {
		location.href="/detail.do?num=" + num;
	}
</script>
</head>
<body>
	<!-- Login -->
	<div id="login">
		<%
			if (id == null) {
		%>
		<span class="login__btn"><a href="/login.do">Login</a></span> 
		<%
			} else {
		%>
		<span class="login__btn"><a href="/logout.do">Logout</a></span> 
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

			<ul class="menu__list">
				<li class="menu__items"><a href="../info/info.html">INFO</a></li>
				<li class="menu__items"><a href="../products/products.html">PRODUCTS</a></li>
				<li class="menu__items"><a href="/list.do">SERVICE</a></li>
				<form id="input__form" action=''>
					<input type="text" placeholder="Search" id="search__input">
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
					<li class="service__event">Event</li>
					<li class="service__notice">Notice</li>
				</ul>
			</div>

			<!-- Notice board -->
			<!-- 게시판 글쓰기 -->
			<ul class="notice__board">
				<li class="board__list">Number</li>
				<li class="board__list">Title</li>
				<li class="board__list">Date</li>
				<li class="board__list">Hits</li>
			</ul>
			<%
				for (int i = 0; i < list.size(); i++) {
			%>
			<ul class="notice__board" onclick="goDetail(<%= list.get(i).getArticl_sq() %>)">
				<li class="board__list"><%= list.get(i).getArticl_sq() %></li>
				<li class="board__list"><%= list.get(i).getSj() %></li>
				<li class="board__list"><%= list.get(i).getDttm() %></li>
				<li class="board__list"><%= list.get(i).getHit() %></li>
			</ul>
			<%
				}
			%>
		</div>
		<span>
			<a href="/list.do?pn=<%= pagenation.getStartPage() - 1 %>">◁</a>
		</span>
		<% 
			for (int i = pagenation.getStartPage(); i <= pagenation.getEndPage(); i++) { 
		%>
		<span>
			<a href="/list.do?pn=<%= i %>">
				<%= i %>
			</a>
		</span>
		<%
			}
		%>
		<span>
			<a href="/list.do?pn=<%= pagenation.getEndPage() + 1 %>">▷</a>
		</span>
		<!-- 글쓰기 버튼 -->
		<button onclick="location.href='/write.do'">글쓰기</button> 

		<!-- Footer -->

		<div id="footer">
			<img src="/images/tempFooter.png" alt="">
		</div>
	</div>

</body>
</html>