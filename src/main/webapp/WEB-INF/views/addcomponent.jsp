<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Service Registration Form</title>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <script src="/opsCenter/static/script/comon.js"></script>	
</head>

<body>
	<div class="generic-container1">
		<div class="panel panel-default1">
		
			<div class="row" style="background-color: <%=System.getProperty("header_background") %>;">
		  	<div class="col-md-4" style="font-size: xx-large;font-family: sans-serif;"><strong>${component.name}</strong></div>
		  	
		  	<div class="col-md-8">
		  	<security:authorize access="isAuthenticated()">
			  		<font color="red">${accessMessage}</font>
					<span class=" floatRight"><a href="/opsCenter/logout" data-toggle="tooltip" data-placement="left" title="Exit"><img src="/opsCenter/static/images/exit.png" alt="exit" height="40" width="40"></a></span>
					<security:authorize access="hasRole('ADMIN')">
					<span class="floatRight"><a href="<c:url value='/services/newservice'/>" data-toggle="tooltip" data-placement="left" title="Add New"><img src="/opsCenter/static/images/add.png" alt="add" height="40" width="40"></a></span>
					</security:authorize>
					<span class="floatRight" style="cursor:pointer"><a class="button" onclick="goBack()" data-toggle="tooltip" data-placement="left" title="Back"><img src="/opsCenter/static/images/back.png" alt="Back"  height="40" width="40"></a></span>
			  		<span class="floatRight my_text_black"><a href="/opsCenter/home" data-toggle="tooltip" data-placement="left" title="Home"><img src="/opsCenter/static/images/home1.png" alt="Home" height="40" width="40"> </a></span>
	   			</security:authorize>
		  	</div>
		  	</div>			
			<form:form method="POST" modelAttribute="component"
				enctype="multipart/form-data" class="form-horizontal">
				<form:input type="hidden" path="id" id="id" />
				<div class="row">&nbsp;</div>
				<div class="row">
					<div class="form-group col-md-12">
						<div class="col-md-3">&nbsp;</div>
						<div class="col-md-1" align="left">
							<label for="services">&nbsp;&nbsp;&nbsp;<strong>Services</strong></label>
						</div>
						<div class="col-md-3">
							<form:select path="serviceModel" items="${services}"
								multiple="false" itemValue="id" itemLabel="name"
								class="form-control input-sm" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-12">
						<div class="col-md-3">&nbsp;</div>
						<div class="col-md-1" align="left">
							<label for="name">&nbsp;&nbsp;&nbsp;<strong>Name</strong></label>
						</div>
						<div class="col-md-3">
							<form:input type="text" path="name" id="name"
								class="form-control input-sm" />
						</div>
						<div class="col-md-3">
							<font color="red">${nameErr}</font>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-12">
						<div class="col-md-3">&nbsp;</div>
						<div class="col-md-1" align="left">
							<label for="component_description">&nbsp;&nbsp;&nbsp;<strong>Description</strong></label>
						</div>
						<div class="col-md-3">
							<form:input type="text" path="componentDescription"
								id="componentDescription" class="form-control input-sm" />
						</div>
						<div class="col-md-3">
							<font color="red">${componentDescriptionErr}</font>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-12">
						<div class="col-md-3">&nbsp;</div>
						<div class="col-md-1" align="left">
							<label for="component_description">&nbsp;&nbsp;&nbsp;<strong>Version</strong></label>
						</div>
						<div class="col-md-3">
							<form:input type="text" path="currentVersion" id="currentVersion"
								class="form-control input-sm" />
						</div>
						<div class="col-md-3">
							<font color="red">${currentVersionErr}</font>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-12">

						<div class="col-md-3">&nbsp;</div>
						<div class="col-md-1" align="left">
							<label for="file"><strong>&nbsp;&nbsp;Reference
									Doc</strong></label>
						</div>
						<div class="col-md-6">
							<div class="col-md-4">
								<form:input type="file" path="file" id="Upload" title="update" />
								<div class="has-error">
									<form:errors path="file" class="help-inline" />
								</div>
							</div>
							<div class="col-md-3">
								<c:choose>
									<c:when test="${component.docId!=null}">
										<a
											href="<c:url value='/download-document-${component.docId}' />">Existing
											Doc.</a>
									</c:when>
								</c:choose>
								<font color="red">${fileErr}</font>
							</div>

						</div>



					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-12">
						<div class="form-actions">
							<div class="col-md-5">&nbsp;</div>
							<c:choose>
								<c:when test="${edit}">
									<input type="submit" value="Update"
										class="btn btn-primary btn-sm" /> or <a class="btn btn-primary btn-sm" onclick="goBack()">
										Cancel</a>
								</c:when>
								<c:otherwise>
									<input type="submit" value="Submit"
										class="btn btn-primary btn-sm" /> or <a class="btn btn-primary btn-sm" onclick="goBack()">
										Cancel</a>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>