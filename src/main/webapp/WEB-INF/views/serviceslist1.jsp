<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<title>OPS Center</title>
</head>
<body>
	<div class="generic-container1">
		<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="lead" style="font-family:serif"><strong>List of Services </strong></span>
		  	<div class="row">
		  	<div class="col-md-5"><font size="4" color="red">${error}</font></div>
		  	</div>
			  	<security:authorize access="isAuthenticated()">
			  		<font color="red">${accessMessage}</font>
					<span class="well floatRight"><a href="/opsCenter/logout">Logout</a></span>
					<security:authorize access="hasRole('ADMIN')">
					<span class="well floatRight"><a href="<c:url value='/services/newservice'/>">Add New</a></span>
					</security:authorize>
			  		<span class="well floatRight"><a href="/opsCenter/home">Home</a></span>
	   			</security:authorize>
		  	</div>
		  	<div class="row">
		  	<div class="column_heading">
		  		<div class="col-md-1"><label>Account</label></div>
		  		<div class="col-md-1"><label>Service</label></div>
		  		<div class="col-md-1"><label>Owner</label></div>
		  		<div class="col-md-2"><label>Support Accountable</label></div>
		  		<div class="col-md-1"><label>Start Time</label></div>
		  		<div class="col-md-1"><label>End Time</label></div>
		  		<div class="col-md-1">&nbsp;</div>
		  		<div class="col-md-1">&nbsp;</div>
		  		<div class="col-md-1">&nbsp;</div>
		  		<div class="col-md-1">&nbsp;</div>
		  		<div class="col-md-1">&nbsp;</div>
		  		</div>
		  	</div>
		  	<c:forEach items="${services}" var="service" varStatus="counter">
		 		<c:choose>
			    <c:when test="${counter.count%2==0}">
			        	<c:set var="background" value="#ffffff"/>
			    </c:when>
			    <c:otherwise>
			        <c:set var="background" value="#45debc"/>
			    </c:otherwise>
				</c:choose>
				<div class="row" style="background-color:${background}">
		  	<div class="col-md-1">&nbsp;</div>
		  	</div>
		  	<div class="row" style="background-color:${background}">
		  		<div class="col-md-1">${service.account.name}</div>
				<security:authorize access="hasRole('ADMIN')">
						<c:set var="authenticated" value="${true}"/>
		  		</security:authorize>
		  		<c:choose>
			    <c:when test="${authenticated}">
			        <div class="col-md-1">
			        <a href="<c:url value='/services/edit-service-${service.id}' />">${service.name}</a>
			        </div>
			    </c:when>
			    <c:otherwise>
			        <div class="col-md-1">${service.name}</div>
			    </c:otherwise>
				</c:choose>
			  	<div class="col-md-1">${service.serviceOwner}</div>
			  	<div class="col-md-2">${service.supportBy}</div>
			  	<div class="col-md-1">${service.hrsStart}</div>
			  	<div class="col-md-1">${service.hrsEnd}</div>
			  	<div class="col-md-1"><a href="<c:url value='/compservices/listbyservice-${service.id}' />">Components</a></div>
			  	<div class="col-md-1"><a href="<c:url value='/healthcheck/listbyservice-${service.id}' />">Health Check</a></div>
			  	<div class="col-md-1"><a href="<c:url value='/serviceSla/listbyservice-${service.id}' />">SLA</a></div>
			  	<c:choose>
			    <c:when test="${service.docId!=null}">
			        <div class="col-md-1">
			       <a href="<c:url value='/download-document-${service.docId}' />">Ref Doc</a>
			        </div>
			    </c:when>
			    <c:otherwise>
			        <div class="col-md-1">&nbsp;</div>
			    </c:otherwise>
				</c:choose>
			  	
			  	
			  	
		  		 <security:authorize access="hasRole('ADMIN')">
							<div class="col-md-1"><a href="<c:url value='/services/delete-service-${service.id}' />" class="btn btn-danger custom-width">delete</a></div>
				</security:authorize>

		  	</div>		  	
		  	</c:forEach>
		  	</div>
		</div>

</body>
</html>