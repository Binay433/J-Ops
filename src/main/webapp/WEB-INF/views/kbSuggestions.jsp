<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<div class="row" style="background-color: <%=System.getProperty("header_background") %>;">
				<div class="col-md-12">
						  	<span class="floatRight"><a href="/opsCenter/logout" data-toggle="tooltip" data-placement="left" title="Exit"><img
										src="/opsCenter/static/images/exit.png" alt="exit" height="40"
										width="40"></a></span>
								<span class="floatRight" style="cursor: pointer"><a href="/opsCenter/kb/search"
									class="button" data-toggle="tooltip" data-placement="left" title="Back"><img
										src="/opsCenter/static/images/back.png" alt="Back" height="40"
										width="40"></a></span> <span class="floatRight"><a
									href="/opsCenter/home" data-toggle="tooltip" data-placement="left" title="Home"><img
										src="/opsCenter/static/images/home1.png" alt="Home" height="40"
										width="40"></a></span>
				</div>
			</div>		
		<div class="row">
		<div class="col-md-1">&nbsp;</div>
		<div class="col-md-11">
			<h3>Below are the already submitted suggestions. Please Click on
				OK if you still wish to continue.</h3>
				</div>
		</div><hr>
		
		<div class="row">
			<c:choose>
				<c:when test="${kblistsuggestions.size()==0}">
					<div class="col-md-12" align="center">
						<font color="blue"> No record found, if you want to raise
							case please <a href="/opsCenter/kb/suggest">click</a> here
						</font>
					</div>

				</c:when>
				<c:otherwise>
					<c:forEach items="${kblistsuggestions}" var="suggestItem"
						varStatus="counter">
						<div class="col-md-12">
							<div class="row">
								<div class="col-md-11">
									<font color="green"><strong>&nbsp;&nbsp;&nbsp;${suggestItem.keyword}</strong></font>
								</div>
							</div>
							<div class="row">
								<div class="col-md-11">
									<font size="3" color="blue"><strong>&nbsp;&nbsp;${suggestItem.description}</strong></font>
								</div>
							</div>

							<hr>
						</div>


						<br>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="row">
		<div class="col-md-4">&nbsp;</div>
			<div class="col-md-1">
				<form:form method="POST" action="/opsCenter/kb/suggestSave"
					modelAttribute="kbForward" enctype="multipart/form-data"
					class="form-horizontal">
					<button type="submit" name="OK" value="Submit" class="btn btn-info">OK</button>
				</form:form>
			</div>
			<div class="col-md-4">
				<a href="/opsCenter/kb/search" class="btn btn-info" role="button">Cancel</a>
			</div>
		</div>
</body>
</html>