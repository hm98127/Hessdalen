<%@page import="org.mdoubleh.www.common.Paging"%>
<%@ page import="org.mdoubleh.www.board.item.vo.BoardVo"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="org.mdoubleh.www.common.LoginManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	LoginManager lm = LoginManager.getInstance();
String id = lm.getMemberId(session);
ArrayList<BoardVo> list = (ArrayList<BoardVo>) request.getAttribute("list");
Paging paging = (Paging) request.getAttribute("paging");
String nowPage = request.getParameter("pn");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>ITEM LIST</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&display=swap" rel="stylesheet" />
<link rel="icon" type="image/png" href="/design/imgs/Logo.png" />
<link rel="stylesheet" href="/design/css/navbar.css" />
<link rel="stylesheet" href="/design/css/products.css" />
<script src="/design/js/selectList.js" defer></script>
<script src="/design/js/category.js" defer></script>
<script>
function goDetail(num) {
    location.href ="/itemDetail.do?pn=" + <%=nowPage%> + "&num=" + num;
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

	<div class="category__click">
		<!-- Products Banner -->
		<div id="products__banner">
			<img src="/design/imgs/productsBanner.png" alt="productsBanner" />
		</div>
		<a href="itemWrite.do">상품 등록</a>

		<!-- Products Menu List-->
		<div id="products__menu">
			<ul class="products__list">
				<li class="products__items" data-link=".bed__products">BED</li>
				<li class="products__items" data-link=".table__products">TABLE</li>
				<li class="products__items" data-link=".sofa__products">SOFA</li>
				<li class="products__items" data-link=".cabinet__products">CABINET</li>
			</ul>
		</div>
		<div class="products__title">
			<div class="products__Ltitle">
				<h2>Funrni</h2>
			</div>
			<div class="products__Rtitle">
				<h2>ture Product</h2>
			</div>
		</div>

		<!--Bed Products-->
		<div class="bed__products">
			<div class="products__container">
				<%
					for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getItem_group().equals("bed")) {
				%>
				<div class="products__box" onclick="goDetail(<%=list.get(i).getItem_postnum()%>)">
					<div class="products__image">
						<img src="/images/<%=list.get(i).getItem_img()%>" alt="products1" />
					</div>
					<div class="products__name">
						<h2><%=list.get(i).getItem_content()%></h2>
					</div>
					<div class="products__price">
						<p>$<%=list.get(i).getItem_price()%></p>
						<div class="products__sailing">$800</div>
					</div>
				</div>
				<%
					}
				}
				%>
			</div>
		</div>

		<!--Table Products-->
		<div class="table__products">
			<div class="products__container">
				<%
					for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getItem_group().equals("table")) {
				%>
				<div class="products__box" onclick="goDetail(<%=list.get(i).getItem_postnum()%>)">
					<div class="products__image">
						<img src="/images/<%=list.get(i).getItem_img()%>" alt="productTable" />
					</div>
					<div class="products__name">
						<h2><%=list.get(i).getItem_content()%></h2>
					</div>
					<div class="products__price">
						<p>$<%=list.get(i).getItem_price()%></p>
						<div class="products__sailing">$800</div>
					</div>
				</div>
				<%
					}
				}
				%>
			</div>
		</div>

		<!--Sofa Products-->
		<div class="sofa__products">
			<div class="products__container">
				<%
					for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getItem_group().equals("sofa")) {
				%>
				<div class="products__box" onclick="goDetail(<%=list.get(i).getItem_postnum()%>)">
					<div class="products__image">
						<img src="/images/<%=list.get(i).getItem_img()%>" alt="productSofa" />
					</div>
					<div class="products__name">
						<h2><%=list.get(i).getItem_content()%></h2>
					</div>
					<div class="products__price">
						<p>$<%=list.get(i).getItem_price()%></p>
						<div class="products__sailing">$800</div>
					</div>
				</div>
				<%
					}
				}
				%>
			</div>
		</div>

		<!--Cabinet Products-->
		<div class="cabinet__products">
			<div class="products__container">
				<%
					for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getItem_group().equals("cabinet")) {
				%>
				<div class="products__box" onclick="goDetail(<%=list.get(i).getItem_postnum()%>)">
					<div class="products__image">
						<img src="/images/<%=list.get(i).getItem_img()%>" alt="productCaninet" />
					</div>
					<div class="products__name">
						<h2><%=list.get(i).getItem_content()%></h2>
					</div>
					<div class="products__price">
						<p>$<%=list.get(i).getItem_price()%></p>
						<div class="products__sailing">$800</div>
					</div>
				</div>
				<%
					}
				}
				%>
			</div>
		</div>

		<span>
			<a href="/itemList.do?pn=<%=paging.getStartPage() - 1%>"><</a>
		</span>
		<%
			for (int i = paging.getStartPage(); i <= paging.getEndPage(); i++) {
		%>
		<span>
			<a href="/itemList.do?pn=<%=i%>"><%=i%></a>
		</span>
		<%
			}
		%>
		<span>
			<a href="/itemList.do?pn=<%=paging.getEndPage() + 1%>">></a>
		</span>
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

		<!--  Category click </div>-->
	</div>
</body>
</html>