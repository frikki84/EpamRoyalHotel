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



<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/style.css" />

</head>
<body>
	<div class="container" >
		<div class="row">
			<div class="col-md-6 col-md-offset-3 well">
				<h3 class="text-center">${log_form_title}</h3>

				<div class="wrong_password">
					<c:out value="${sessionScope.answerLogination}"></c:out>

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

						<input type="submit" class="btn btn-default"
							value="${log_form_login}" />
					</div>


					<%-- <div class="clearfix">
						<label class="pull-left checkbox-inline"> <input
							type="checkbox">${log_form_remember_me}</label> <a href="#"
							class="pull-right">${log_form_forgot_password}</a>
					</div> --%>

				</form>
				<form action="" class="text-center">
					<input type="hidden" name="command" value="go_to_registration_page" />

					<input type="submit" value="${log_form_create_account}" />

				</form>


			</div>
		</div>
	</div>
</body>

</html>