<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.local" var="loc" />

<fmt:message bundle="${loc}" key="client_delete_page_are_you_sure"
	var="client_delete_page_are_you_sure" />
<fmt:message bundle="${loc}" key="client_delete_page_main_text"
	var="client_delete_page_main_text" />
<fmt:message bundle="${loc}" key="client_delete_page_cancel"
	var="client_delete_page_cancel" />
<fmt:message bundle="${loc}" key="client_delete_page_delete"
	var="client_delete_page_delete" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete page</title>

<link rel="stylesheet" href="css/style.css" />
</head>

<body class="text-center">
	<div id="myModal" class="modal fade show" aria-modal="true"
		style="display: block;">
		<div class="modal-dialog modal-confirm">
			<div class="modal-content">
				<div class="modal-header flex-column">

					<h4 class="modal-title w-100">${client_delete_page_are_you_sure}</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
				</div>
				<div class="modal-body">
					<p>${client_delete_page_main_text}</p>
				</div>
				<div class="modal-footer">
				
					<form action="mainPage" method="post">
						<input type="hidden" name = "command" value="welcome_new_user" /> 
						<input type="submit" value="${client_delete_page_cancel}" class="btn btn-secondary">
					</form>
					
					<form action="mainPage" method="post">
						<input type="hidden" name = "command" value="user_was_deleted" /> <input
							type="submit" value="${client_delete_page_delete}" class="btn btn-secondary">
					</form>

				</div>
			</div>
		</div>
	</div>



</body>
</html>