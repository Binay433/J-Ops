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
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <script src="/opsCenter/static/script/comon.js"></script>	
</head>

<body>

<div class="row">
	<div class="col-md-10" align="right">
	<font color="blue">
	<security:authorize access="isAuthenticated()">
		<div class="row">
			
			<div class="col-md-10">
			Hello ${user.firstName} ${user.lastName},
			</div>
		</div>
		<div class="row">
			<div class="col-md-10">
 				<c:choose>
					<c:when test="${user.servicepoint!=null}">
					You have <a href="/opsCenter/SP/list-${user.id}">earned <b>${user.servicepoint}</b></a> service points.
					</c:when>
				</c:choose> 
			</div>
		</div>
		</security:authorize>
		</font>
	</div>
	<div class="col-md-2">
	<security:authorize access="isAuthenticated()">
	      		<font color="red">${accessMessage}</font>
	      		
				<span class="floatRight"><a href="/opsCenter/logout" data-toggle="tooltip" data-placement="left" title="Exit"><img src="/opsCenter/static/images/exit.png" alt="exit" height="50" width="50"></a></span>
				<a href="/opsCenter/users/profile-${user.id}">Change Password</a>
	   </security:authorize>
	      <security:authorize access="isAnonymous()">
	      		<font color="red">${accessMessage}</font>
				<span class="floatright"><a href="/opsCenter/login"  data-toggle="tooltip" data-placement="left" title="Login">Login</a></span>
	   </security:authorize>
	  </div>
</div>	
 <div class="row">
	<div class="col-md-12"><font size="3">&nbsp;&nbsp;<marquee style="background-color:orange"><%=System.getProperty("welcome_message") %></marquee></font></div>
	</div> 
  <div class="home-container">
 
 <div class="row"><div class="col-md-4">&nbsp;</div><div class="col-md-8">&nbsp;</div></div>
 
	<div class="row">
     
	  <div class="col-md-4">
	  <a href="/opsCenter/services/list--"><img src="/opsCenter/static/images/ICONS_03.jpg" alt="Services">
	  </a>
	  </div>
	  
	   <div class="col-md-4">
	  	<a href="/opsCenter/kb/search"><img src="/opsCenter/static/images/ICONS_05.jpg" alt="Knowledge Management"> </a> 
	  </div>
	  
	 <security:authorize access="hasRole('ADMIN')">
		 <div class="col-md-4">
		  	<a href="/opsCenter/users/list"><img src="/opsCenter/static/images/ICONS_19.jpg" alt="Users"> </a>
		 </div>
	</security:authorize>
	
	<security:authorize access="hasRole('OPS')">
		<div class="col-md-4">
		  	<a href="/opsCenter/serviceSla/newsladata-"><img src="/opsCenter/static/images/ICONS_07.jpg" alt="SLA"> </a>
		</div>
	</security:authorize>
	<security:authorize access="hasAnyRole('ADMIN', 'USER')">
		<div class="col-md-4">
		  	<a href="/opsCenter/serviceSla/list-0-5"><img src="/opsCenter/static/images/ICONS_07.jpg" alt="SLA"> </a>
		</div>
	</security:authorize>
	  <div class="col-md-4">
		  	<a href="/opsCenter/serviceSla/datewise--"><img src="/opsCenter/static/images/icons_12.jpg" alt="SLA Report"> </a>
	 </div>
	  <security:authorize access="hasRole('ADMIN')">
		  <div class="col-md-4">
		  <a href="/opsCenter/compservices/list"><img src="/opsCenter/static/images/ICONS_20.jpg" alt="Service Components">
		  </a>
		  </div>

	  </security:authorize>
	  <security:authorize access="hasAnyRole('OPS', 'ADMIN')">
			<div class="col-md-4">
		  		<a href="/opsCenter/healthcheck/list"><img src="/opsCenter/static/images/ICONS_13.jpg" alt="Health Checks"> </a>
	 		</div>
	</security:authorize>
	 
	 <div class="col-md-4">
		  	<a href="/opsCenter/healthcheck/compact---"><img src="/opsCenter/static/images/icons_14.jpg" alt="Health Status"> </a>
	 </div>
	<security:authorize access="hasAnyRole('OPS', 'USER')">
		<div class="col-md-4">
			  	<a href="/opsCenter/change/list-"><img src="/opsCenter/static/images/ICONS_18.jpg" alt="Emergency Changes"> </a>
		</div>
	</security:authorize>
	<security:authorize access="hasAnyRole('OPS', 'USER')">
		<div class="col-md-4">
			  	<a href="/opsCenter/release/list">Release Management</a>
		</div>
	</security:authorize>
	<security:authorize access="hasRole('ADMIN')">
	<div class="col-md-4">
	  <a href="/opsCenter/config/list"><img src="/opsCenter/static/images/config.jpg">
	  </a>
	  </div>
	  </security:authorize>
	  
	  	<security:authorize access="hasRole('SUPER')">
			<div class="col-md-4">
		  	<a href="/opsCenter/SP/allearned"><img src="/opsCenter/static/images/ICONS_21.jpg">
		  	</a>
	  </div>
	  </security:authorize>
	  
	  
	</div>
	</div>


</body>
</html>