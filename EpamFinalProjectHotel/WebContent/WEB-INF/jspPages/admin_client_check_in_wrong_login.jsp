<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Client check-in</title>

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
			<h2 class="title">${wrong_login}</h2>
			<br />
			<h4 class="title">${check_in_title}</h4>
			<div class="row">
				<div class="col-xs-12 col-sm-9">


					<div class="panel panel-default">

						<div class="panel-heading">
							<h5 class="panel-title">${client_dp_user_info}</h5>
						</div>
						<div class="panel-body">
							<div class="form-group">
								<form class="form-horizontal" action="mainPage" method="post">
									<input type="hidden" name="command" value="admin_user_booking" />
									<input type="text" name="user_info" /> <br /> <input
										type="submit" class="form-control" value="${check_in_check}" />
								</form>
							</div>
						</div>
						<br />
						<div class="panel-body">
							<div class="form-group">
								<form class="form-horizontal" action="mainPage" method="post">
									<input type="submit" class="form-control"
										value="${check_in_new_client}" />
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>



		</div>



	</div>
</body>
</html>