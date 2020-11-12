<%@ page language="java" contentType="text/html;UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.local}" />

<fmt:setBundle basename="resources.local" var="loc" />

<fmt:message bundle="${loc}" key="booking_details_title"
	var="booking_details_title" />
<fmt:message bundle="${loc}" key="booking_resultlist_room"
	var="booking_resultlist_room" />
<fmt:message bundle="${loc}" key="booking_resultlist_floor"
	var="booking_resultlist_floor" />
<fmt:message bundle="${loc}" key="booking_resultlist_roomcategory"
	var="booking_resultlist_roomcategory" />
<fmt:message bundle="${loc}" key="booking_resultlist_peoplenumber"
	var="booking_resultlist_peoplenumber" />
<fmt:message bundle="${loc}" key="booking_resultlist_price"
	var="booking_resultlist_price" />
<fmt:message bundle="${loc}" key="booking_details_baby"
	var="booking_details_baby" />
<fmt:message bundle="${loc}" key="booking_details_sum"
	var="booking_details_sum" />
<fmt:message bundle="${loc}" key="booking_details_prepayment"
	var="booking_details_prepayment" />
<fmt:message bundle="${loc}" key="booking_details_start"
	var="booking_details_start" />
<fmt:message bundle="${loc}" key="booking_details_end"
	var="booking_details_end" />
<fmt:message bundle="${loc}" key="booking_details_submit"
	var="booking_details_submit" />	
	


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Booking Details</title>
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
		<div class="row">
		<div class="my_table_title">${booking_details_title}</div>
			
			<div class="my_table_booking">
				
				
					
					<table border="2">

						<tr>
							<th>${booking_details_start}</th>
							<th><c:out value="${booking_room.startDate}"/></th>
							
						</tr>
												<tr>
							<th>${booking_details_end}</th>
							<th><c:out value="${booking_room.endDate}"/></th>
							
						</tr>
						<tr>
							<th>${booking_resultlist_room}</th>
							<th><c:out value="${booking_room.room.hotelRoomNumber}"/></th>
							
						</tr>
						<tr>
							<th>${booking_resultlist_floor}</th>
							<th><c:out value="${booking_room.room.floorNumber}"/></th>
						</tr>
						<tr>
							<th>${booking_resultlist_roomcategory}</th>
							<th> <c:out value="${booking_room.room.roomCategory.roomCategoryName}"/></th>
						</tr>
						<tr>
							<th>${booking_resultlist_peoplenumber}</th>
							<th> <c:out value="${booking_room.room.roomCategory.peopleNumberInRoom}"/></th>
						</tr>
						<tr>
							<th>${booking_resultlist_price}</th>
							<th> <c:out value="${booking_price}"/> </th>
						</tr>
						<tr>
							<th>${booking_details_baby}</th>
							<th> <c:out value="${baby}"/> </th>
						</tr>
						<tr>
							<th >${booking_details_sum}</th>
							<th> <c:out value="${baby + booking_price}"/> </th>
						</tr>
						<tr>
							<th>${booking_details_prepayment}</th>
							<th> <c:out value="${prepayment}"/> </th>
						</tr>
							
					</table>
					<br/>
					
					<form class="form-group_my " action="mainPage" method="post" >

					<input type="hidden" name="command" value="BOOKING_History" />
					<input type="submit" value="${booking_details_submit}">
				</form>
			</div>
		</div>

	</div>

</body>
</html>