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
	



	<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Booking list</title>
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
<link rel="stylesheet" href="css/Personal_data_style.css">

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
											<button type="submit" name="prepayment"
												value="${item.bookingId}">${booking_history_pay}</button>
										</c:if></th>
								</tr>

							</c:forEach>

						</form>


						</div>





						</div>
						</div>

						</div>
</body>
</html>