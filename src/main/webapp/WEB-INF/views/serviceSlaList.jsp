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
<title>OPS Center</title>
</head>
<body>
	<div class="generic-container1">
		<div class="panel panel-default">
			<!-- Default panel contents -->			
			<div class="row my_brown">	
				<div class="column_heading">
					<div class="col-md-1">&nbsp;</div>
					<div class="col-md-1">
						<label>Name</label>
					</div>
					<div class="col-md-1">
						<label>Service</label>
					</div>
					<div class="col-md-1">
						<label>Service Owner</label>
					</div>
					<div class="col-md-2">
						<label>SLA Brief</label>
					</div>
					
				</div>
				<span class="floatRight"><a href="/opsCenter/logout" data-toggle="tooltip" data-placement="left" title="Exit"><img src="/opsCenter/static/images/exit.png" alt="exit" height="40" width="40"></a></span> 
				<security:authorize access="hasRole('ADMIN')">
				<span class="floatRight"><a href="<c:url value='/serviceSla/newservicesla'/>" data-toggle="tooltip" data-placement="left" title="Add New"><img src="/opsCenter/static/images/add.png" alt="add" height="40" width="40"></a></span>
				</security:authorize> 
				<security:authorize access="hasRole('OPS')">
						<span class="floatRight"><a href="<c:url value='/serviceSla/newsladata'/>" data-toggle="tooltip" data-placement="left" title="SLA-Entry">SLA-Entry</a></span>
				</security:authorize>
				<span class="floatRight" style="cursor:pointer"><a class="button" onclick="goBack()" data-toggle="tooltip" data-placement="left" title="Back"><img src="/opsCenter/static/images/back.png" alt="Back"  height="40" width="40"></a></span>
				<span class="floatRight"><a href="/opsCenter/home" data-toggle="tooltip" data-placement="left" title="Home"><img src="/opsCenter/static/images/home1.png" alt="Home" height="40" width="40"></a></span>
			</div>
			<c:forEach items="${serviceSlaList}" var="sla" varStatus="counter">
				<c:choose>
					<c:when test="${counter.count%2==0}">
						<c:set var="background" value='<%=System.getProperty("row_even") %>'/>
					</c:when>
					<c:otherwise>
						<c:set var="background" value='<%=System.getProperty("row_odd") %>'/>
					</c:otherwise>
				</c:choose>
				<div class="row" style="background-color:${background}">
					<div class="col-md-1">&nbsp;</div>
				</div>
				<div class="row" style="background-color:${background}">
					<div class="col-md-1">&nbsp;</div>
					<security:authorize access="hasRole('ADMIN')">
						<c:set var="authenticated" value="${true}" />
					</security:authorize>
					<c:choose>
						<c:when test="${authenticated}">
							<div class="col-md-1">
								<a href="<c:url value='/serviceSla/edit-sla-${sla.id}' />">${sla.name}</a>
							</div>
						</c:when>
						<c:otherwise>
							<div class="col-md-1" align="center">${sla.name}</div>
						</c:otherwise>
					</c:choose>
					<div class="col-md-1">${sla.serviceModel.name}</div>
					<div class="col-md-1">${sla.serviceModel.serviceOwner}</div>

					<div class="col-md-2">${sla.description}</div>
						
					<c:choose>
						<c:when test="${sla.docId!=null}">
							<div class="col-md-1">
								<a href="<c:url value='/download-document-${sla.docId}' />">Reference Doc</a>
							</div>
						</c:when>
						<c:otherwise>
							<div class="col-md-1">&nbsp;</div>
						</c:otherwise>
					</c:choose>
					<security:authorize access="hasRole('ADMIN')">
						<div class="col-md-2">
						<a href="javascript:confirmDelete('/opsCenter/serviceSla/delete-sla-${sla.id}')"class="btn btn-danger custom-width"><img src="/opsCenter/static/images/delete.png" alt="exit" height="20" width="30" data-toggle="tooltip" data-placement="left" title="Delete"></a>
						</div>
					</security:authorize>
				</div>
				<div class="row" style="background-color:${background}">
		  	<div class="col-md-1" >&nbsp;</div>
		  	</div>
			</c:forEach>
			<div class="row">
				<div class="col-md-2" align="right">
				<c:choose>
					<c:when test="${previous < 0}">
						Previous
					</c:when>
					<c:otherwise>
						<a href="/opsCenter/serviceSla/list-${previous}-${maxResult}">Previous</a>					
					</c:otherwise>
				</c:choose>

				
				</div>
				<div class="col-md-9" align="center">
				<c:forEach items="${pages}" var="page" varStatus="counter">
					<c:choose>
						<c:when test="${page[0]==offset}">
							${counter.count}
						</c:when>
						<c:otherwise>
							<a href="/opsCenter/serviceSla/list-${page[0]}-${maxResult}">${counter.count}&nbsp;</a>
						</c:otherwise>
					</c:choose>

				</c:forEach>
				</div>
				<div class="col-md-1" align="left">
					<c:choose>
						<c:when test="${next==0}">
							Next
						</c:when>
						<c:otherwise>
							<a href="/opsCenter/serviceSla/list-${next}-${maxResult}">Next</a>			
						</c:otherwise>
					</c:choose>
				
				</div>
			</div>
		</div>
	</div>
</body>
</html>