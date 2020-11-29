<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Client personal page</title>

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
<fmt:message bundle="${loc}" key="contact_buttom" var="contact_buttom" />
<fmt:message bundle="${loc}" key="admin_client_pers_info"
	var="admin_client_pers_info" />
<fmt:message bundle="${loc}" key="client_dp_country"
	var="client_dp_country" />
<fmt:message bundle="${loc}" key="client_dp_select_country"
	var="client_dp_select_country" />
<fmt:message bundle="${loc}" key="client_dp_name" var="client_dp_name" />
<fmt:message bundle="${loc}" key="client_dp_surname"
	var="client_dp_surname" />
<fmt:message bundle="${loc}" key="client_dp_third_name"
	var="client_dp_third_name" />
<fmt:message bundle="${loc}" key="client_dp_name_english"
	var="client_dp_name_english" />
<fmt:message bundle="${loc}" key="client_dp_surname_english"
	var="client_dp_surname_english" />
<fmt:message bundle="${loc}" key="client_dp_birth" var="client_dp_birth" />
<fmt:message bundle="${loc}" key="client_dp_passport"
	var="client_dp_passport" />
<fmt:message bundle="${loc}" key="client_dp_passport_id"
	var="client_dp_passport_id" />
<fmt:message bundle="${loc}" key="client_dp_passport_other_info"
	var="client_dp_passport_other_info" />
<fmt:message bundle="${loc}" key="client_dp_contact_info"
	var="client_dp_contact_info" />
<fmt:message bundle="${loc}" key="reg_form_phone" var="reg_form_phone" />
<fmt:message bundle="${loc}" key="reg_form_email" var="reg_form_email" />
<fmt:message bundle="${loc}" key="client_dp_update"
	var="client_dp_update" />
<fmt:message bundle="${loc}" key="client_dp_security"
	var="client_dp_security" />
<fmt:message bundle="${loc}" key="client_dp_old_password"
	var="client_dp_old_password" />
<fmt:message bundle="${loc}" key="client_dp_new_password"
	var="client_dp_new_password" />
<fmt:message bundle="${loc}" key="client_dp_repeat_password"
	var="client_dp_repeat_password" />
<fmt:message bundle="${loc}" key="client_dp_date_placeholder"
	var="client_dp_date_placeholder" />
<fmt:message bundle="${loc}" key="client_dp_delete_user"
	var="client_dp_delete_user" />
<fmt:message bundle="${loc}" key="admin_client_next_step"
	var="admin_client_next_step" />
<fmt:message bundle="${loc}" key="admin_client_guest_info"
	var="admin_client_guest_info" />
<fmt:message bundle="${loc}" key="admin_client_guest_add"
	var="admin_client_guest_add" />
<fmt:message bundle="${loc}" key="admin_payment_page_sum"
	var="admin_payment_page_sum" />
<fmt:message bundle="${loc}" key="admin_payment_page_pay"
	var="admin_payment_page_pay" />
<fmt:message bundle="${loc}" key="admin_client_next_step"
	var="admin_client_next_step" />

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
					<%-- <ul class="nav navbar-nav menu_nav ml-auto">
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
					</ul> --%>
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
					<h2 class="my_table_title">${admin_client_pers_info}</h2>

					<div class="row">
						<div class="col-xs-12 col-sm-9">

							<form class="form-horizontal" action="mainPage" method="post">
								<input type="hidden" name="command"
									value="update_client_details" />
								<div class="panel panel-default">

									<div class="panel-body">
										<div class="form-group">
											<label class="col-sm-2 control-label">${client_dp_name}</label>
											<div class="col-sm-10">
												<input type="text" class="form-control"
													value="${userDetail.firstName}" name="firstName">

											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">${client_dp_surname}</label>
											<div class="col-sm-10">
												<input type="text" class="form-control"
													value="${userDetail.secondName}" name="secondName">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">${client_dp_third_name}</label>
											<div class="col-sm-10">
												<input type="text" class="form-control"
													value="${userDetail.thirdName}" name="thirdName">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">${client_dp_name_english}</label>
											<div class="col-sm-10">
												<input type="text" class="form-control"
													value="${userDetail.firstNameEnglish}"
													name="firstNameEnglish">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">${client_dp_surname_english}</label>
											<div class="col-sm-10">
												<input type="text" class="form-control"
													value="${userDetail.secondNameEnglish}"
													name="secondNameEnglish">
											</div>
										</div>


										<!-- works good, don't touch -->
										<div class="form-group">
											<label class="col-sm-2 control-label">${client_dp_birth}</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" name="birthDate"
													value="${userDetail.birthDate}" />

											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label">${client_dp_passport}</label>
											<div class="col-sm-10">
												<input type="text" class="form-control"
													value="${userDetail.passportNumber}" name="passportNumber">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">${client_dp_passport_id}</label>
											<div class="col-sm-10">
												<input type="text" class="form-control"
													value="${userDetail.passportId}" name="passportId">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">${client_dp_passport_other_info}</label>
											<div class="col-sm-10">
												<input type="text" class="form-control"
													value="${userDetail.passportOtherInfo}"
													name="passportOtherInfo">
											</div>
										</div>

										<!-- works good, don't touch -->

										<div class="form-group">
											<label class="col-sm-2 control-label">${client_dp_country}</label>
											<br /> <select name="country" class="col-sm-10">
												<option selected="selected">${userDetail.country.name}</option>
												<c:forEach items="${countryList}" var="country">
													<option value="${country.name}">${country.name}</option>
												</c:forEach>
											</select>
										</div>

										<div class="form-group">
											<div class="col-sm-10">
												<input type="submit" class="my_form_control"
													value="${client_dp_update}">
											</div>
										</div>


									</div>
								</div>
							</form>
							<form action="mainPage" method="post">
							<input type="hidden" name = "command" value="ADMIN_GO_TO_ADDING_NEW_GUESTS"/>
							<div class="form-group">
								<div class="col-sm-10">
									<input type="submit" class="my_form_control"
										value="${admin_client_next_step}"/>
								</div>
							</div>
							</form>

						</div>
					</div>

					<%-- <div class="container_my_row">

						<form class="form-horizontal" action="mainPage" method="post">
							<input type="hidden" name="command" value="admin_add_new_guest" />
							<c:if test="${guest_number > 0}">

								<c:forEach var="i" begin="1" end="${guest_number}">
									<div class="guest_data">

										<h2 class="my_table_title">${admin_client_guest_info}</h2>

										<div class="panel panel-default">

											<div class="panel-body">
												<div class="form-group">
													<label class="col-sm-2 control-label">${client_dp_name}</label>
													<div class="col-sm-10">
														<input type="text" class="form-control" name="firstName">

													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label">${client_dp_surname}</label>
													<div class="col-sm-10">
														<input type="text" class="form-control" name="secondName">
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label">${client_dp_third_name}</label>
													<div class="col-sm-10">
														<input type="text" class="form-control" name="thirdName">
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label">${client_dp_name_english}</label>
													<div class="col-sm-10">
														<input type="text" class="form-control"
															name="firstNameEnglish">
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label">${client_dp_surname_english}</label>
													<div class="col-sm-10">
														<input type="text" class="form-control"
															name="secondNameEnglish">
													</div>
												</div>


												<!-- works good, don't touch -->
												<div class="form-group">
													<label class="col-sm-2 control-label">${client_dp_birth}</label>
													<div class="col-sm-10">
														<input type="text" class="form-control" name="birthDate" />

													</div>
												</div>

												<div class="form-group">
													<label class="col-sm-2 control-label">${client_dp_passport}</label>
													<div class="col-sm-10">
														<input type="text" class="form-control"
															name="passportNumber" />
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label">${client_dp_passport_id}</label>
													<div class="col-sm-10">
														<input type="text" class="form-control" name="passportId">
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label">${client_dp_passport_other_info}</label>
													<div class="col-sm-10">
														<input type="text" class="form-control"
															name="passportOtherInfo">
													</div>
												</div>

												<!-- works good, don't touch -->

												<div class="form-group">
													<label class="col-sm-2 control-label">${client_dp_country}</label>
													<br /> <select name="country" class="col-sm-10">

														<c:forEach items="${countryList}" var="country">
															<option value="${country.name}">${country.name}</option>
														</c:forEach>
													</select>
												</div>



												<div class="form-group">
													<label class="col-sm-2 control-label">${reg_form_phone}</label>
													<div class="col-sm-10">
														<input type="text" class="form-control" name="phone">
													</div>
												</div>

												<div class="form-group">
													<label class="col-sm-2 control-label">${reg_form_email}</label>
													<div class="col-sm-10">
														<input type="text" class="form-control" name="email">
													</div>
												</div>
											</div>
										</div>

									</div>

								</c:forEach>
							</c:if> --%>

					<%-- <div class="form-group">
								<div class="col-sm-10">
									<input type="submit" class="my_form_control"
										value="${admin_client_guest_add}" />
								</div>
							</div>

						</form>

					</div>
					<jsp:include page="admin_client_suum_to_pay.jsp"></jsp:include>
				</div> --%>
					<%-- 
				<form action="mainPage" method="post" class="my_table_booking">
					<input type="hidden" name="command"
						value="ADMIN_CLIENT_WAS_CHECKED_IN" />
					<table border="2">
						<tr>

							<th width="30%">${admin_payment_page_sum}</th>
							<th width="30%">${sum_to_pay}</th>
							<th width="30%"><input type="submit"
								value="${admin_payment_page_pay}"
								class="my_table_booking_submit" /></th>
						</tr>
					</table>
				</form> --%>


					<br />


				</div>
			</div>
	</section>

</body>
</html>