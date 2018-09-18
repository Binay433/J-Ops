<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
		  	<div class="row" style="background-color: <%=System.getProperty("header_background") %>;">
				<div class="col-md-12">
						<security:authorize access="isAnonymous()">
				      		<font color="red">${accessMessage}</font>
				      		<span class="well floatRight"><a href="/opsCenter/login" data-toggle="tooltip" data-placement="left" title="Login">Login</a></span>
			   			</security:authorize>
					  	<security:authorize access="isAuthenticated()">
					  		<font color="red">${accessMessage}</font>
							<span class="floatRight"><a href="/opsCenter/logout" data-toggle="tooltip" data-placement="left" title="Exit"><img src="/opsCenter/static/images/exit.png" alt="exit" height="40" width="40"></a></span>
					  		<span class="floatRight" style="cursor:pointer"><a class="button" onclick="goBack()" data-toggle="tooltip" data-placement="left" title="Back"><img src="/opsCenter/static/images/back.png" alt="Back"  height="40" width="40"></a></span>
				  			<span class="floatRight"><a href="/opsCenter/home" data-toggle="tooltip" data-placement="left" title="Home"><img src="/opsCenter/static/images/home1.png" alt="Home" height="40" width="40"></a></span>
			   			</security:authorize>
				</div>
			</div>
		  	
		  	<div class="row my_brown">		  	
			  	<div class="column_heading">
			  		<div class="col-md-2"><font size="2">&nbsp;&nbsp;&nbsp;&nbsp;Check Name</font></div>
			  		<div class="col-md-1"><font size="2">Service</font></div>
			  		<div class="col-md-1"><font size="2">Component</font></div>
			  		<div class="col-md-1"><font size="2">Status</font></div>
			  		<div class="col-md-1"><font size="2">Check By</font></div>
			  		<div class="col-md-1"><font size="2">Updated Time</font></div>
			  		<div class="col-md-1"><font size="2">Linked KB Item</font></div>
			  		<div class="col-md-2"><font size="2">Comments</font></div>
			  	 </div>	  	

		  		</div>
		  	
		  	<c:forEach items="${transList}" var="trans" varStatus="counter">
		  		<c:choose>
				    <c:when test="${counter.count%2==0}">
				        	<c:set var="background" value="#deded8"/>
				    </c:when>
				    <c:otherwise>
				        <c:set var="background" value="#b3f297"/>
				    </c:otherwise>
				</c:choose>
		  	
		  	<c:choose>
		  	<c:when test="${trans.status!=0}">
		  	
		  	<div class="row" style="background-color:${background}">
			    <div class="col-md-2">&nbsp;&nbsp;&nbsp;&nbsp;${trans.healthCheck.name}</div>
			  	<div class="col-md-1">${trans.serviceModel.name}</div>
			  	<div class="col-md-1">${trans.component.name}</div>
			  	<c:choose>
			    <c:when test="${trans.status==1}">
			    	<div class="col-md-1" >Ok</div>
			    </c:when>
			    <c:when test="${trans.status==2}">
			    	<div class="col-md-1" style="background: #ff9933 ">Warning</div>
			    </c:when>
			    <c:otherwise>
			    	<div class="col-md-1" style="background-color: red">Issue</div>
			    </c:otherwise>
			    </c:choose>
			  	<!-- <div class="col-md-1">${trans.kbId}</div>-->
			  	<div class="col-md-1">${trans.checkBy.firstName} &nbsp;${trans.checkBy.lastName}</div>
			  	<div class="col-md-1">${trans.checkTime}</div>
			  	<c:choose>
			  		<c:when test="${trans.kbId!=null && trans.kbId >0}">
			  		<div class="col-md-1"><a href="#" onclick="window.open('/opsCenter/kb/searchpopup-${trans.kbId}','_blank','location=yes,height=400,width=500,scrollbars=yes')" ><font size="2" color="blue">KB ${trans.kbId}</font></a></div>
			  		</c:when>
			  		<c:otherwise>
			  		<div class="col-md-1"></div>
			  		</c:otherwise>
			  	</c:choose>
			  	
			  	
			  	
			  	
			  	<div class="col-md-2">${trans.comments}</div>
		  		 <security:authorize access="hasRole('OPS')">
							<div class="col-md-1"><a href="<c:url value='/healthcheck/edit-trn-${trans.id}' />" class="btn btn-success custom-width"><img src="/opsCenter/static/images/Edit.jpg" alt="exit" height="20" width="30"></a></div>
							<div class="col-md-1"><a href="<c:url value='/healthcheck/deletetrn/${trans.id}/${trans.serviceModel.id}/${fn:substring(trans.entryDate, 0, 10)}' />" class="btn btn-danger custom-width"><img src="/opsCenter/static/images/delete.png" alt="exit" height="20" width="30"></a></div>
				</security:authorize>

		  	</div><br>
		  	</c:when>
		  	</c:choose>
		  	
		  	
		  	
		  	</c:forEach> 
		  	</div>
		</div>

</body>


</html>