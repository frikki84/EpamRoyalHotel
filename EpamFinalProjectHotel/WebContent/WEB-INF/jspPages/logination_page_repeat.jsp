<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Logination page repeat</title>

<fmt:setLocale value="${sessionScope.local}" />

<fmt:setBundle basename="resources.local" var="loc" />

<fmt:message bundle="${loc}" key="log_form_title" var="log_form_title" />
<fmt:message bundle="${loc}" key="log_form_user" var="log_form_user" />
<fmt:message bundle="${loc}" key="log_form_password"
	var="log_form_password" />
<fmt:message bundle="${loc}" key="log_form_login" var="log_form_login" />
<fmt:message bundle="${loc}" key="log_form_remember_me"
	var="log_form_remember_me" />
<fmt:message bundle="${loc}" key="log_form_forgot_password"
	var="log_form_forgot_password" />
<fmt:message bundle="${loc}" key="log_form_create_account"
	var="log_form_create_account" />
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
			<div class="container_regist">
				<div class="row">
					<div class="col-md-6 col-md-offset-3 well">
						<h3 class="text-center">${log_form_title}</h3>

						<div class="wrong_password">
							<c:out value="${answerLogination}"></c:out>

						</div>
						<form class="form" action="mainPage" method="post">

							<input type="hidden" name="command" value="user_logination" />

							<div class="col-xs-12">
								<div class="form-group">

									<input type="text" class="form-control"
										placeholder="${log_form_user}" name="login" required />
								</div>
							</div>
							<div class="col-xs-12">
								<div class="form-group">
									<input type="text" class="form-control"
										placeholder="${log_form_password}" name="password" />
								</div>
							</div>

							<div class="text-center col-xs-12">

								<input type="submit" class="my_table_booking_submit"
									value="${log_form_login}" />
							</div>


							<%-- <div class="clearfix">
						<label class="pull-left checkbox-inline"> <input
							type="checkbox">${log_form_remember_me}</label> <a href="#"
							class="pull-right">${log_form_forgot_password}</a>
					</div> --%>

						</form>
						<form action="mainPage" method="post">
							<input type="hidden" name="command"
								value="go_to_registration_page" /> <input type="submit"
								value="${log_form_create_account}" />

						</form>


					</div>
				</div>
			</div>

		</div>
	</section>

</body>

</html>