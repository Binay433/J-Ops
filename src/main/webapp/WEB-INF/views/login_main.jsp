<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <script src="/opsCenter/static/script/comon.js"></script>	
<!-- <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />-->
</head>

<body>
	<div class="login-left-block">
		<img src="/opsCenter/static/images/Jops.jpg" height="100%"
			width="100%" />

	</div>
	<div class="login-right-block">
		<div class="row j_ops">
			<span class="floatRight"><strong>J-OPS</strong></span>				
		</div>
		<div class="row version_block" >
		<!-- <span class="floatRight">Version - ${version}</span>-->
		<span class="floatRight">Version - <%= System.getProperty("appVersion")%></span>
		</div>
		<div class="login_wrapper login_wrapper-m">
		<div class="my_block-m-6 my_block-6">
			<c:url var="loginUrl" value="/login" />
			<form action="${loginUrl}" method="post">
				<c:if test="${param.error != null}">
					<div class="alert alert-danger">
						<p>Invalid username and password.</p>
					</div>
				</c:if>
				<div class="input-group input-sm-my">
					<label class="input-group-addon" for="username"><i
						class="fa fa-user"></i></label> 
						<input type="text" class="form-control"
						id="username" name="loginId" placeholder="Enter Username" required>
				</div>
				<div class="input-group input-sm-my">
					<label class="input-group-addon" for="password"><i
						class="fa fa-lock"></i></label> <input type="password"
						class="form-control" id="password" name="password"
						placeholder="Enter Password" required>
				</div>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<div class="button_block">
					<div class="form-actions">
						<input type="submit" class="btn btn-primary_my" value="Log in">
					</div>
				</div>
			</form>
		</div>
		</div>
	</div>
</body>
</html>