<%@page import="org.mdoubleh.www.board.item.vo.BoardVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="org.mdoubleh.www.common.LoginManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	ArrayList<BoardVo> list = (ArrayList<BoardVo>) request.getAttribute("list");
String nowPage = request.getParameter("pn");
LoginManager lm = LoginManager.getInstance();
String id = lm.getMemberId(session);
%>
<html>
<head>
<meta charset="UTF-8">
<title>MAIN</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&display=swap" rel="stylesheet" />
<link rel="stylesheet" href="/design/css/navbar.css" />
<link rel="stylesheet" href="/design/css/main.css" />
<link rel="icon" type="image/png" href="/design/imgs/Logo.png" />
<script src="/design/js/category.js" defer></script>
<script src="/design/js/moreBtn.js" defer></script>
<script src="/design/js/eventFadeIn.js" defer></script>
<script src="/design/js/newFadeIn.js" defer></script>
<script>
function goDetail(num) {
    location.href ="/itemDetail.do?pn=" + <%=nowPage%> + "&num=" + num;
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
			<div class="square__point">
				<div class="squareA">
					<i class="fas fa-square-full"></i>
				</div>
				<div class="squareB">
					<i class="fas fa-square-full"></i>
				</div>
			</div>
			<div class="logo__title">
				<a href="/main.jsp">HESSDALEN</a>
			</div>
			<ul class="navbar__menu">
				<li class="navbar__component"><a href="/info.do">INFO</a></li>
				<li class="navbar__component"><a href="/itemList.do?pn=1">ITEM</a></li>
				<li class="navbar__component"><a href="/noticeList.do">SERVICE</a></li>
				<li>
					<form id="input__form" action="">
						<i class="fas fa-search"></i>
						<input type="text" placeholder="Search" id="search__input" />
					</form>
				</li>
			</ul>
		</div>
		<div class="category">
			<i class="fas fa-bars"></i>
		</div>
	</div>

	<div class="category__click">

		<!-- MainBanner -->
		<div id="main__banner">
			<img src="/design/imgs/furnitureMain.jpg" alt="MainBanner" />
			<div class="main__title">
				<h1>Luxury Furniture</h1>
				<h2>
					Furniture containing both the sensibilty and<br /> comfort of Northern Europe.
				</h2>
			</div>
			<div class="more__btn">
				<p data-link="#event__furnture">More</p>
			</div>
		</div>

		<!-- Event Furniture -->
		<div id="event__furnture">
			<div class="event__left">
				<%
					for (int i = 0; i < list.size(); i++) {
				%>
				<%
					if (list.get(i).isItem_event() && (i + 1) % 3 == 1) {
				%>
				<div class="left__furnture" onClick="goDetail(<%=list.get(i).getItem_postnum()%>)">
					<img src="/images/<%=list.get(i).getItem_img()%>" alt="event__furniture1" />
					<div class="left__pricetag">
						<h2><%=list.get(i).getItem_content()%></h2>
					</div>
					<div class="pricetag">
						<p>$<%=list.get(i).getItem_price()%></p>
						<div class="sail__pricetag">
							<p>$140</p>
						</div>
					</div>
					
					<div class="event__tag">Event</div>
				</div>
				<%
					}
				}
				%>
			</div>

			<div class="event__right">
				<div class="event__title">
					<div class="left__title">
						<h2>Event Fu</h2>
					</div>
					<div class="right__title">
						<h2>rniture</h2>
					</div>
				</div>
				<div class="right__furnture">
					<%
						for (int i = 1; i < list.size(); i++) {
					%>
					<%
						if (list.get(i).isItem_event() && i < 3) {
					%>
					<div class="right__furnitureF" onClick="goDetail(<%=list.get(i).getItem_postnum()%>)">
						<img src="/images/<%=list.get(i).getItem_img()%>" alt="furnitureEventFirst" />
						<h2><%=list.get(i).getItem_content()%></h2>
						<div class="pricetag">
							<p>$<%=list.get(i).getItem_price()%></p>
							<div class="sail__pricetag">$480</div>
							<div class="event__tag">Event</div>
						</div>
					</div>
					<%
						}
					%>
					<%
						}
					%>
				</div>

			</div>
		</div>
	</div>

	<!-- vertical Bar -->
	<div id="vertical__bar">
		<div class="bar"></div>
	</div>

	<!-- MD Recommendation -->
	<div id="recommendation">
		<div class="recommendation__title">
			<h1>MD Recommendation</h1>
		</div>

		<div class="recommendation__conitainer">
			<%
				for (int i = 3; i < list.size(); i++) {
			%>
			<%
				if (!list.get(i).isItem_event() && i < 6) {
			%>
			<div class="recommendation__box" onClick="goDetail(<%=list.get(i).getItem_postnum()%>)">
				<img src="/images/<%=list.get(i).getItem_img()%>" alt="furnitureMd" />
				<h1><%=list.get(i).getItem_content()%></h1>
				<div class="recommendation__box__pricetag">
					$<%=list.get(i).getItem_price()%></div>
			</div>
			<%
				}
			}
			%>
		</div>
	</div>

	<!-- Advertising banner -->

	<div class="advertising__banner">
		<div class="banner">
			<img src="/design/imgs/furnitureBannar.png" alt="advertisingBanner" />
		</div>
	</div>

	<!-- New Furniture -->

	<div id="new__furniture">
		<div class="new__title">
			<div class="left__new">
				<h2>New Fu</h2>
			</div>

			<div class="right__new">
				<h2>rniture</h2>
			</div>
		</div>
	</div>

	<div class="new__container">
		<%
			for (int i = 6; i < list.size(); i++) {
		%>
		<%
			if (!list.get(i).isItem_event() && i < 7) {
		%>
		<div class="new__boxf">
			<img src="/images/<%=list.get(i).getItem_img()%>" alt="furniturNewItemFirst" />
			<div class="new__pricetag">
				<h1><%=list.get(i).getItem_content()%></h1>
				<div class="pricetag">
					$<%=list.get(i).getItem_price()%></div>
			</div>
		</div>
		<%
			}
		}
		%>

		<div class="new__boxc">
			<%
				for (int i = 7; i < list.size(); i++) {
			%>
			<%
				if (!list.get(i).isItem_event() && i < 8) {
			%>
			<div class="top__box">
				<img src="/images/<%=list.get(i).getItem_img()%>" alt="furniturNewItemTop" />
				<div class="new__pricetag">
					<h1><%=list.get(i).getItem_content()%></h1>
					<div class="pricetag">
						$<%=list.get(i).getItem_price()%></div>
				</div>
			</div>
			<%
				}
			}
			%>
			<%
				for (int i = 8; i < list.size(); i++) {
			%>
			<%
				if (!list.get(i).isItem_event() && i < 9) {
			%>
			<div class="bottom__box">
				<img src="/images/<%=list.get(i).getItem_img()%>" alt="furniturNewItemBottom" />
				<div class="new__pricetag">
					<h1><%=list.get(i).getItem_content()%></h1>
					<div class="pricetag">
						$<%=list.get(i).getItem_price()%></div>
				</div>
			</div>
			<%
				}
			}
			%>
		</div>
		<%
			for (int i = 9; i < list.size(); i++) {
		%>
		<%
			if (!list.get(i).isItem_event() && i < 10) {
		%>
		<div class="new__boxs">
			<img src="/images/<%=list.get(i).getItem_img()%>" alt="furniturNewItemSecond" />
			<div class="new__pricetag">
				<h1><%=list.get(i).getItem_content()%></h1>
				<div class="pricetag">
					$<%=list.get(i).getItem_price()%></div>
			</div>
		</div>
		<%
			}
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
	<!-- Click Category </div> !! -->
</body>
</html>