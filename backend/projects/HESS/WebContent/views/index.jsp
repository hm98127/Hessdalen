<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@ page import="org.mdoubleh.www.common.LoginManager" %>
<%
	LoginManager lm = LoginManager.getInstance();
	String id = lm.getMemberId(session);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&display=swap" />
<link rel="stylesheet" href="/css/navbar.css" />
<link rel="stylesheet" href="/css/index.css" />
<link rel="icon" type="image/png" href="/images/Logo.png" />
<script src="/js/category.js" defer></script>
<script src="/js/moreBtn.js" defer></script>
<script src="/js/eventFadeIn.js" defer></script>
<script src="/js/newFadeIn.js" defer></script>
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
				<li class="menu__items"><a href="info/info.html">INFO</a></li>
				<li class="menu__items"><a href="products/products.html">PRODUCTS</a></li>
				<li class="menu__items"><a href="/list.do?pn=1">SERVICE</a></li>
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
		<!-- MainBanner -->
		<div id="main__banner">
			<img src="/images/furnitureMain.jpg" alt="MainBanner">
			<div class="main__title">
				<h1>Luxury Furniture</h1>
				<h2>
					Furniture containing both the sensibilty and<br /> comfort of
					Northern Europe.
				</h2>
			</div>
			<div class="more__btn">
				<p data-link="#event__furnture">More</p>
			</div>

		</div>

		<!-- Event Furniture -->
		<div id="event__furnture">
			<div class="event__left">
				<div class="left__furnture">
					<img src="/images/furnitureEvent1.png" alt="event__furniture1" />
					<div class="left__pricetag">
						<h2>Lizzio Open-type sofa table</h2>
					</div>
					<div class="pricetag">
						<p>120$</p>
						<div class="sail__pricetag">
							<p>140$</p>
						</div>
					</div>
					<div class="event__tag">Event</div>
				</div>
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

				<div class="right__slide">
					<div class="right__furnitureF">
						<img src="/images/furnitureEvent2.png" alt="furnitureEventFirst" />
						<h2>Banson Steel Folding Table 400 A Type</h2>
						<div class="pricetag">
							<p>450$</p>
							<div class="sail__pricetag">480$</div>
							<div class="event__tag">Event</div>
						</div>
					</div>

					<div class="right__furnitureS">
						<img src="/images/furnitureEvent3.png" alt="furnitureEventSeconde" />
						<h2>Zippo Gray Storage sofa table 1000</h2>
						<div class="pricetag">
							<p>99$</p>
							<div class="sail__pricetag">120$</div>
							<div class="event__tag">Event</div>
						</div>
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
				<div class="recommendation__box">
					<img src="/images/furnitureMd1.png" alt="furnitureMd">
					<h1>Market Bee BENKU Single Corner Sofa Rubber</h1>
					<div class="pricetag">120$</div>
				</div>
				<div class="recommendation__box">
					<img src="/images/furnitureMd2.png" alt="furnitureMd">
					<h1>Bianth Kellen Aqua</h1>
					<div class="pricetag">325$</div>
				</div>
				<div class="recommendation__box">
					<img src="/images/furnitureMd3.png" alt="furnitureMd">
					<h1>Paroma Plane LED Multi-Recipient PU Bed</h1>
					<div class="pricetag">712$</div>
				</div>
			</div>
		</div>

		<!-- Advertising banner -->
		<div class="advertising__banner">
			<div class="banner">
				<img src="/images/furnitureBannar.png" alt="advertisingBanner">
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

			<div class="new__boxf">
				<img src="/images/furnitureNewItem1.png" alt="furniturNewItemFirst">
				<div class="new__pricetag">
					<h1>Odin Living Dining sofa set</h1>
					<div class="pricetag">240$</div>
				</div>
			</div>
			<div class="new__boxc">
				<div class="top__box">
					<img src="/images/furnitureNewItem2.png" alt="furniturNewItemTop">
					<div class="new__pricetag">
						<h1>Ikea LINNMON/ADILS Table</h1>
						<div class="pricetag">130$</div>
					</div>
				</div>

				<div class="bottom__box">
					<img src="/images/furnitureNewItem3.png"
						alt="furniturNewItemBottom">
					<div class="new__pricetag">
						<h1>round folding table</h1>
						<div class="pricetag">175$</div>
					</div>
				</div>
			</div>

			<div class="new__boxs">
				<img src="/images/furnitureNewItem4.png" alt="furniturNewItemSecond">
				<div class="new__pricetag">
					<h1>Ldlab Amazing Wooden Rack MS</h1>
					<div class="pricetag">680$</div>
				</div>
			</div>
		</div>

		<!-- Footer -->
		<div id="footer">
			<img src="/images/tempFooter.png" alt="">
		</div>
	</div>
	<!-- Click Category </div> !! -->
</body>
</html>