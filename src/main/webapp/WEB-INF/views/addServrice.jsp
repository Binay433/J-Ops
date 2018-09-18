<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Service Registration Form</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
	<script src="/opsCenter/static/script/comon.js"></script>
</head>

<body>
<security:authorize access="hasAnyRole('ADMIN')">
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
 	
				

 	<form:form method="POST" modelAttribute="service" enctype="multipart/form-data" class="form-horizontal">
		<form:input type="hidden" path="id" id="id"/>
		<div class="row">
		&nbsp;
		</div>
 		<div class="row">
			<div class="form-group col-md-12">
			<div class="col-md-3">&nbsp;</div>
				<label class="col-md-1 control-lable" for="account"><strong>Account</strong></label>
				<div class="col-md-3">
					<form:select path="account" items="${accounts}" multiple="false"  itemValue="id" itemLabel="name" class="form-control input-sm" disabled="${disable}"/>
<%-- 					<div class="has-error">
						<form:errors path="account" class="help-inline"/>
					</div> --%>
				</div>
			</div>
		</div> 
		<div class="row">
			<div class="form-group col-md-12">
			<div class="col-md-3">&nbsp;</div>
				<label class="col-md-1 control-lable" for="name"><strong>Service Name</strong></label>
				<div class="col-md-3">
					<form:input type="text" path="name" id="name" class="form-control input-sm" disabled="${disable}"/>
				</div>
				<div class="col-md-3">
					<font color="red">${nameErr}</font>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group col-md-12">
			<div class="col-md-3">&nbsp;</div>
				<label class="col-md-1 control-lable" for="businessOwner"><strong>Business Owner</strong></label>
				<div class="col-md-3">
					<form:input type="text" path="businessOwner" id="businessOwner" class="form-control input-sm" disabled="${disable}"/>
				</div>
				<div class="col-md-3">
					<font color="red">${businessOwnerErr}</font>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
			<div class="col-md-3">&nbsp;</div>
				<label class="col-md-1 control-lable" for="serviceOwner"><strong>Service Owner</strong></label>
				<div class="col-md-3">
					<form:input type="text" path="serviceOwner" id="serviceOwner" class="form-control input-sm" disabled="${disable}"/>
				</div>
				<div class="col-md-3">
					<font color="red">${serviceOwnerErr}</font>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
			<div class="col-md-3">&nbsp;</div>
				<label class="col-md-1 control-lable" for="supportBy"><strong>Support Owner</strong></label>
				<div class="col-md-3">
					<form:input type="text" path="supportBy" id="supportBy" class="form-control input-sm" disabled="${disable}"/>
				</div>
				<div class="col-md-3">
					<font color="red">${supportByErr}</font>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
			<div class="col-md-3">&nbsp;</div>
				<label class="col-md-1 control-lable" for="systemManager"><strong>System Manager</strong></label>
				<div class="col-md-3">
					<form:input type="text" path="systemManager" id="systemManager" class="form-control input-sm" disabled="${disable}"/>
				
				</div>
				<div class="col-md-3">
					<font color="red">${systemManagerErr}</font>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
			<div class="col-md-3">&nbsp;</div>
				<label class="col-md-1 control-lable" for="hrsStart"><strong>Service Start Time</strong></label>
				<div class="col-md-3">
					<form:input type="time" path="hrsStart" id="hrsStart" class="form-control input-sm" disabled="${disable}"/>
				</div>
				<div class="col-md-3">
					<font color="red">${hrsStartErr}</font>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
			<div class="col-md-3">&nbsp;</div>
				<label class="col-md-1 control-lable" for="hrsEnd"><strong>Service End Time</strong></label>
				<div class="col-md-3">
					<form:input type="time" path="hrsEnd" id="hrsEnd" class="form-control input-sm" disabled="${disable}"/>
				</div>
				<div class="col-md-3">
					<font color="red">${hrsEndErr}</font>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group col-md-12">
			<div class="col-md-3">&nbsp;</div>
				<label class="col-md-1 control-lable" for="brief"><strong>Description</strong></label>
				<div class="col-md-3">
					<form:textarea type="time" path="brief" id="brief" cols="4" class="form-control input-sm" disabled="${disable}"/>
				</div>
				<div class="col-md-3">
					<font color="red">${briefErr}</font>
				</div>
			</div>
		</div>
				<div class="row">
			<div class="form-group col-md-12">			
					<div class="row">
						<div class="form-group col-md-12">
						<div class="col-md-3">&nbsp;</div>
							<div class="col-md-1" align="left">
							<label for="file">&nbsp;&nbsp;&nbsp;<strong>Reference Doc</strong></label>
							</div>
							<div class="col-md-8">
											<div class="col-md-3">
												<form:input type="file" path="file" id="Upload"  title="update" class="form-control input-sm" disabled="${disable}"/>
												<div class="has-error">
													<form:errors path="file" class="help-inline"/>
												</div>
											</div>
											<c:choose>
													<c:when test="${service.docId!=null}">
													<div class="col-md-3">
														<a href="<c:url value='/download-document-${service.docId}' />" >Existing Doc.</a>
													</div>
													<div class="col-md-3">
															  		 <security:authorize access="hasRole('ADMIN')">
																			<div class="col-md-1">
																				<a href="javascript:confirmDelete('/opsCenter/services/delete-servicedoc-${service.id}')"class="btn btn-danger custom-width"><img src="/opsCenter/static/images/delete.png" alt="exit" height="20" width="30" data-toggle="tooltip" data-placement="left" title="Delete Document"></a>
																			</div>
																</security:authorize>
													</div>			
													</c:when>
												</c:choose>
												<font color="red">${fileErr}</font>
											

							</div>
					
						</div>
					</div>
			</div>
		</div>

		<security:authorize access="hasAnyRole('ADMIN')">
		<div class="row">
			<div class="form-actions">
			<div class="col-md-4">&nbsp;</div>
				<c:choose>
					<c:when test="${edit}">
						<input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/services/list--' />">Cancel</a>
					</c:when>
					<c:otherwise>
						<input type="submit" value="Submit" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/services/list--' />">Cancel</a>
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