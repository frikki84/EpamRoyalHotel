<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin price page</title>

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

<fmt:message bundle="${loc}" key="admin_prices_title"
	var="admin_prices_title" />
<fmt:message bundle="${loc}" key="admin_prices_room_category"
	var="admin_prices_room_category" />
<fmt:message bundle="${loc}" key="admin_prices_cost"
	var="admin_prices_cost" />
<fmt:message bundle="${loc}" key="admin_prices_date"
	var="admin_prices_date" />
<fmt:message bundle="${loc}" key="admin_prices_delete"
	var="admin_prices_delete" />
<fmt:message bundle="${loc}" key="admin_prices_add"
	var="admin_prices_add" />
<fmt:message bundle="${loc}" key="admin_prices_select_category"
	var="admin_prices_select_category" />
<fmt:message bundle="${loc}" key="admin_prices_add_date"
	var="admin_prices_add_date" />
<fmt:message bundle="${loc}" key="admin_prices_add_price"
	var="admin_prices_add_price" />




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
									class="nonactive" />
							</form>
						</li>

						<li>
							<form action="mainPage" method="post">
								<input type="hidden" name="command" value="admin_" /> <input
									type="submit" value="${admin_navigate_change_taxes}"
									class="active" />
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
				<form action="mainPage" method="post">
					<input type="hidden" name="command" value="admin_prices_delete" />
					<table border="2">
						<tr>
							<th>${admin_prices_room_category}</th>
							<th>${admin_prices_cost}</th>
							<th>${admin_prices_date }</th>
							<th></th>

						</tr>
						<c:forEach items="${price_list}" var="item">
							<tr>

								<th width="40%">${item.roomCategory.roomCategoryName}</th>
								<th width="20%">${item.pricePerDay}</th>
								<th width="20%">${item.startDate}</th>
								<th width="20%">

									<button type="submit" name="price_code" value="${item.id}">${admin_prices_delete}</button>
								</th>


							</tr>
						</c:forEach>
					</table>
				</form>
				<br />
				<h3 class="title">${admin_prices_add}</h3>
				<h4 class="title">${price_adding_error}</h4>
				<form action="mainPage" method="post">
					<input type="hidden" name="command" value="admin_prices_add" />
					<div class="row">
						<label class="col-sm-2 control-label">${admin_prices_select_category}</label>
						<div class="col-sm-2">
							<select name="roomCategoryId" class="col-sm-10" >
								<%-- <option selected="selected">${admin_prices_select_category}</option> --%>
								<c:forEach items="${room_category_names}" var="roomCat">
									<option value="${roomCat.id}">${roomCat.roomCategoryName}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-sm-2 control-label">${admin_prices_add_date}</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" name="priceStartDate" required >
						</div>
						<label class="col-sm-2 control-label">${admin_prices_add_price}</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" name="price" required >
						</div>

					</div>
					<br /> <input type="submit" value="${admin_prices_add}" />
				</form>

			</div>
		</div>







	</div>
</body>
</html>