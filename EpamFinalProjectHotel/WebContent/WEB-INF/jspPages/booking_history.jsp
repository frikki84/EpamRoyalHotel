<%@ page language="java" contentType="text/html;UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<fmt:setLocale value="${sessionScope.local}" />

	<fmt:setBundle basename="resources.local" var="loc" />

	<fmt:message bundle="${loc}" key="client_dp_profile"
		var="client_dp_profile" />
	<fmt:message bundle="${loc}" key="client_dp_book" var="client_dp_book" />
	<fmt:message bundle="${loc}" key="client_dp_history"
		var="client_dp_history" />
	<fmt:message bundle="${loc}" key="client_dp_exit" var="client_dp_exit" />



	<fmt:message bundle="${loc}" key="booking_history_title"
		var="booking_history_title" />

	<fmt:message bundle="${loc}" key="booking_history_number"
		var="booking_history_number" />

	<fmt:message bundle="${loc}" key="booking_history_startDate"
		var="booking_history_startDate" />

	<fmt:message bundle="${loc}" key="booking_history_endDate"
		var="booking_history_endDate" />

	<fmt:message bundle="${loc}" key="booking_resultlist_room"
		var="booking_resultlist_room" />

	<fmt:message bundle="${loc}" key="booking_resultlist_peoplenumber"
		var="booking_resultlist_peoplenumber" />

	<fmt:message bundle="${loc}" key="booking_history_childen"
		var="booking_history_childen" />


	<fmt:message bundle="${loc}" key="booking_resultlist_full"
		var="booking_resultlist_full" />

	<fmt:message bundle="${loc}" key="booking_history_prepayment"
		var="booking_history_prepayment" />

	<fmt:message bundle="${loc}" key="booking_history_prepayment_status"
		var="booking_history_prepayment_status" />

	<fmt:message bundle="${loc}" key="booking_history_prepaiment_paid"
		var="booking_history_prepaiment_paid" />


	<fmt:message bundle="${loc}" key="booking_history_pay"
		var="booking_history_pay" />

	<fmt:message bundle="${loc}" key="booking_history_category"
		var="booking_history_category" />
	<fmt:message bundle="${loc}" key="home_button" var="home_button" />
	<fmt:message bundle="${loc}" key="language_button"
		var="language_button" />
	<fmt:message bundle="${loc}" key="language_button_en"
		var="language_button_en" />
	<fmt:message bundle="${loc}" key="language_button_ru"
		var="language_button_ru" />


	<!-- <link rel="stylesheet" href="css/Personal_data_style.css" /> -->
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
	<link rel="stylesheet" href="css/style.css" />
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

	</head>
<body>
	<div class="main_container">
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
											<input type="hidden" name="local" value="en"> <input
												type="hidden" name="address" value="${address}"> <input
												type="submit" value="${language_button_en}" class="nav-link" />
										</form>
									</li>
									<li class="nav-item">
										<form action="mainPage" method="post">
											<input type="hidden" name="command" value="locale_change">
											<input type="hidden" name="local" value="ru"> <input
												type="hidden" name="address" value="${address}"> <input
												type="submit" value="${language_button_ru}" class="nav-link" />
										</form>
									</li>

								</ul></li>
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

		<div class="view-account">

			<div class="side-bar">

				<div class="user-info">
					<img class="img-profile img-circle img-responsive center-block"
						src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="">
					<div class="my_table_title">
						<c:out value="${name}"></c:out>

					</div>


				</div>
				<nav class="side-menu">
					<ul class="nav">

						<li>
							<form action="mainPage" method="post">
								<input type="hidden" name="command" value="welcome_new_user" />
								<input type="submit" value="${client_dp_profile}"
									class="nonactive" />
							</form>
						</li>

						<li>
							<form action="mainPage" method="post">
								<input type="hidden" name="command" value="BOOKING_PAGE" /> <input
									type="submit" value="${client_dp_book}" class="nonactive" />
							</form>
						</li>

						<li>
							<form action="mainPage" method="post">
								<input type="hidden" name="command" value="BOOKING_HISTORY" />
								<input type="submit" value="${client_dp_history}" class="active" />
							</form>
						</li>

						<li>
							<form action="mainPage" method="post">
								<input type="hidden" name="command" value="EXIT_TO_MAIN_PAGE" />
								<input type="submit" value="${client_dp_exit}" class="nonactive" />
							</form>
						</li>

					</ul>
				</nav>
			</div>

		</div>
		<div class="view-account_big">
			<div class="row">
				<div class="my_table_title">${booking_history_title}</div>

				<div class="my_table_booking">


					<table border="2">
						<tr>
							<th>${booking_history_number}</th>
							<th>${booking_history_startDate}</th>
							<th>${booking_history_endDate}</th>
							<th>${booking_resultlist_room}</th>
							<th>${booking_history_category}</th>
							<th>${booking_resultlist_peoplenumber}</th>
							<th>${booking_history_childen}</th>
							<th>${booking_resultlist_full}</th>
							<th>${booking_history_prepayment}</th>
							<th>${booking_history_prepayment_status}</th>

						</tr>

						<form action="mainPage" method="post">
							<input type="hidden" name="command" value="PREPAIMENT_PAID" />
							<c:forEach items="${booking_history}" var="item" varStatus="loop">
								<tr>
									<th width="10%" align="center">${item.bookingId}</th>
									<th width="10%" align="center">${item.startDate}</th>
									<th width="10%" align="center">${item.endDate}</th>
									<th width="10%" align="center">${item.hotelRoomNumber}</th>
									<th width="10%" align="center">${item.roomCategoryName}</th>
									<th width="10%" align="center">${item.peopleNumberInRoom}</th>
									<th width="10%" align="center">${item.childrenNumber}</th>
									<th width="10%" align="center">${item.fullPrice}</th>
									<th width="10%" align="center">${item.prepayment}</th>
									<th width="10%" align="center"><c:if
											test="${item.isPrepayment == true}">
											<c:out value="${booking_history_prepaiment_paid}"></c:out>
										</c:if> <c:if test="${item.isPrepayment==false}">
											<button type="submit" name="prepayment" class="my_table_booking_submit"
												value="${item.bookingId}">${booking_history_pay}</button>
										</c:if></th>
								</tr>

							</c:forEach>

						</form>
					</table>
				</div>
</body>
</html>