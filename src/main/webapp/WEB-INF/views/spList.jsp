<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <script src="/opsCenter/static/script/comon.js"></script>	
<title>Service Points List</title>
</head>
<body>
	<div class="generic-container1">
		<div class="panel panel-default1">
			<!-- Default panel contents -->
			
			<div class="row" style="background-color: <%=System.getProperty("header_background") %>;">
		  	<div class="col-md-4" style="font-size: xx-large;font-family: sans-serif;"><strong>Service Points List</strong></div>
		  	
		  	<div class="col-md-8">
		  	<span class="floatRight"><a href="/opsCenter/logout" data-toggle="tooltip" data-placement="left" title="Exit"><img
						src="/opsCenter/static/images/exit.png" alt="exit" height="40"
						width="40"></a></span> 
				<span class="floatRight" style="cursor: pointer"><a
					class="button" onclick="goBack()" data-toggle="tooltip" data-placement="left" title="Back"><img
						src="/opsCenter/static/images/back.png" alt="Back" height="40"
						width="40"></a></span> <span class="floatRight"><a
					href="/opsCenter/home" data-toggle="tooltip" data-placement="left" title="Home"><img
						src="/opsCenter/static/images/home1.png" alt="Home" height="40"
						width="40"></a></span>
		  	</div>
		  	</div>
			<div class="row my_brown">
				<div class="row" style="background-color: #F2F3F4;">				
					<div class="column_heading" >
						<div class="col-md-1">
							<label><strong>&nbsp;</strong></label>
						</div>
						<div class="col-md-2">
							<label><strong>Name</strong></label>
						</div>
						<div class="col-md-2">
							<label><strong>Service Points</strong></label>
						</div>
						<div class="col-md-3">
							<label><strong>Updated By</strong></label>
						</div>
					</div>
				</div>

			</div>
			<c:forEach items="${splist}" var="sp" varStatus="counter">
					<c:set var="rowCount" value="${counter.count}"/>
					<c:choose>
						<c:when test="${counter.count%2==0}">
							<c:set var="background" value='<%=System.getProperty("row_even") %>'/>
						</c:when>
						<c:otherwise>
							<c:set var="background" value='<%=System.getProperty("row_odd") %>'/>
						</c:otherwise>
					</c:choose>
				<div class="row" style="background-color:${background}">
						<div class="col-md-1">
							<label><strong>&nbsp;</strong></label>
						</div>
						<div class="col-md-2">
							<label><strong><a href="/opsCenter/SP/list-${sp.user.id}">${sp.user.firstName}&nbsp;${sp.user.lastName}</a></strong></label>
						</div>
						<div class="col-md-2">
							<label><strong>${sp.point}</strong></label>
						</div>
						<div class="col-md-3">
							<label><strong>${sp.updatedby.firstName}&nbsp;${sp.updatedby.lastName}</strong></label>
						</div>
				</div>
			</c:forEach>
		</div>
		
	</div>
</body>
</html>