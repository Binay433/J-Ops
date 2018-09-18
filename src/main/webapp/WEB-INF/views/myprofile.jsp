<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration Form</title>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="/opsCenter/static/script/comon.js"></script>
</head>

<body>

	<div class="generic-container1">
		<div class="panel panel-default1">
			<div class="row"
				style="background-color: <%=System.getProperty("header_background")%>;">
				<div class="col-md-4"
					style="font-size: xx-large; font-family: sans-serif;">
					<strong>${user.firstName}&nbsp;${user.lastName}</strong>
				</div>

				<div class="col-md-8">
					<security:authorize access="isAuthenticated()">
						<font color="red">${accessMessage}</font>
						<span class="floatRight"><a href="/opsCenter/logout"
							data-toggle="tooltip" data-placement="left" title="Exit"><img
								src="/opsCenter/static/images/exit.png" alt="exit" height="40"
								width="40"></a></span>
						<security:authorize access="hasRole('ADMIN')">
						</security:authorize>
						<span class="floatRight" style="cursor: pointer"><a
							class="button" onclick="goBack()" data-toggle="tooltip"
							data-placement="left" title="Back"><img
								src="/opsCenter/static/images/back.png" alt="Back" height="40"
								width="40"></a></span>
						<span class="floatRight"><a href="/opsCenter/home"
							data-toggle="tooltip" data-placement="left" title="Home"><img
								src="/opsCenter/static/images/home1.png" alt="Home" height="40"
								width="40"></a></span>
					</security:authorize>
				</div>
			</div>
		</div>
		<c:if test="${msgColor!=null}">
		
		<div class="row">
		<div class="col-md-1">&nbsp;</div>
		<div class="col-md-9" style="background-color: ${msgColor}">
			<p class="text-uppercase" >${message}<br></p>
			</div>
			<div class="col-md-1">&nbsp;</div>
		</div>
		</c:if>
		<form:form method="POST" modelAttribute="user" class="form-horizontal"
			action="/opsCenter/users/profile-${user.id}">
			<form:input type="hidden" path="id" id="id" />
			<div class="row">&nbsp;</div>
			<div class="row">
				<div class="form-group col-md-12">
					<div class="col-md-3">&nbsp;</div>
					<label class="col-md-1" for="firstName">First Name</label>
					<div class="col-md-3">
						<form:input type="text" path="firstName" id="firstName"
							class="form-control input-sm" readonly="true" />
						<div class="has-error">
							<form:errors path="firstName" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<div class="col-md-3">&nbsp;</div>
					<label class="col-md-1" for="lastName">Last Name</label>
					<div class="col-md-3">
						<form:input type="text" path="lastName" id="lastName"
							class="form-control input-sm" readonly="true" />
						<div class="has-error">
							<form:errors path="lastName" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<div class="col-md-3">&nbsp;</div>
					<label class="col-md-1" for="loginId">Login ID</label>
					<div class="col-md-3">
						<form:input type="text" path="loginId" id="loginId"
							class="form-control input-sm" readonly="true" />
						<div class="has-error">
							<form:errors path="loginId" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<div class="col-md-3">&nbsp;</div>
					<label class="col-md-1" for="email">Email</label>
					<div class="col-md-3">
						<form:input type="text" path="email" id="email"
							class="form-control input-sm" readonly="true" />
						<div class="has-error">
							<form:errors path="email" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<div class="col-md-3">&nbsp;</div>
					<label class="col-md-1" for="password">Enter Existing
						Password</label>
					<div class="col-md-3">
						<form:input type="password" path="existingPassword"
							id="existingPassword" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="existingPassword" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<div class="col-md-3">&nbsp;</div>
					<label class="col-md-1" for="password">New Password</label>
					<div class="col-md-3">
						<form:input type="password" path="newPassword" id="newPassword"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="password" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			<%-- 		<div class="row">
			<div class="form-group col-md-12">
			<div class="col-md-3">&nbsp;</div>
				<label class="col-md-1" for="email">Service points</label>
				<div class="col-md-3">
					<form:input type="text" path="servicepoint" id="servicepoint" size="10" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="servicepoint" class="help-inline"/>
					</div>
				</div>
			</div>
		</div> --%>
			<c:if test="${msgColor=='#e9fce9'}">
			<div class="row">
				<div class="form-actions">
					<div class="col-md-4">&nbsp;</div>
				<a class="btn btn-primary btn-sm" href="<c:url value='/home' />">Home</a>
				</div>
			</div>
			</c:if>
			<c:if test="${msgColor=='#ffeee6' or msgColor==null}">
			<div class="row">
				<div class="form-actions">
					<div class="col-md-4">&nbsp;</div>
					<input type="submit" value="Update" class="btn btn-primary btn-sm" />
					or <a href="<c:url value='/home' />">Cancel</a>
				</div>
			</div>
			</c:if>
		</form:form>
	</div>
</body>
</html>