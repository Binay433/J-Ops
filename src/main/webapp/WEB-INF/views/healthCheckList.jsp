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
<title>Health Check List</title>
</head>
<body>
	<div class="generic-container1">
		<div class="panel panel-default1">
			<!-- Default panel contents -->
			
			<div class="row" style="background-color: <%=System.getProperty("header_background") %>;">
		  	<div class="col-md-4" style="font-size: xx-large;font-family: sans-serif;"><strong>HEALTH CHECKS</strong></div>
		  	
		  	<div class="col-md-8">
					<span class="floatRight"><a href="/opsCenter/logout" data-toggle="tooltip" data-placement="left" title="Exit"><img
						src="/opsCenter/static/images/exit.png" alt="exit" height="40"
						width="40"></a></span> 	
					<span class="floatRight"><a
					href="/opsCenter/healthcheck/datewise---" data-toggle="tooltip" data-placement="left" title="Report"><img
						src="/opsCenter/static/images/report.jpg" height="40"
						width="40"></a></span>
				<security:authorize access="hasRole('ADMIN')">
					<span class="floatRight"><a
						href="/opsCenter/healthcheck/newcheck" data-toggle="tooltip" data-placement="left" title="Add New"><img
							src="/opsCenter/static/images/add.png" alt="add" height="40"
							width="40"></a></span>
				</security:authorize>
				<span class="floatRight" style="cursor: pointer"><a
					class="button" onclick="goBack()" data-toggle="tooltip" data-placement="left" title="Back"><img
						src="/opsCenter/static/images/back.png" alt="Back" height="40"
						width="40"></a></span>
				<span class="floatRight"><a
					href="/opsCenter/home" data-toggle="tooltip" data-placement="left" title="Home"><img
						src="/opsCenter/static/images/home1.png" alt="Home" height="40"
						width="40"></a></span>
				<security:authorize access="hasAnyRole('ADMIN', 'OPS')">		
				<span class="floatRight"><a href="/opsCenter/healthcheck/withoutkblist" data-toggle="tooltip" data-placement="left" title="Issue/Warnings without kb"><img
						src="/opsCenter/static/images/HealthKB.PNG" alt="WithoutKB" height="40"
						width="40"></a></span> 	
				</security:authorize>	
		  	</div>
		  	</div>
			<div class="row my_brown">
				<div class="row">				
					<div class="column_heading">
						<div class="col-md-1">
							<label><strong>&nbsp;&nbsp;Name</strong></label>
						</div>
						<div class="col-md-1">
							<label><strong>Check Time</strong></label>
						</div>
						<div class="col-md-1">
							<label><strong>Email Enabled</strong></label>
						</div>
						<div class="col-md-2">Mail To</div>
						<div class="col-md-2">Mail CC</div>
						<div class="col-md-1">&nbsp;</div>
						<div class="col-md-1">&nbsp;</div>
					</div>
				</div>

			</div>
			<c:forEach items="${checkList}" var="check" varStatus="counter">
				<c:choose>
					<c:when test="${counter.count%2==0}">
						<c:set var="background"
							value='<%=System.getProperty("row_even")%>' />
					</c:when>
					<c:otherwise>
						<c:set var="background" value='<%=System.getProperty("row_odd")%>' />
					</c:otherwise>
				</c:choose>
				<div class="row" style="background-color:${background}">
					<div class="col-md-1">&nbsp;</div>
				</div>
				<div class="row" style="background-color:${background}">
					<div class="col-md-1">${check.name}</div>
					<div class="col-md-1">${check.checkTime}</div>
					<c:choose>
						<c:when test="${check.emailEnabled}">
							<div class="col-md-1">Yes</div>
						</c:when>
						<c:otherwise>
							<div class="col-md-1">No</div>
						</c:otherwise>
					</c:choose>
					<div class="col-md-2">${check.emailTo}</div>
					<div class="col-md-2">${check.emailCc}</div>
					<c:choose>
						<c:when test="${check.docId!=null}">
							<div class="col-md-1">
								<a href="<c:url value='/download-document-${check.docId}' />">ref-Doc</a>
							</div>
						</c:when>
						<c:otherwise>
							<div class="col-md-1">&nbsp;</div>
						</c:otherwise>
					</c:choose>
					<security:authorize access="hasRole('OPS')">
						<div class="col-md-1">
							<a href="<c:url value='/healthcheck/newtrans-${check.id}' />"
								class="btn btn-success custom-width">Start Check</a>
						</div>
						<div class="col-md-1">
							<a href="<c:url value='/healthcheck/oldrecords-${check.id}--' />"
								class="btn btn-success custom-width">Old Data</a>
						</div>
					</security:authorize>
					<security:authorize access="hasRole('ADMIN')">
						<div class="col-md-1">
							<a
								href="<c:url value='/healthcheck/edit-healthcheck-${check.id}' />"
								class="btn btn-success custom-width"><img src="/opsCenter/static/images/Edit.jpg" alt="exit" height="20" width="30" data-toggle="tooltip" data-placement="left" title="Edit"></a>
						</div>
						<div class="col-md-1">
						<a href="javascript:confirmDelete('/opsCenter/healthcheck/delete-${check.id}')"class="btn btn-danger custom-width"><img src="/opsCenter/static/images/delete.png" alt="exit" height="20" width="30" data-toggle="tooltip" data-placement="left" title="Delete"></a>
							
						</div>
					</security:authorize>
				</div>
				<div class="row" style="background-color:${background}">
					<div class="col-md-1">&nbsp;</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>