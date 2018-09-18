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
	<title>OPS Center</title>
</head>
<body>
	<div class="generic-container1">
			<div class="panel panel-default">
			  <!-- Default panel contents -->			  
		  	
		  	
		  	
		  	<div class="row">
		  	<div class="row my_brown">	
		  	<div class="row" style="background-color: <%=System.getProperty("header_background") %>;">
		  	<div class="col-md-5" style="font-size: xx-large;font-family: sans-serif;"><strong>&nbsp;&nbsp;&nbsp;List of Change Requests</strong></div>
		  	<div class="col-md-2">
		  		<span class="floatLeft"><a href="/opsCenter/change/summary-" data-toggle="tooltip" data-placement="left" title="Month View"><img src="/opsCenter/static/images/month.png" alt="Month View" height="40" width="40"></a></span>
		  		&nbsp;&nbsp;&nbsp;
		  		<span class="floatLeft"><a href="/opsCenter/change/servicesummary-" data-toggle="tooltip" data-placement="left" title="Service statistics"><img src="/opsCenter/static/images/serviceTally.png" alt="exit" height="40" width="40"></a></span>
			</div>
		  	<div class="col-md-5">
		  		<span class="floatRight"><a href="/opsCenter/logout" data-toggle="tooltip" data-placement="left" title="Exit"><img src="/opsCenter/static/images/exit.png" alt="exit" height="40" width="40"></a></span>
		  		<security:authorize access="hasRole('OPS')">
		  		<span class="floatRight"><a href="<c:url value='/change/newchange-'/>" data-toggle="tooltip" data-placement="left" title="Add New"><img src="/opsCenter/static/images/add.png" alt="add" height="40" width="40"></a></span>
		  		</security:authorize>
			  	<span class="floatRight" style="cursor:pointer"><a class="button" onclick="goBack()" data-toggle="tooltip" data-placement="left" title="Back"><img src="/opsCenter/static/images/back.png" alt="Back"  height="40" width="40"></a></span>
			  	<span class="floatRight"><a href="/opsCenter/home" data-toggle="tooltip" data-placement="left" title="Home"><img src="/opsCenter/static/images/home1.png" alt="home"  height="40" width="40" ></a></span>
		  	</div>
		  	</div>
		  	<div class="column_heading">
		  		<div class="col-md-2" align="left"><label><strong>&nbsp;&nbsp;&nbsp;Change Ref.</strong></label></div>
		  		<div class="col-md-2" align="left"><label><strong>Incident Ref.</strong></label></div>
		  		<!-- <div class="col-md-1 my_text_align"><label><strong>Date</strong></label></div>
		  		<div class="col-md-1 my_text_align"><label><strong>Time</strong></label></div> -->
		  		<div class="col-md-1" align="left"><label><strong>Date</strong></label></div>
		  		<div class="col-md-1" align="left"><label><strong>Status</strong></label></div>
		  		<div class="col-md-1" align="left"><label><strong>Env.</strong></label></div>
		  		<div class="col-md-1" align="left"><label><strong>Category</strong></label></div>
		  		<div class="col-md-1" align="left"><label><strong>Requested By</strong></label></div>

		  		</div>
		  	</div>
		  	<c:forEach items="${changes}" var="change" varStatus="counter">
		  	<c:choose>
			    <c:when test="${counter.count%2==0}">
			        	<c:set var="background" value='<%=System.getProperty("row_even") %>'/>
			    </c:when>
			    <c:otherwise>
			        <c:set var="background" value='<%=System.getProperty("row_odd") %>'/>
			    </c:otherwise>
				</c:choose>
				
				<div class="row" style="background-color:${background}">
		  	<div class="col-md-1" >&nbsp;</div>
		  	</div>
		   	<div class="row" style="background-color:${background}">
		   		<div class="col-md-2" align="left">&nbsp;&nbsp;&nbsp;&nbsp;${change.crqRef}</div>
		   		<div class="col-md-2" align="left">${change.incidenceRef}</div>
		   		<div class="col-md-1" align="left" style="background-color:${change.dateColor}">${change.latestProdDate}</font></div>
		   		<div class="col-md-1" align="left">${change.status}</div>
		 		<div class="col-md-1" align="left">${change.changeEnvironment}</div>
		 		<div class="col-md-1" align="left">${change.changeCategory}</div>
		 		<div class="col-md-1" align="left">${change.requestBy.firstName}&nbsp;${change.requestBy.lastName}</div>
		 		
		 		
			    
		  		
		  		 <security:authorize access="hasRole('OPS')">
							<div class="col-md-1"><a href="<c:url value='/change/edit-changerequest-${change.id}' />" class="btn btn-success custom-width" data-toggle="tooltip" data-placement="left" title="Edit">Edit</a></div>
							<div class="col-md-1"><a href="<c:url value='/change/newchange-${change.id}' />" class="btn btn-success custom-width" data-toggle="tooltip" data-placement="left" title="Copy">Copy</a></div>
							<div class="col-md-1"><a href="<c:url value='/change/delete-change-${change.id}' />"  onclick="return beforeDelete()" class="btn btn-danger custom-width" data-toggle="tooltip" data-placement="left" title="Delete">Delete</a></div>
				</security:authorize>
				<security:authorize access="hasRole('USER')">
							<div class="col-md-1"><a href="<c:url value='/change/edit-changerequest-${change.id}' />" class="btn btn-success custom-width" data-toggle="tooltip" data-placement="left" title="View">View</a></div>
				</security:authorize>
		  	</div>
		  	</c:forEach>
		</div>
		</div>
		</div>
</body>
<script type="text/javascript">
function beforeDelete(){
	var status = confirm("Are you sure that you want to delete this change?");
	 if(status){
		return submit;		
	}
	return false;
	

}
</script>
</html>