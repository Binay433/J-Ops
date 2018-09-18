<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <script src="/opsCenter/static/script/comon.js"></script>	
<title>OPS Centre</title>
</head>
<body>
	<div class="generic-container1">
	
	<div class="row" style="background-color: <%=System.getProperty("header_background") %>;">
			  <div class="col-md-4" style="font-size: xx-large;font-family: sans-serif;"><strong>USER LIST</strong></div>
			  	
			  <div class="col-md-8">
			  <security:authorize access="isAuthenticated()">
			  		<font color="red">${accessMessage}</font>
		  	<span class="floatRight"><a href="/opsCenter/logout" data-toggle="tooltip" data-placement="left" title="Exit"><img src="/opsCenter/static/images/exit.png" alt="exit" height="40" width="40"></a></span>
		  		<security:authorize access="hasRole('ADMIN')">
		  	<span class="floatRight"><a href="<c:url value='/users/newuser'/>" data-toggle="tooltip" data-placement="left" title="Add New"><img src="/opsCenter/static/images/add.png" alt="add" height="40" width="40"></a></span>
		  	</security:authorize>
		  	<span class="floatRight" style="cursor:pointer"><a class="button" onclick="goBack()" data-toggle="tooltip" data-placement="left" title="Back"><img src="/opsCenter/static/images/back.png" alt="Back"  height="40" width="40"></a></span>
		  	<span class="floatRight"><a href="/opsCenter/home" data-toggle="tooltip" data-placement="left" title="Home"><img src="/opsCenter/static/images/home1.png" alt="Home" height="40" width="40"></a></span>
		  	</security:authorize>
			  </div>
			  </div>
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="row my_brown">
              <div class="column_heading">
				<div class="col-md-1">
					<label>First Name &nbsp;                       &nbsp;&nbsp;&nbsp;&nbsp;</label>
				</div>
				<div class="col-md-1">
					<label>&nbsp;</label>
				</div>
				<div class="col-md-2">
					<label>Last Name</label>
				</div>
				<div class="col-md-2">
					<label>Email</label>
				</div>
				<div class="col-md-2">
					<label>Login ID</label>
				</div>
				</div>
			

			</div>
			<c:forEach items="${users}" var="user" varStatus="counter">
				<c:choose>
					<c:when test="${counter.count%2==0}">
						<c:set var="background"
							value='<%=System.getProperty("row_even")%>' />
					</c:when>
					<c:otherwise>
						<c:set var="background"
							value='<%=System.getProperty("row_odd")%>' />
					</c:otherwise>
				</c:choose>
				<div class="row" style="background-color:${background}">
					<div class="col-md-1">&nbsp;</div>
				</div>
				<div class="row" style="background-color:${background}">
					<div class="col-md-2"><strong>${user.firstName}</strong></div>
					<div class="col-md-2"><strong>${user.lastName}</strong></div>
					<div class="col-md-2"><strong>${user.email}</strong></div>
					<div class="col-md-1">
						<a href="<c:url value='/users/edit-user-${user.loginId}' />"
							class="btn btn-success custom-width"><img src="/opsCenter/static/images/Edit.jpg" alt="exit" height="20" width="30" data-toggle="tooltip" data-placement="left" title="Edit"></a>
					</div>
					<div class="col-md-1">
					<a href="javascript:confirmDelete('/opsCenter/users/delete-user-${user.id}')"class="btn btn-danger custom-width"><img src="/opsCenter/static/images/delete.png" alt="exit" height="20" width="30" data-toggle="tooltip" data-placement="left" title="Delete"></a>
					</div>
				</div>
				<div class="row" style="background-color:${background}">
					<div class="col-md-1">&nbsp;</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>