<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/security/tags" 	prefix="security"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<title>Health check records without KB</title>
</head>
<body>
	<div class="generic-container1">
		<div class="panel panel-default1">
			<!-- Default panel contents -->
			
			<div class="row" style="background-color: <%=System.getProperty("header_background") %>;">
		  	<div class="col-md-4" style="font-size:20;font-family: sans-serif;"><strong>Health check Issue /Warnings without KB</strong></div>
		  	
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
						<div class="col-md-1">
							<label><strong>Date</strong></label>
						</div>
						<div class="col-md-1">
							<label><strong>Status</strong></label>
						</div>
						<div class="col-md-2">
							<label><strong>Check By</strong></label>
						</div>
						<div class="col-md-3">Comments</div>
						<div class="col-md-1">&nbsp;</div>
						<div class="col-md-1">&nbsp;</div>
					</div>
				</div>

			</div>

			<c:forEach items="${list}" var="hc" varStatus="counter">
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
						<div class="col-md-1">
							<label><strong><fmt:formatDate pattern="dd-MM-yyyy" value="${hc.entryDate}" /></strong></label>
						</div>
						<div class="col-md-1">
							<c:choose>
								<c:when test="${hc.status==2}">
									<c:set var="statusname" value="Warning" />
								</c:when>
								<c:otherwise>
									<c:set var="statusname" value="Issue" />
								</c:otherwise>
							</c:choose>

							<label><strong>${statusname}</strong></label>
						</div>
						<div class="col-md-2">
							<label><strong>${hc.checkBy.firstName} ${hc.checkBy.lastName}</strong></label>
						</div>
						<div class="col-md-3">
							<label><strong>${hc.comments}</strong></label>
						</div>
						<security:authorize access="hasAnyRole('ADMIN','OPS')">
					        <div class="col-md-1">
					        <a href="<c:url value='/healthcheck/addkb-${hc.id}' />" class="btn btn-success custom-width"><img src="/opsCenter/static/images/Edit.jpg" alt="exit" height="20" width="30" data-toggle="tooltip" data-placement="left" title="Edit"></a>
					        </div>
						</security:authorize>
				</div>

			</c:forEach>
		</div>
						
		<%-- <div class="row">
				<div class="col-md-2" align="right">
				<c:choose>
					<c:when test="${previous < 0}">
						Previous
					</c:when>
					<c:otherwise>
						<a href="/opsCenter/kb/list-${previous}-${maxResult}-${draft}">Previous</a>					
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
							<a href="/opsCenter/kb/list-${page[0]}-${maxResult}-${draft}">${counter.count}&nbsp;</a>
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
							<a href="/opsCenter/kb/list-${next}-${maxResult}-${draft}">Next</a>			
						</c:otherwise>
					</c:choose>
				
				</div>
			</div> --%>
	</div>
</body>
</html>