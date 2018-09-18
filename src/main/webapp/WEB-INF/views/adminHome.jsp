<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
  <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>

<div class="row">

<security:authorize access="isAuthenticated()">
      		<font color="red">${accessMessage}</font>
      		
			<span class="well floatRight"><a href="/opsCenter/logout">Logout</a></span>
			
   </security:authorize>
      <security:authorize access="isAnonymous()">
      		<font color="red">${accessMessage}</font>
			<span class="well floatright"><a href="/opsCenter/login">Login</a></span>
   </security:authorize>
  
   </div>	
  <div class="container">
  <div class="row">
	&nbsp;
	</div>
	<div class="row">
	&nbsp;
	</div>
	<div class="row">
	&nbsp;
	</div>
	<div class="row">
	&nbsp;
	</div>
 
	<div class="row">
     
	  <div class="col-md-3">
	  <div class="box_my">
	   <h3> <a href="/opsCenter/services/list"><strong>Service Management</strong></a></h3>    
	  </div>
	  <div class="col-md-3">
	  &nbsp;
	  </div>
	  </div>
	  
	  <div class="col-md-3">
	  <div class="box_my">
	    <h3><a href="/opsCenter/users/list"><strong>Knowledge Management</strong></a></h3>
	    </div>
	    <div class="col-md-3">
	  &nbsp;
	  </div>
	  </div>
	  
	  <div class="col-md-3">
	  <div class="box_my">
	    <h3><a href="/opsCenter/healthcheck/list"><strong>Health Checks</strong></a></h3>
	    </div>
	    <div class="col-md-3">
	  &nbsp;
	  </div>
	  </div>
	 	
	</div>

	<div class="row">
	  <div class="col-md-3">
	  <div class="box_my">
	    <h3><a href="/opsCenter/serviceSla/list"><strong>SLA Management</strong></a></h3>
	  </div>
	  <div class="col-md-3">
	  &nbsp;
	  </div>
	  </div>
	  
	  <div class="col-md-3">
	  <div class="box_my">
	    <h3><a href="/opsCenter/compservices/list"><strong>Components Management</strong></a></h3>
	    </div>
	    <div class="col-md-3">
	  &nbsp;
	  </div>
	  </div>
	  <security:authorize access="hasRole('OPS')">
	  <div class="col-md-3">
	  <div class="box_my">
	    <h3><a href="/opsCenter/change/list"><strong>Request For Change</strong></a></h3>	    
	  </div>	    
	  <div class="col-md-3">
	  &nbsp;
	  </div>
	  </div>
	  </security:authorize>
	  <security:authorize access="hasRole('ADMIN')">
	  <div class="col-md-3">
	  <div class="box_my">
	    <h3><a href="/opsCenter/users/list"><strong>User Management</strong></a></h3>	    
	    </div>
	    <div class="col-md-3">
	  &nbsp;
	  </div>
	  </div>	
	  </security:authorize>  
	
	</div>

</div>
</body>
</html>