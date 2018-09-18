<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Project</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
	<script src="/opsCenter/static/script/comon.js"></script>
</head>

<body>
<security:authorize access="hasAnyRole('OPS')">
		<c:set var="authenticated" value="${true}"/>
	</security:authorize>
	<c:choose>
		<c:when test="${!authenticated}">
		<c:set var="disable" value="true"/>
		</c:when>
	</c:choose>
<div class="row" style="background-color: <%=System.getProperty("header_background") %>;">
<div class="col-md-4" style="font-size: xx-large;font-family: sans-serif;"><strong>${service.name}</strong></div>
<div class="col-md-8">
<span class="floatRight"><a href="/opsCenter/logout" data-toggle="tooltip" data-placement="left" title="Exit"><img src="/opsCenter/static/images/exit.png" alt="exit" height="40" width="40"></a></span>		
				<security:authorize access="hasRole('OPS')">
				<span class="floatRight"><a href="<c:url value='/serviceSla/newsladata'/>" data-toggle="tooltip" data-placement="left" title="Sla_Entry"><img src="/opsCenter/static/images/data_entry.png" height="40" width="50"></a></span>
				</security:authorize>
				<span class="floatRight" style="cursor:pointer"><a class="button" onclick="goBack()" data-toggle="tooltip" data-placement="left" title="Back"><img src="/opsCenter/static/images/back.png" alt="Back"  height="40" width="40"></a></span>
				<span class="floatRight"><a href="/opsCenter/home" data-toggle="tooltip" data-placement="left" title="Home"><img src="/opsCenter/static/images/home1.png" alt="Home" height="40" width="40"></a></span>			  					

</div>
</div>
	
 	<div class="generic-container1">
 	<div class="panel panel-default1">
 	
				

 	<form:form method="POST" modelAttribute="maping" enctype="multipart/form-data" class="form-horizontal">
		<form:input type="hidden" path="id" id="id"/>
		<form:input type="hidden" path="proj" id="proj"/>
		<div class="row">
		&nbsp;
		</div>
 		<div class="row">
			<div class="form-group col-md-12">
			<div class="col-md-3">&nbsp;</div>
				<label class="col-md-1 control-lable" for="comp"><strong>Component Name</strong></label>
				<div class="col-md-3">
					<!--<form:input path="comp" id="comp" class="form-control input-sm" disabled="${disable}"/>-->
					<form:select path="comp" items="${compList}" multiple="false" itemValue="id" itemLabel="name"
								class="form-control input-sm" />
				
				</div>
			</div>
		</div> 
		<div class="row">
			<div class="form-group col-md-12">
			<div class="col-md-3">&nbsp;</div>
				<label class="col-md-1 control-lable" for="description"><strong>Description</strong></label>
				<div class="col-md-3">
					<form:textarea path="description" id="description" class="form-control input-sm" disabled="${disable}"/>
				</div>
			</div>
		</div> 
				
		<security:authorize access="hasAnyRole('OPS')">
		<div class="row">
			<div class="form-actions">
			<div class="col-md-4">&nbsp;</div>
				<c:choose>
					<c:when test="${edit}">
						<input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/relprojcomp/${projId}' />">Cancel</a>
					</c:when>
					<c:otherwise>
						<input type="submit" value="Submit" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/relprojcomp/${projId}' />">Cancel</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		</security:authorize>
	</form:form>
 	</div>
	</div>
</body>

</html>