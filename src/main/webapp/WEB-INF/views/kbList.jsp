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
<title>Knowledge Items</title>
</head>
<body>
	<div class="generic-container1">
		<div class="panel panel-default1">
			<!-- Default panel contents -->
			
			<div class="row" style="background-color: <%=System.getProperty("header_background") %>;">
		  	<div class="col-md-4" style="font-size: xx-large;font-family: sans-serif;"><strong>Knowledge Items</strong></div>
		  	
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
							<label><strong>Keywords</strong></label>
						</div>
						<div class="col-md-1">
							<label><strong>Author</strong></label>
						</div>
						<div class="col-md-2">
							<label><strong>Description</strong></label>
						</div>
						<div class="col-md-3">
							<label><strong>Solution</strong></label>
						</div>
						<div class="col-md-1">Status</div>
						<div class="col-md-1">&nbsp;</div>
						<div class="col-md-1">&nbsp;</div>
					</div>
				</div>

			</div>
			<security:authorize access="hasRole('ADMIN')">
			<div class="row">
			<div class="col-md-10" align="right">
			 <a href="/opsCenter/kb/list---${all}">
			 <input type="button" value="${allcaption}" style="background-color: F3F2F4">
			 </a>
			</div>
			</div><br>
			</security:authorize>
			<c:forEach items="${kbList}" var="kb" varStatus="counter">
				<div class="row">
						<div class="col-md-1">
							<label><strong>&nbsp;</strong></label>
						</div>
						<div class="col-md-1">
							<label><strong>${kb.keywords}</strong></label>
						</div>
						<div class="col-md-1">
							<label><strong>${kb.createdby.firstName}&nbsp;${kb.createdby.lastName}</strong></label>
						</div>
						<div class="col-md-2">
							<label><strong>${kb.description}</strong></label>
						</div>
						<div class="col-md-3">
							<c:choose>
								<c:when test="${kb.solution!=null && kb.solution.length() > 300}">
									<label><strong>${kb.solution.substring(0,300)}</strong>	</label>														
								</c:when>
								<c:otherwise>
									<label><strong>${kb.solution}</strong></label>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="col-md-1">
							<c:choose>
								<c:when test="${kb.approved}">
									<label><strong>Approved</strong></label>
								</c:when>
								<c:otherwise>
									<label><strong>Draft</strong></label>
								</c:otherwise>
							</c:choose>
						
						</div>
<%-- 						<security:authorize access="hasRole('ADMIN')"> --%>
					        <div class="col-md-1">
					        <a href="<c:url value='/kb//search-${kb.id}' />" class="btn btn-success custom-width"><img src="/opsCenter/static/images/Edit.jpg" alt="exit" height="20" width="30" data-toggle="tooltip" data-placement="left" title="Edit"></a>
					        </div>
<%-- 						</security:authorize> --%>
						<c:set var="hasAccess" value="0"></c:set>
 						<security:authorize access="hasRole('ADMIN')"> 
							<div class="col-md-1">
							<c:set var="hasAccess" value="1"></c:set>
								<a href="javascript:confirmDelete('/opsCenter/kb/delete-${kb.id}')"class="btn btn-danger custom-width"><img src="/opsCenter/static/images/delete.png" alt="exit" height="20" width="30" data-toggle="tooltip" data-placement="left" title="Delete"></a>
							</div>
						</security:authorize> 
						 <security:authorize access="hasAnyRole('OPS','USER')"> 
						 	<c:choose>
						 	<%-- commented as casting exception <c:when test="${!kb.approved && hasAccess==0} && kb.createdby==currentUser.id">--%>
						 		<c:when test="${!kb.approved && hasAccess==0}">
								<div class="col-md-1">
									<a href="javascript:confirmDelete('/opsCenter/kb/delete-${kb.id}')"class="btn btn-danger custom-width"><img src="/opsCenter/static/images/delete.png" alt="exit" height="20" width="30" data-toggle="tooltip" data-placement="left" title="Delete"></a>
								</div>						 		
						 		</c:when>
						 	</c:choose>
						</security:authorize> 
				</div>
				<hr>
			</c:forEach>
		</div>
						<c:choose>
						<c:when test="${all==1}">
							<c:set var="draft" value="0"></c:set>
						</c:when>
						<c:otherwise>
							<c:set var="draft" value="1"></c:set>
						</c:otherwise>
					</c:choose>
		<div class="row">
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
			</div>
	</div>
</body>
</html>