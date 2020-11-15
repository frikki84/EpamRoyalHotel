<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>ERROR Page</title>


<fmt:setLocale value="${sessionScope.local}"></fmt:setLocale>

<fmt:setBundle basename="resources.local" var="loc" scope="session"></fmt:setBundle>

<fmt:message bundle="${loc}" key="error_button" var="error_button" />
<fmt:message bundle="${loc}" key="error_OOPs" var="error_OOPs" />
<fmt:message bundle="${loc}" key="error_wrong" var="error_wrong" />

<!-- Google font -->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:200,400,700"
	rel="stylesheet">

<!-- Custom stlylesheet -->
<link rel="stylesheet" href="/css/style_error.css">

</head>

<body>

	<div id="notfound">
		<div class="notfound">
			<div class="notfound-404">
				<h1>${error_OOPs}</h1>
				<h2>${error_wrong}</h2>
			</div>

			<form action="mainPage" method="post" class="form-group_my">
				<input type="hidden" name="command" value="GO_TO_FIRST_PAGE" />
				<input type="submit" value="${error_button}"/>

			</form>
		</div>
	</div>

</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->

</html>
