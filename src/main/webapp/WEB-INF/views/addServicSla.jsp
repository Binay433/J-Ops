<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Service SLA Entry</title>
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
		  	<div class="col-md-4" style="font-size: xx-large;font-family: sans-serif;"></div>
		  	
		  	<div class="col-md-8">
		  	<span class="floatRight"><a href="/opsCenter/logout" data-toggle="tooltip" data-placement="left" title="Exit"><img
						src="/opsCenter/static/images/exit.png" alt="exit" height="40"
						width="40"></a></span>
				<security:authorize access="hasRole('OPS')">
					<span class="floatRight"><a
						href="<c:url value='/serviceSla/newsladata'/>" data-toggle="tooltip" data-placement="left" title="SLA_Entry">SLA-Entry</a></span>
				</security:authorize>
				<span class="floatRight" style="cursor: pointer"><a
					class="button" onclick="goBack()" data-toggle="tooltip" data-placement="left" title="Back"><img
						src="/opsCenter/static/images/back.png" alt="Back" height="40"
						width="40"></a></span> <span class="floatRight"><a
					href="/opsCenter/home" data-toggle="tooltip" data-placement="left" title="Home"><img
						src="/opsCenter/static/images/home1.png" alt="Home" height="40"
						width="40"></a></span>
		  	</div>
		  	</div>
			<div class="row">&nbsp;</div>
			<form:form method="POST" modelAttribute="serviceSla"
				enctype="multipart/form-data" class="form-horizontal">
				<form:input type="hidden" path="id" id="id" />
				<div class="row">
					<div class="form-group col-md-12">
						<div class="col-md-3">&nbsp;</div>
						<div class="col-md-2" align="left">
							<label for="account">&nbsp;&nbsp;&nbsp;<strong>Service</strong></label>
						</div>
						<div class="col-md-3">
							<form:select path="serviceModel" items="${services}"
								multiple="false" itemValue="id" itemLabel="name"
								class="form-control input-sm" />
							<div class="has-error">
								<form:errors path="serviceModel" class="help-inline" />
							</div>
						</div>

					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-12">
						<div class="col-md-3">&nbsp;</div>
						<div class="col-md-2" align="left">
							<label for="name">&nbsp;&nbsp;&nbsp;<strong>SLA
									Reference</strong></label>
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
						<div class="col-md-2" align="left">
							<label for="businessOwner">&nbsp;&nbsp;&nbsp;<strong>Description</strong></label>
						</div>
						<div class="col-md-3">
							<form:textarea type="text" path="description" id="description"
								cols="4" class="form-control input-sm" />

						</div>
						<div class="col-md-3">
							<font color="red">${descriptionErr}</font>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-12">
						<div class="col-md-3">&nbsp;</div>
						<div class="col-md-2" align="left">
							<label for="businessOwner">&nbsp;&nbsp;&nbsp;<strong>Target
									Green</strong></label>
						</div>
						<div class="col-md-3">
							<form:input type="text" path="targetGreen" id="targetGreen"
								class="form-control input-sm" />
						</div>
						<div class="col-md-3">
							<font color="red">${targetGreenErr}</font>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-12">
						<div class="col-md-3">&nbsp;</div>
						<div class="col-md-2" align="left">
							<label for="businessOwner">&nbsp;&nbsp;&nbsp;<strong>Target
									Amber</strong></label>
						</div>
						<div class="col-md-3">
							<form:input type="text" path="targetAmber" id="targetAmber"
								class="form-control input-sm" />
						</div>
						<div class="col-md-3">
							<font color="red">${targetAmberErr}</font>
						</div>
					</div>
				</div>
				
					<div class="row">
					<div class="form-group col-md-12">
						<div class="col-md-3">&nbsp;</div>
						<div class="col-md-2" align="left">
							<label for="businessOwner">&nbsp;&nbsp;&nbsp;<strong>SLA Type</strong></label>
						</div>
						<div class="col-md-3">
								<form:select path="sla_type" id="sla_type" class="form-control input-sm">
									<form:option value="0">Percentage</form:option>
									<form:option value="1">Average</form:option>
								</form:select>
								
								
								
						</div>
						<div class="col-md-3">
							<font color="red">${targetAmberErr}</font>
						</div>
					</div>
				</div>


				<div class="row">
					<div class="form-group col-md-12">
						<div class="row">
							<div class="form-group col-md-12">
								<div class="col-md-3">&nbsp;</div>
								<div class="col-md-2" align="left">
									<label for="file">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>Reference
											Doc</strong></label>
								</div>
								<div class="col-md-3" align="right">
									<form:input type="file" path="file" id="Upload" title="update" />
								</div>
								<div class="col-md-3" align="left">
									<c:choose>
										<c:when test="${serviceSla.docId!=null}">
											<a
												href="<c:url value='/download-document-${serviceSla.docId}' />">Existing
												Doc.</a>
										</c:when>
									</c:choose>
									<font color="red">${fileErr}</font>
								</div>

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
										class="btn btn-primary btn-sm" /> or <a
										href="<c:url value='/serviceSla/list' />">Cancel</a>
								</c:when>
								<c:otherwise>
									<input type="submit" value="Submit"
										class="btn btn-primary btn-sm" /> or <a
										href="<c:url value='/serviceSla/list' />">Cancel</a>
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