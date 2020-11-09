<%@ page import="org.mdoubleh.www.board.review.vo.ReviewVo"%>
<%@ page import="org.mdoubleh.www.common.LoginManager"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="org.mdoubleh.www.board.item.vo.BoardVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	BoardVo vo = (BoardVo) request.getAttribute("vo");
ArrayList<BoardVo> itemList = (ArrayList<BoardVo>) request.getAttribute("list");
ArrayList<ReviewVo> reviewList = (ArrayList<ReviewVo>) request.getAttribute("reviewList");
String nowPage = request.getParameter("pn");
LoginManager lm = LoginManager.getInstance();
String id = lm.getMemberId(session);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&display=swap" rel="stylesheet">
<link rel="stylesheet" href="/design/css/navbar.css">
<link rel="icon" type="image/png" href="/design/imgs/Logo.png">
<link rel="stylesheet" href="/design/css/detail.css">
<script src="/design/js/carousel.js" defer></script>
<script src="/design/js/category.js" defer></script>
<script>
	function checkDelete(url) {
		var result;
		console.log(url);

		result = confirm("글 삭제를 하겠습니까?")

		if (result == true) {
			location.href = url;
		}
	}
	
	function goDetail(num) {
	    location.href ="/itemDetail.do?pn=" + <%=nowPage%> + "&num=" + num;
	}
	
	function getReviewDetail(num) {
		location.href = "/reviewDetail.do?num=" + num;
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
			<a href="/memberLogin.do">Login</a>
		</span>
		<span class="login__btn">
			<a href="/memberJoin.do">Sign up</a>
		</span>
		<%
			} else {
		%>
		<span class="login__btn">
			<a href="/itemModify.do?num=<%=vo.getItem_postnum()%>">상품 수정</a>
		</span>
		<span class="login__btn">
			<a href="javascript:checkDelete('/itemDelete.do?num=<%=vo.getItem_postnum()%>')">상품 삭제</a>
		</span>
		<span class="login__btn">
			<a href="/memberlogout.do">Logout</a>
		</span>
		<%
			}
		%>
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

	<!-- Category -->
	<div class="category__click">

		<!-- Detail Product -->
		<div id="detail__products">
			<div class="left__detail">
				<div class="detail__description">
					<div class="detail__title">
						<h1><%=vo.getItem_title()%></h1>
					</div>
					<div class="detail__price">
						<p>Price : $<%=vo.getItem_price()%></p>
					</div>
					<ul class="rb__btn">
						<li class="rent__btn">Purchase</li>
						<li class="basket__btn">Basket</li>
					</ul>

					<div class="production__detail__description">
						Lorem ipsum dolor sit amet consectetur adipisicing elit. Hic laudantium<br /> non quaerat qui nostrum? Ab voluptatum numquam <br />perspiciatis dicta perferendis commodi, corrupti, reprehenderit veniam <br />facilis quisquam dolorem voluptas magni enim.
					</div>
					<div class="naver__buy">
						<div class="buy__description">
							<div class="naver__logo">NAVER</div>
							<div calss="navar__pay">
								Simple purchase<br /> through Naver ID Navar Pay
							</div>
						</div>
						<div class="buy__btn">
							<img src="/design/imgs/naverLogo.png" alt="naverLogo" style="width: 18px;"> Pay Purchase
						</div>
					</div>

					<div class="review">
						<div class="review__title">Review</div>
						<ul class="review__board">
							<li class="review__list">Number</li>
							<li class="review__list">Photo</li>
							<li class="review__list">Title</li>
							<li class="review__list">Writer</li>
							<li class="review__list">Rating</li>
						</ul>
						<%
							if (reviewList.size() > 0) {
						%>
						<%
							for (int i = 0; i < reviewList.size(); i++) {
						%>
						<ul class="review__board" onclick="goReviewDetail(<%=reviewList.get(i).getReview_postnum()%>)">
							<li class="review__list"><%=reviewList.get(i).getReview_postnum()%></li>
							<li class="review__list"><%=reviewList.get(i).getReview_img()%></li>
							<li class="review__list"><%=reviewList.get(i).getReview_title()%></li>
							<li class="review__list"><%=reviewList.get(i).getMember_id()%></li>
							<li class="review__list"><%=reviewList.get(i).getReview_hit()%></li>
						</ul>
						<%
							}
						%>
						<%
							} else {
						%>
						<ul class="review__board">
							<li class="review__list">등록된 글이 없습니다.</li>
						</ul>
						<%
							}
						%>
						<button onclick="location.href='/reviewWrite.do'">후기 쓰기</button>
					</div>
				</div>
			</div>
			<div class="right__detail">
				<img src="/images/<%=vo.getItem_img()%>" alt="detailImage">
			</div>
		</div>

		<!-- Detail More Products -->
		<div id="relative__category">
			<h2>Related Products</h2>
		</div>
		<div id="more__carousel">
			<div class="left__btn">
				<i class="fas fa-angle-left"></i>
			</div>
			<div id="container">
				<div class="slide_wrap">
					<div class="slide_box">
						<div class="slide_list">
							<%
								for (int i = 0; i < itemList.size(); i++) {
								if (itemList.get(i).getItem_postnum() != vo.getItem_postnum()
								|| vo.getItem_group() == itemList.get(i).getItem_group()) {
							%>
							<div class="current__slide">
								<img src="/images/<%=itemList.get(i).getItem_img()%>" alt="detailImage" onclick="goDetail(<%=itemList.get(i).getItem_postnum()%>)">
							</div>
							<%
								}
							}
							%>
						</div>
					</div>
				</div>
			</div>
			<div class="right__btn">
				<i class="fas fa-angle-right"></i>
			</div>
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
</body>
</html>