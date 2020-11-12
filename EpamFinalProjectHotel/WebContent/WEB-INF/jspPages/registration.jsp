<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<fmt:setLocale value="${sessionScope.local}" />

<fmt:setBundle basename="resources.local" var="loc" />

<fmt:message bundle="${loc}" key="reg_form_login" var="reg_form_login" />
<fmt:message bundle="${loc}" key="reg_form_password"
	var="reg_form_password" />
<fmt:message bundle="${loc}" key="reg_form_repeat_password"
	var="reg_form_repeat_password" />
<fmt:message bundle="${loc}" key="reg_form_phone" var="reg_form_phone" />
<fmt:message bundle="${loc}" key="reg_form_email" var="reg_form_email" />
<fmt:message bundle="${loc}" key="reg_form_title" var="reg_form_title" />
<fmt:message bundle="${loc}" key="submit" var="submit" />
<fmt:message bundle="${loc}" key="reg_form_check_password" var="reg_form_check_password" />



<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/style.css"/>

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3 well">
				<h3 class="text-center">${reg_form_title}</h3>

				<form class="form" action="mainPage" method="post">

					<input type="hidden" name="command" value="USER_REGISTRATION" />

					<div class="col-xs-12">
						<div class="form-group">

							<input type="text" class="form-control"
								placeholder="${reg_form_login}" name="login" required/>
						</div>
					</div>
					<div class="col-xs-12">
						<div class="form-group">
							<input type="email" class="form-control"
								placeholder="${reg_form_email}" name="email" />
						</div>
					</div>
					<div class="col-xs-12">
						<div class="form-group">
							<input type="text" class="form-control"
								placeholder="${reg_form_phone}" name="phone" />
						</div>
					</div>

					<div class="col-xs-12">
						<div class="form-group">
							<input type="password" class="form-control"
								placeholder="${reg_form_password}" name="password" />
						</div>
					</div>
					<div class="col-xs-12">
						<div class="form-group">
							<input type="password" class="form-control"
								placeholder ="${reg_form_repeat_password}" name="passwordRepeat"/>
						</div>
					</div>
					<div class="text-center col-xs-12">

						<input type="submit" class="btn btn-default" value="${submit}" />
					</div>

				</form>
				<label >
				</label>

				

			</div>
		</div>
	</div>
</body>
</html>