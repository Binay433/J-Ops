<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
</head>

<body>
<div class="container">

<div class="jumbotron">
   <img src="/opsCenter/static/images/ops.png" height="100" width="100%"/>
   <h1>OPS Center</h1>
   <security:authorize access="isAuthenticated()">
      		<font color="red">${accessMessage}</font>
			<a href="/opsCenter/logout">Logout</a>
   </security:authorize>
      <security:authorize access="isAnonymous()">
      		<font color="red">${accessMessage}</font>
			<a href="/opsCenter/login">Login</a>
   </security:authorize>

					
   <p>OPS Admin</p> 
   <p>we share our experiences and knowledge about the services we provide, to enhance our support and 
   services to make make our customers more happy and to help people joining us to provide better
   knowledge base</p> 
</div>

	<div class="row">
	  <div class="col-md-3">
	    <h3><a href="/opsCenter/healthcheck/list">Health check</a></h3>
	    <p>Add health check data</p>
	  </div>
	  <div class="col-md-3">
	    <h2><a href="/opsCenter/healthcheck/datewise---">Health-check View</a></h2>
	    <p>Health Check Report</p>
	  </div>
	  <div class="col-md-3">
	    <h3><a href="/opsCenter/serviceSla/list">SLA</a></h3>
	    <p>SLA lorepum test xyz. lorepum test xyz.lorepum test xyz.lorepum test xyz.lorepum test xyz.</p>
	  </div>
	  <div class="col-md-3">
	    <h3><a href="/opsCenter/serviceSla/datewise--">SLA View</a></h3>
	    <p>SLA lorepum test xyz. lorepum test xyz.lorepum test xyz.lorepum test xyz.lorepum test xyz.</p>
	  </div>
	
	</div>
	<div class="row">
	  <div class="col-md-3">
	    <h3><a href="/opsCenter/services/list">Services</a></h3>
	    <p>you can View services from here</p>
	  </div>
	  <div class="col-md-3">
	    <h3><a href="/opsCenter/users/list">Service Transition</a></h3>
	    <p>details on service transition</p>
	    
	  </div>
	  <div class="col-md-3">
	    <h3><a href="/opsCenter/change/list">Request For Change</a></h3>
	     <p>Request an Expedite change from here</p>
	  </div>
	
	</div>
</div>
</body>
</html>