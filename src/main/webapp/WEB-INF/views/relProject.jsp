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
 	
				

 	<form:form method="POST" modelAttribute="proj" enctype="multipart/form-data" class="form-horizontal">
		<form:input type="hidden" path="id" id="id"/>
		<form:input type="hidden" path="relId" id="relId"/>
		<div class="row">
		&nbsp;
		</div>
 		<div class="row">
			<div class="form-group col-md-12">
			<div class="col-md-3">&nbsp;</div>
				<label class="col-md-1 control-lable" for="name"><strong>Project Name</strong></label>
				<div class="col-md-3">
					<form:input path="name" id="name" class="form-control input-sm" disabled="${disable}"/>
<%-- 					<div class="has-error">
						<form:errors path="account" class="help-inline"/>
					</div> --%>
				</div>
			</div>
		</div> 
		<div class="row">
			<div class="form-group col-md-12">
			<div class="col-md-3">&nbsp;</div>
				<label class="col-md-1 control-lable" for="summary"><strong>Project Summary</strong></label>
				<div class="col-md-3">
					<form:input path="summary" id="summary" class="form-control input-sm" disabled="${disable}"/>
				</div>
			</div>
		</div> 
		

		
		<div class="row">
			<div class="form-group col-md-12">
			<div class="col-md-3">&nbsp;</div>
				<label class="col-md-1 control-lable" for="projManager"><strong>Project Manager</strong></label>
				<div class="col-md-3">
					<form:input path="projManager" id="projManager" class="form-control input-sm" disabled="${disable}"/>
				</div>
			</div>
		</div> 
		<div class="row">
			<div class="form-group col-md-12">
			<div class="col-md-3">&nbsp;</div>
				<label class="col-md-1 control-lable" for="testManager"><strong>Test Manager</strong></label>
				<div class="col-md-3">
					<form:input path="testManager" id="testManager" class="form-control input-sm" disabled="${disable}"/>
				</div>
			</div>
		</div> 
		<div class="row">
			<div class="form-group col-md-12">
			<div class="col-md-3">&nbsp;</div>
				<label class="col-md-1 control-lable" for="rcmLead"><strong>RCM Lead</strong></label>
				<div class="col-md-3">
					<form:input path="rcmLead" id="rcmLead" class="form-control input-sm" disabled="${disable}"/>
				</div>
			</div>
		</div> 
		<div class="row">
			<div class="form-group col-md-12">
			<div class="col-md-3">&nbsp;</div>
				<label class="col-md-1 control-lable" for="rcmleadname"><strong>Status</strong></label>
				<div class="col-md-3">
					<form:input path="status" id="status" class="form-control input-sm" disabled="${disable}"/>
				</div>
			</div>
		</div> 
		
		<security:authorize access="hasAnyRole('OPS')">
		<div class="row">
			<div class="form-actions">
			<div class="col-md-4">&nbsp;</div>
				<c:choose>
					<c:when test="${edit}">
						<input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/relproj/${relId}' />">Cancel</a>
					</c:when>
					<c:otherwise>
						<input type="submit" value="Submit" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/relproj/${relId}' />">Cancel</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		</security:authorize>
	</form:form>
 	</div><br><br><br>
 	<c:choose>
			<c:when test="${edit}">
			<div class="row" style="border: thin;border-color: buttontext;">
			<div class="col-md-3" align="left">&nbsp;</div>
				<div class="col-md-3" align="left">
					<label ><strong>Document List &nbsp;&nbsp;&nbsp;&nbsp;[Click Document name to open]</strong></label>
				</div>
				<div class="col-md-3" align="left">
				<a href="" onclick="addFunction('/opsCenter/relproj/doc/${id}')"><img src="/opsCenter/static/images/addDoc.png"></a>
				</div>
			</div>
			<c:choose>
			<c:when test="${proj.docs.size() > 0}">
							<div class="row" style="border: thin;border-color: buttontext;">
								<div class="col-md-3" align="left">&nbsp;</div>
								<div class="col-md-3" align="left"><B>Document Name</B></div>
								<div class="col-md-3" align="left"><B>Uploaded By	</B></div>
								<div class="col-md-3" align="left"><B>Uploaded Date</B></div>
							</div>
						<c:forEach items="${proj.docs}" var="document" varStatus="counter">
							<div class="row" style="border: thin;border-color: buttontext;">
								<div class="col-md-3" align="left">&nbsp;</div>
								<div class="col-md-3" align="left"><a href="<c:url value='document-${document.id}' />">${document.name}</a></div>
								<div class="col-md-3" align="left">${document.createdBy.firstName}&nbsp;${document.createdBy.lastName}</div>
								<div class="col-md-3" align="left">${document.craetedDate}	</div>
							</div>
							
							
						</c:forEach>
			</c:when>
			</c:choose>
			
			
			
			</c:when>
		</c:choose>
	
 	
	</div>
</body>
<script>
function addFunction(url) {
	myWindow = window.open(url, "add documents", "height=400,width=400")
}
</script>
</html>