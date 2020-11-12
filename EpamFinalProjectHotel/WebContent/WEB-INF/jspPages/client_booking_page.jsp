<%@ page language="java" contentType="text/html; UTF-8"
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

<fmt:message bundle="${loc}" key="booking_title" var="booking_title" />
<fmt:message bundle="${loc}" key="booking_arravial"
	var="booking_arravial" />
<fmt:message bundle="${loc}" key="booking_depatchure"
	var="booking_depatchure" />
<fmt:message bundle="${loc}" key="booking_date" var="booking_date" />
<fmt:message bundle="${loc}" key="booking_adult" var="booking_adult" />
<fmt:message bundle="${loc}" key="booking_children"
	var="booking_children" />
<fmt:message bundle="${loc}" key="booking_find_roon" var="booking_find_roon" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Booking page</title>
<!-- <link rel="stylesheet" href="vendors/linericon/style.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">
<link rel="stylesheet"
	href="vendors/bootstrap-datepicker/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="vendors/nice-select/css/nice-select.css">
<link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css"> -->

<!-- <link rel="stylesheet" href="css/style.css"> -->
<link rel="stylesheet" href="css/Personal_data_style.css">
<!-- <link rel="stylesheet" href="css/responsive.css"> -->


</head>
<body>
	<div class="container">

		<div class="view-account">

			<div class="side-bar">

				<div class="user-info">
					<img class="img-profile img-circle img-responsive center-block"
						src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="">
					<div class="meta list list-unstyled">
						<c:out value="${name}"></c:out>

					</div>


				</div>
				<nav class="side-menu">
					<ul class="nav">

						<li>
							<form action="mainPage" method="post">
								<input type="hidden" name = "command" value="welcome_new_user" /> <input
									type="submit" value="${client_dp_profile}" class="nonactive" />
							</form>
						</li>

						<li>
							<form action="mainPage" method="post">
								<input type="hidden" name = "command" value="BOOKING_PAGE" /> 
								<input
									type="submit" value="${client_dp_book}" class="active" />
							</form>
						</li>

						<li>
							<form action="mainPage" method="post">
								<input type="hidden" name = "command" value="BOOKING_HISTORY" /> <input
									type="submit" value="${client_dp_history}" class="nonactive" />
							</form>
						</li>

						<li>
							<form action="mainPage" method="post">
								<input type="hidden" name="command" value="EXIT_TO_MAIN_PAGE" /> <input
									type="submit" value="${client_dp_exit}" class="nonactive" />
							</form>
						</li>

					</ul>
				</nav>
			</div>

		</div>
		<div class="view-account_big">

			<div class="hotel_booking_table">

				<div class="col-md-3_my">
					<h2>${booking_title}</h2>
				</div>


				<form action="mainPage" method="post" class="form-group_my">

					<input type="hidden" name="command" value="result_booking_list" />

					<div class="col-md-3_my">${booking_arravial}</div>
					<input type="date" placeholder="${booking_date}"
						class='input-group-addon' name ="startDate"/>

					<div class="col-md-3_my">${booking_depatchure}</div>
					<input type="date" placeholder="${booking_date}"
						class='input-group-addon' name = "endDate"/>

					<div class="col-md-3_my">${booking_adult}</div>
					
					<select class="col-sm-10" name="peopleNumber">
						<c:forEach items="${room_capacity}" var="item">
							<option value="${item}">${item}</option>
						</c:forEach>
					</select>

					<div class="col-md-3_my">${booking_children}</div>

					<select name="childrenNumber" class="col-sm-10">
						<option selected  value="0">0</option>
						<option value="1">1</option>
						<option value="2">2</option>
					</select>
					<br/>
					
					<input type="submit" value="${booking_find_roon}"  /> 

				</form>
			</div>

		</div>
	</div>


</body>
</html>