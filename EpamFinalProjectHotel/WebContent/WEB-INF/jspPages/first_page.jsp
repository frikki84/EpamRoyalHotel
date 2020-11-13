<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix = "mytag" uri = "/WEB-INF/MyTag.tld"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="vendors/linericon/style.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">
<link rel="stylesheet"
	href="vendors/bootstrap-datepicker/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="vendors/nice-select/css/nice-select.css">
<link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">
<!-- main css -->
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/responsive.css">
<link rel="icon" href="image/favicon.png" type="image/png">


<title>MagicHotel HomePage</title>

<fmt:setLocale value="${sessionScope.local}"></fmt:setLocale>

<fmt:setBundle basename="resources.local" var="loc" scope="session"></fmt:setBundle>

<fmt:message bundle="${loc}" key="home_button" var="home_button" />
<fmt:message bundle="${loc}" key="language_button" var="language_button" />
<fmt:message bundle="${loc}" key="language_button_en"
	var="language_button_en" />
<fmt:message bundle="${loc}" key="language_button_ru"
	var="language_button_ru" />
<fmt:message bundle="${loc}" key="gallery_buttom" var="gallery_buttom" />
<fmt:message bundle="${loc}" key="contact_buttom" var="contact_buttom" />
<fmt:message bundle="${loc}" key="lema_away_from_monotorios"
	var="lema_away_from_monotorios" />
<fmt:message bundle="${loc}" key="lema_relax" var="lema_relax" />
<fmt:message bundle="${loc}" key="lema_description"
	var="lema_description" />
<fmt:message bundle="${loc}" key="log_in_button" var="log_in_button" />
<fmt:message bundle="${loc}" key="sign_up_button" var="sign_up_button" />
<fmt:message bundle="${loc}" key="hotel_accomodation"
	var="hotel_accomodation" />
<fmt:message bundle="${loc}" key="lema_accomodation_button"
	var="lema_accomodation_button" />
<fmt:message bundle="${loc}" key="rub_night"
	var="rub_night" />
<fmt:message bundle="${loc}" key="book_now"
	var="book_now" />
<fmt:message bundle="${loc}" key="history"
	var="history" />
<fmt:message bundle="${loc}" key="rights"
	var="rights" />

</head>

<body>
	<header class="header_area">
		<div class="container">
			<nav class="navbar navbar-expand-lg navbar-light">
				<!-- Brand and toggle get grouped for better mobile display -->
				<a class="navbar-brand logo_h" href="index.html"><img
					src="image/Logo.png" alt=""></a>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse offset"
					id="navbarSupportedContent">
					<ul class="nav navbar-nav menu_nav ml-auto">
						<li class="nav-item active"><a class="nav-link"
							href="index.jsp">${home_button}</a></li>
						<li class="nav-item submenu dropdown"><a href="#"
							class="nav-link dropdown-toggle" data-toggle="dropdown"
															
							role="button" aria-haspopup="true" aria-expanded="false">${language_button}</a>
							<ul class="dropdown-menu">
								<li class="nav-item">
									<form action="mainPage" method="post">
									<input type="hidden" name="command" value="locale_change"> 									
										<input type="hidden" name="local" value="en"> 
										<input type="hidden" name="address" value="${address}"> 
										<input type="submit" value="${language_button_en}" class="nav-link"/>
									</form>
								</li>
								<li class="nav-item">
									<form action="mainPage" method="post">
									<input type="hidden" name="command" value="locale_change"> 	
										<input type="hidden" name="local" value="ru"> 
										<input type="hidden" name="address" value="${address}"> 
										<input type="submit" value="${language_button_ru}" class="nav-link"/>
									</form>
							</li>
					
							</ul>
							</li>
							<li class="nav-item"><a class="nav-link" href="contacts.jsp">${contact_buttom}</a></li>
					</ul>
				</div>
			</nav>
		</div>
	</header>
	<section class="banner_area">
		<div class="booking_table d_flex align-items-center">
			<div class="overlay bg-parallax" data-stellar-ratio="0.9"
				data-stellar-vertical-offset="0" data-background=""
				style="transform: translateY(-0.017054px);"></div>
			<div class="container">
				<div class="banner_content text-center">
					<h6>${lema_away_from_monotorios}</h6>
					<h2>${lema_relax}</h2>
					<p>${lema_description}</p>
				</div>

				<div class="banner_content text-center">

					<form action="mainPage" method="post">
						<input type="hidden" name="command" value="GO_TO_LOGINATION_PAGE" />
						<input type="submit" class="btn theme_btn button_hover"
							value="${log_in_button}" />
					</form>

					<br />

					<form action="mainPage" method="post">
						<input type="hidden" name="command"
							value="GO_TO_REGISTRATION_PAGE" /> <input type="submit"
							class="btn theme_btn button_hover" value="${sign_up_button}" />
					</form>
				</div>

			</div>
		</div>

	</section>


	<section class="accomodation_area section_gap">
	
		<div class="container">
			<div class="section_title text-center">
				<h2 class="title_color">${hotel_accomodation}</h2>
				<p>${lema_accomodation_button}</p>
			</div>
			<div class="row mb_30">
			<c:forEach items="${map}" var = "item">
				<div class="col-lg-3 col-sm-6">
					<div class="accomodation_item text-center">
						<div class="hotel_img">
							<img src="image/room1.jpg" alt=""> <a href="#"
								class="btn theme_btn button_hover">${book_now}</a>
						</div>
						<a href="#"><h4 class="sec_h4">${item.key}</h4></a>
						<h5>
							${item.value}<small>${rub_night}</small>
						</h5>
					</div>
					
				</div>
				</c:forEach>
			</div>
		</div>
		
	</section>

	

	<footer class="footer-area section_gap">
		<div class="container">
			<div
				class="row footer-bottom d-flex justify-content-between align-items-center">
				<p class="col-lg-8 col-sm-12 footer-text m-0">
					<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
					${history}
					<mytag:mytag/>		
					<br/>
					 ${rights}
					
				</p>
			</div>
		</div>
	</footer>

</body>

</html>