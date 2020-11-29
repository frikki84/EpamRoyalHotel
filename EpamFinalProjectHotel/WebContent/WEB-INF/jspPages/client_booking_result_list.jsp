<%@ page language="java" contentType="text/html;UTF-8"
	pageEncoding="UTF-8"%>


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


<fmt:message bundle="${loc}" key="booking_resultlist_title"
	var="booking_resultlist_title" />
<fmt:message bundle="${loc}" key="booking_resultlist_room"
	var="booking_resultlist_room" />
<fmt:message bundle="${loc}" key="booking_resultlist_floor"
	var="booking_resultlist_floor" />
<fmt:message bundle="${loc}" key="booking_resultlist_roomcategory"
	var="booking_resultlist_roomcategory" />
<fmt:message bundle="${loc}" key="booking_resultlist_peoplenumber"
	var="booking_resultlist_peoplenumber" />
<fmt:message bundle="${loc}" key="booking_resultlist_price_adult"
	var="booking_resultlist_price" />
<fmt:message bundle="${loc}" key="booking_resultlist_check"
	var="booking_resultlist_check" />
<fmt:message bundle="${loc}" key="booking_resultlist_baby"
	var="booking_resultlist_baby" />
<fmt:message bundle="${loc}" key="booking_resultlist_prepayment"
	var="booking_resultlist_prepayment" />
<fmt:message bundle="${loc}" key="booking_resultlist_full"
	var="booking_resultlist_full" />
<fmt:message bundle="${loc}" key="booking_resultlist_book"
	var="booking_resultlist_book" />
<fmt:message bundle="${loc}" key="booking_resultlist_from_date"
	var="booking_resultlist_from_date" />
<fmt:message bundle="${loc}" key="booking_resultlist_to_date"
	var="booking_resultlist_to_date" />
<fmt:message bundle="${loc}" key="contact_buttom" var="contact_buttom" />
<fmt:message bundle="${loc}" key="home_button" var="home_button" />
<fmt:message bundle="${loc}" key="language_button" var="language_button" />
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
<link rel="stylesheet" href="css/responsive.css">
<link rel="stylesheet" href="css/style.css">
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
											<input type="hidden" name="command" value="BOOKING_PAGE" />
											<input type="submit" value="${client_dp_book}" class="active" />
										</form>
									</li>

									<li>
										<form action="mainPage" method="post">
											<input type="hidden" name="command" value="new_room_booking" />
											<input type="submit" value="${client_dp_history}"
												class="nonactive" />
										</form>
									</li>

									<li>
										<form action="mainPage" method="post">
											<input type="hidden" name="command" value="EXIT_TO_MAIN_PAGE" />
											<input type="submit" value="${client_dp_exit}"
												class="nonactive" />
										</form>
									</li>

								</ul>
							</nav>
						</div>

					</div>
					<div class="view-account_big">
						<div class="row">
							<div class="my_table_title">${booking_resultlist_title}</div>

							<div class="my_table_title">${booking_resultlist_from_date}
								${booking_info.get(0).startDate} ${booking_resultlist_to_date}
								${booking_info.get(0).endDate}</div>

							<!-- <div class="my_booking_table"> -->
							<form action="mainPage" method="post">
								<input type="hidden" name="command" value="new_room_booking" />

								<table border="2" class="my_table_booking">
									<tr>
										<th>${booking_resultlist_room}</th>
										<th>${booking_resultlist_floor}</th>
										<th>${booking_resultlist_roomcategory}</th>
										<th>${booking_resultlist_peoplenumber}</th>
										<th>${booking_resultlist_price}</th>
										<th>${booking_resultlist_baby}</th>
										<th>${booking_resultlist_full}</th>
										<th>${booking_resultlist_prepayment}</th>
										<th></th>

									</tr>
									<c:forEach items="${booking_info}" var="item" varStatus="loop">
										<tr>
											<th width="10%" align="center">${item.room.hotelRoomNumber}</th>
											<th width="10%" align="center">${item.room.floorNumber}</th>
											<th width="20%" align="center">${item.room.roomCategory.roomCategoryName}</th>
											<th width="10%" align="center">${item.room.roomCategory.peopleNumberInRoom}</th>
											<th width="10%" align="center">${item.basicPayment}</th>
											<th width="10%" align="center">${item.babyExpenceSum}</th>
											<th width="10%" align="center">${item.basicPayment + item.babyExpenceSum}</th>
											<th width="10%" align="center">${item.prepaymentSum}</th>
											<th><button type="submit" class="my_table_booking_submit">${booking_resultlist_book}
													<c:set var="booking_info" scope="session" value="${item}" />
												</button></th>

										</tr>
									</c:forEach>
								</table>
							</form>
							<!-- </div> -->
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>

</body>
</html>