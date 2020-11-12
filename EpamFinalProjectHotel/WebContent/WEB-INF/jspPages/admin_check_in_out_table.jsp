<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin check out list</title>

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
<fmt:message bundle="${loc}" key="admin_checl_in_out_table_arrivile"
	var="admin_checl_in_out_table_arrivile" />
<fmt:message bundle="${loc}" key="admin_checl_in_out_table_depachure"
	var="admin_checl_in_out_table_depachure" />
<fmt:message bundle="${loc}"
	key="admin_checl_in_out_table_room_category"
	var="admin_checl_in_out_table_room_category" />
<fmt:message bundle="${loc}" key="booking_resultlist_room"
	var="booking_resultlist_room" />
<fmt:message bundle="${loc}" key="admin_check_out_list_date"
	var="admin_check_out_list_date" />



<fmt:message bundle="${loc}" key="admin_check_out_list"
	var="admin_check_out_list" />


<fmt:message bundle="${loc}" key="booking_history_startDate"
	var="booking_history_startDate" />

<fmt:message bundle="${loc}" key="booking_history_endDate"
	var="booking_history_endDate" />




<fmt:message bundle="${loc}" key="admin_check_out_list_submit"
	var="admin_check_out_list_submit" />
<fmt:message bundle="${loc}" key="admin_check_out_list_client_out"
	var="admin_check_out_list_client_out" />



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
									value="${admin_navigate_checkin}" class="nonactive" />
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
									class="active" />
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

				<form action="mainPage" method="post">
					<input type="hidden" name="command" value="admin_cleaner_list" /> 
						<input type="text" name="date" placeholder="${date}" /> <input
						type="submit" value="${admin_check_out_list_date}">

				</form>
				<br />

				<div class="my_table_booking">

					<form action="mainPage" method="post">
						<input type="hidden" name="command"	value="admin_cleaner_list" />
						
						<table border="2">
							<tr>
								<th>${booking_resultlist_room}</th>
								<th>${admin_checl_in_out_table_room_category}</th>
								<th>${admin_checl_in_out_table_arrivile}</th>
								<th>${admin_checl_in_out_table_depachure}</th>
							</tr>

							<c:forEach items="${cleaner_list}" var="item">
								<tr>
									<th width="20%" align="center">${item.roomNumber}</th>
									<th width="40%" align="center">${item.roomCategory}</th>
									<th width="20%" align="center"><c:if
											test="${item.cleanAfterArrival}">"X"</c:if></th>
									<th width="20%" align="center"><c:if
											test="${item.cleanBeforeDepatchure}">"X"</c:if></th>
							</c:forEach>


						</table>
					</form>
				</div>
			</div>
		</div>







	</div>
</body>
</html>