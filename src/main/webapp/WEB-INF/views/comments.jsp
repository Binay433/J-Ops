<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <script src="/opsCenter/static/script/comon.js"></script>	
	<title>Comments</title>
</head>
<body>
	<div class="generic-container1">
			<div class="panel panel-default">
			  <!-- Default panel contents -->			  
		  	
		  	
		  	
		  	<div class="row">
		  	<div class="row my_brown">	
				<div class="col-md-10 my_text_align_1"><label><strong>Comments : ${comment}</strong></label></div>
		  	</div>
		  	<div class="row my_brown">	
				<div class="col-md-3 my_text_align_1">
					<input type="button" value="close" onclick="window.close(this);"/>
				</div>
		  	</div>
		</div>
		</div>
		</div>
</body>

</html>