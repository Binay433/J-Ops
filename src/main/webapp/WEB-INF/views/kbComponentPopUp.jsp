<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>jOps Search</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  	<script src="/opsCenter/static/script/comon.js"></script>	
</head>

<body>


	<div class="generic-container1">
		<div class="panel panel-default1">
	<form:form method="POST" modelAttribute="kbComponent" class="form-horizontal" enctype="multipart/form-data">
	<br>
		<div class="row" style="border: thin;border-color: buttontext;">
			<div class="col-md-3" align="left">
				<label  for="component_keywords"><strong>key Words</strong></label>
			</div>
			<div class="col-md-6">
					<form:input type="text" path="keywords" id="keywords" class="form-control input-sm" readonly="true"/>
					<div class="has-error">
						<form:errors path="keywords" class="help-inline"/>
					</div>
			</div>
		</div>
		<br>
		<div class="row" style="border: thin;border-color: buttontext;">
			<div class="col-md-3" align="left">
				<label  for="component_description"><strong>Description</strong></label>
			</div>
			<div class="col-md-6">
					<form:textarea id="description" class="form-control input-sm" rows="7" path="description" readonly="true"/>
					<div class="has-error">
						<form:errors path="description" class="help-inline"/>
					</div>
			</div>
		</div>
		<br>
		<div class="row" style="border: thin;border-color: buttontext;">
			<div class="col-md-3" align="left">
				<label  for="component_solution"><strong>Suggested Solution</strong></label>
			</div>
			<div class="col-md-6">
					<form:textarea id="solution" class="form-control input-sm" rows="7" path="solution" readonly="true"/>
					<div class="has-error">
						<form:errors path="solution" class="help-inline"/>
					</div>
			</div>
		</div>
		<br>
		<div class="row" style="border: thin;border-color: buttontext;">
			<div class="col-md-3" align="left">
				<label  for="component_solution"><strong>Documents</strong></label>
			</div>
			<div class="col-md-6">
					<TABLE id="dataTable">
					<c:choose>
						<c:when test="${kbComponent.documents.size() > 0}">
						<c:forEach items="${kbComponent.documents}" var="document" varStatus="counter">
							<TR hidden="true">
								<TD><INPUT type="checkbox" name="chk"/></TD>
								<TD><form:input type="file" path="files" id="Upload" title="update" /></TD>
							</TR>
							
							<TR>
								<TD><INPUT type="checkbox" name="chk"/></TD>
								<TD><a href="<c:url value='/download-document-${document.id}' />">${document.name}</a></TD>
							</TR>
						</c:forEach>
						</c:when>
					</c:choose>
					</TABLE> 
			</div>
		</div>
	</form:form>
    </div>
    </div>    

</body>
</html>