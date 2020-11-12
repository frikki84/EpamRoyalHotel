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
									type="submit" value="${client_dp_book}" class="active" />
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
								<input type="submit" value="${client_dp_exit}" class="nonactive" />
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




				<div class="my_table_booking">
					<form action="mainPage" method="post">
						<input type="hidden" name="command" value="new_room_booking" />
						
							<table border="2">
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

								<%-- <c:set var="booking_info" scope="session" value="${booking_info}" /> --%>



								<tr>
								<c:forEach items="${booking_info}" var="item" varStatus="loop">
<%-- 									<th><input type="radio" name="booking"
										value="${loop.index}" /></th> --%>
									<th width="10%" align="center">${item.room.hotelRoomNumber}</th>
									<th width="10%" align="center">${item.room.floorNumber}</th>
									<th width="20%" align="center">${item.room.roomCategory.roomCategoryName}</th>
									<th width="10%" align="center">${item.room.roomCategory.peopleNumberInRoom}</th>
									<th width="10%" align="center">${item.basicPayment}</th>
									<th width="10%" align="center">${item.babyExpenceSum}</th>
									<th width="10%" align="center">${item.basicPayment + item.babyExpenceSum}</th>
									<th width="10%" align="center">${item.prepaymentSum}</th>
									<th><button type="submit" >${booking_resultlist_book}
											<c:set var="booking_info" scope="session" value="${item}" />
											</button></th>
											
								</tr>
										</c:forEach>
							</table>


				




						<%-- 	<input type="submit" value="${booking_resultlist_book}" />
 --%>
					</form>


				</div>





			</div>
		</div>

	</div>


</body>
</html>