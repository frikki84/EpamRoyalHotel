<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Client booking history</title>

<fmt:setLocale value="${sessionScope.local}" />

<fmt:setBundle basename="resources.local" var="loc" />

<fmt:message bundle="${loc}" key="admin_navigate_checkin"
	var="admin_navigate_checkin" />
<fmt:message bundle="${loc}" key="admin_navigate_add_tax"
	var="admin_navigate_add_tax" />
<fmt:message bundle="${loc}" key="admin_navigate_change_taxes"
	var="admin_navigate_change_taxes" />
<fmt:message bundle="${loc}" key="admin_navigate_add_admin"
	var="admin_navigate_add_admin" />
<fmt:message bundle="${loc}" key="admin_navigate_checkout"
	var="admin_navigate_checkout" />
<fmt:message bundle="${loc}" key="admin_navigate_check_living_info"
	var="admin_navigate_check_living_info" />
<fmt:message bundle="${loc}" key="admin_navigate_change_admin_info"
	var="admin_navigate_change_admin_info" />
<fmt:message bundle="${loc}" key="admin_navigate_exit"
	var="admin_navigate_exit" />
<fmt:message bundle="${loc}" key="check_in_title" var="check_in_title" />
<fmt:message bundle="${loc}" key="check_in_check" var="check_in_check" />
<fmt:message bundle="${loc}" key="check_in_new_client"
	var="check_in_new_client" />
<fmt:message bundle="${loc}" key="contact_buttom" var="contact_buttom" />
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

<fmt:message bundle="${loc}" key="admin_client_booking_info"
	var="admin_client_booking_info" />

<fmt:message bundle="${loc}" key="admin_client_booking_status"
	var="admin_client_booking_status" />


<fmt:message bundle="${loc}" key="booking_history_pay"
	var="booking_history_pay" />
	
<fmt:message bundle="${loc}" key="admin_client_go_to_pers_page"
	var="admin_client_go_to_pers_page" /> 
	
<fmt:message bundle="${loc}" key="home_button" var="home_button" />
<fmt:message bundle="${loc}" key="language_button" var="language_button" />
<fmt:message bundle="${loc}" key="language_button_en"
	var="language_button_en" />
<fmt:message bundle="${loc}" key="language_button_ru"
	var="language_button_ru" />
<fmt:message bundle="${loc}" key="contact_buttom" var="contact_buttom" />



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
										<input type="hidden" name="command"
											value="ADMIN_FIND_USER_TO_CHECK_IN" /> <input type="submit"
											value="${admin_navigate_checkin}" class="active" />
									</form>
								</li>

								<%-- 						<li>
							<form action="mainPage" method="post">
								<input type="hidden" name="command" value="" /> <input
									type="submit" value="${admin_navigate_add_tax}"
									class="nonactive" />
							</form>
						</li> --%>

								<li>
									<form action="mainPage" method="post">
										<input type="hidden" name="command"
											value="ADMIN_CHECK_OUT_LIST" /> <input type="submit"
											value="${admin_navigate_checkout}" class="nonactive" />
									</form>
								</li>

								<li>
									<form action="mainPage" method="post">
										<input type="hidden" name="command" value="admin_cleaner_List" />
										<input type="submit"
											value="${admin_navigate_check_living_info}" class="nonactive" />
									</form>
								</li>

								<li>
									<form action="mainPage" method="post">
										<input type="hidden" name="command" value="ADMIN_GO_TO_PRICES" />
										<input type="submit" value="${admin_navigate_change_taxes}"
											class="nonactive" />
									</form>
								</li>

								<%-- 						<li>
							<form action="mainPage" method="post">
								<input type="hidden" name="command" value="EXIT_TO_MAIN_PAGE" />
								<input type="submit" value="${admin_navigate_change_admin_info}"
									class="nonactive" />
							</form>
						</li> --%>

								<%-- 					<li>
							<form action="mainPage" method="post">
								<input type="hidden" name="command" value="EXIT_TO_MAIN_PAGE" />
								<input type="submit" value="${admin_navigate_add_admin}"
									class="nonactive" />
							</form>
						</li> --%>

								<li>
									<form action="mainPage" method="post">
										<input type="hidden" name="command" value="EXIT_TO_MAIN_PAGE" />
										<input type="submit" value="${admin_navigate_exit}"
											class="nonactive" />
									</form>
								</li>



							</ul>
						</nav>
					</div>

				</div>

				<div class="view-account_big">				
					<div class="row">
						<h2 class="my_table_title">${userInfoForGreetings}</h2>
						<div class="my_table_booking">

							<table border="2">
								<tr>
									<th>${booking_history_number}</th>
									<th>${booking_history_startDate}</th>
									<th>${booking_history_endDate}</th>
									<th>${booking_resultlist_room}</th>
									<th>${booking_resultlist_peoplenumber}</th>
									<th>${booking_history_childen}</th>
									<th>${booking_resultlist_full}</th>
									<th>${booking_history_prepayment}</th>
									<th>${admin_client_booking_info}</th>

								</tr>

								<form action="mainPage" method="post">
									<input type="hidden" name="command"
										value="ADMIN_CHECK_IN_CLIENT" />
									<c:forEach items="${booking_list}" var="item">
										<tr>
											<th width="10%" align="center">${item.bookingId}</th>
											<th width="15%" align="center">${item.startDate}</th>
											<th width="15%" align="center">${item.endDate}</th>
											<th width="10%" align="center">${item.hotelRoomNumber}</th>
											<th width="10%" align="center">${item.peopleNumberInRoom}</th>
											<th width="10%" align="center">${item.childrenNumber}</th>
											<th width="10%" align="center">${item.fullPrice}</th>
											<th width="10%" align="center">${item.prepayment}</th>
											<th width="10%" align="center"><c:if
													test="${item.isBookingPaid == true}">
													<c:out value="${admin_client_booking_status}">
													</c:out>
												</c:if> <c:if test="${item.isBookingPaid==false}">
													
													<button type="submit" name="client_booking" class = "my_table_booking_submit"
														value="${item.bookingId}">${admin_client_booking_info}</button>
												</c:if></th>
										</tr>
									</c:forEach>
								</form>
							</table>
						</div>
						
											<div class="my_table_booking">
						<form action="mainPage" method="post">
							<input type="hidden" name="command"
								value="ADMIN_CHECK_IN_CLIENT" /> 
								<input type="submit"
								value="${admin_client_go_to_pers_page}" class="my_table_booking_submit" />
							<div class="my_table_booking"></div>

						</form>
					</div>
						
					</div>
				</div>
			</div>
		</div>
	</section>

</body>
</html>