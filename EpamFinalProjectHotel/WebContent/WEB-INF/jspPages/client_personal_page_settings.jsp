<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<fmt:setLocale value="${sessionScope.local}" />

<fmt:setBundle basename="resources.local" var="loc" />

<fmt:message bundle="${loc}" key="client_dp_profile"
	var="client_dp_profile" />
<fmt:message bundle="${loc}" key="client_dp_book" var="client_dp_book" />
<fmt:message bundle="${loc}" key="client_dp_history"
	var="client_dp_history" />
<fmt:message bundle="${loc}" key="client_dp_exit" var="client_dp_exit" />
<fmt:message bundle="${loc}" key="client_dp_user_info"
	var="client_dp_user_info" />
<fmt:message bundle="${loc}" key="client_dp_country"
	var="client_dp_country" />
<fmt:message bundle="${loc}" key="client_dp_select_country"
	var="client_dp_select_country" />
<fmt:message bundle="${loc}" key="reg_form_check_password"
	var="reg_form_check_password" />


<link rel="stylesheet" href="css/Personal_data_style.css" />
<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" /> -->

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
								<input type="hidden" value="welcome_new_user" /> <input
									type="submit" value="${client_dp_profile}" class="active" />
							</form>
						</li>

						<li>
							<form action="mainPage" method="post">
								<input type="hidden" value="welcome_new_user" /> <input
									type="submit" value="${client_dp_book}" class="nonactive" />
							</form>
						</li>

						<li>
							<form action="mainPage" method="post">
								<input type="hidden" value="welcome_new_user" /> <input
									type="submit" value="${client_dp_history}" class="nonactive" />
							</form>
						</li>

						<li>
							<form action="mainPage" method="post">
								<input type="hidden" value="welcome_new_user" /> <input
									type="submit" value="${client_dp_exit}" class="nonactive" />
							</form>
						</li>

					</ul>
				</nav>
			</div>

		</div>

		<div class="view-account_big">
			<h2 class="title">${client_dp_profile}</h2>
			<div class="row">
				<div class="col-xs-12 col-sm-9">
					<form class="form-horizontal">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">${client_dp_user_info}</h4>
							</div>
							
							<c:forEach items="${sessionScope.countryList}" var="country">
								<c:out value="${country.name}"></c:out>
								
							</c:forEach>
							<br/>

							<div class="panel-body">
								<div class="form-group">
									<label class="col-sm-2 control-label">${client_dp_country}</label>
									<div class="col-sm-10">

										<select class="form-control">
											<option selected="">${client_dp_select_country}</option>
											<c:forEach items="sessionScope.countryList" var="country">
												<option>${country}</option>
												 <option>Canada</option>
											<option>Denmark</option>
											<option>Estonia</option>
											<option>France</option>
																					</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">Company name</label>
									<div class="col-sm-10">
										<input type="text" class="form-control">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">Position</label>
									<div class="col-sm-10">
										<input type="text" class="form-control">
									</div>
								</div>
							</div>
						</div>

						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">Contact info</h4>
							</div>
							<div class="panel-body">
								<div class="form-group">
									<label class="col-sm-2 control-label">Work number</label>
									<div class="col-sm-10">
										<input type="tel" class="form-control">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">Mobile number</label>
									<div class="col-sm-10">
										<input type="tel" class="form-control">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">E-mail address</label>
									<div class="col-sm-10">
										<input type="email" class="form-control">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">Work address</label>
									<div class="col-sm-10">
										<textarea rows="3" class="form-control"></textarea>
									</div>
								</div>
							</div>
						</div>

						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">Security</h4>
							</div>
							<div class="panel-body">
								<div class="form-group">
									<label class="col-sm-2 control-label">Current password</label>
									<div class="col-sm-10">
										<input type="password" class="form-control">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">New password</label>
									<div class="col-sm-10">
										<input type="password" class="form-control">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-10 col-sm-offset-2">
										<div class="checkbox">
											<input type="checkbox" id="checkbox_1"> <label
												for="checkbox_1">Make this account public</label>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-10 col-sm-offset-2">
										<button type="submit" class="btn btn-primary">Submit</button>
										<button type="reset" class="btn btn-default">Cancel</button>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>



	</div>
</body>
</html>