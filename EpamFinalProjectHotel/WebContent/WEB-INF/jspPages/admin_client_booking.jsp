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



<link rel="stylesheet" href="css/Personal_data_style.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" />

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
								<input type="hidden" name="command" value="ADMIN_CHECK_OUT_LIST" />
								<input type="submit" value="${admin_navigate_checkout}"
									class="nonactive" />
							</form>
						</li>

						<li>
							<form action="mainPage" method="post">
								<input type="hidden" name="command" value="admin_cleaner_List" />
								<input type="submit" value="${admin_navigate_check_living_info}"
									class="nonactive" />
							</form>
						</li>

						<li>
							<form action="mainPage" method="post">
								<input type="hidden" name="command" value="ADMIN_GO_TO_PRICES" /> <input
									type="submit" value="${admin_navigate_change_taxes}"
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
				<h2 class="title">${admin_prices_title}</h2>

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
							<input type="hidden" name="command" value="ADMIN_CHECK_IN_CLIENT" />
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


											<button type="submit" name="client_booking"
												value="${item.bookingId}">${admin_client_booking_info}</button>
										</c:if></th>

								</tr>

							</c:forEach>

						</form>
					</table>
				</div>
			</div>
		</div>







	</div>
</body>
</html>