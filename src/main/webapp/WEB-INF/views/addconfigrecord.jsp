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
<form:form method="POST" modelAttribute="connfigData" enctype="multipart/form-data">
<br>
&nbsp;&nbsp;Key:
<div class="row">
<div class="form-group col-md-12">
<form:input type="text" path="code" id="code" class="form-control input-sm" />
</div>
</div>
&nbsp;&nbsp;Value:
<div class="row">
<div class="form-group col-md-12">
<form:input type="text" path="value" id="value" class="form-control input-sm" />
</div>
</div>
<div class="row">
<div class="col-md-6">
<input type="submit"/>
</div>
</div>
</form:form>
</body>
</html>