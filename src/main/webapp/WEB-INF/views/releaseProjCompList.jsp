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
		  	<div class="row" style="background-color: <%=System.getProperty("header_background") %>;">
		  	<div class="col-md-4" style="font-size: xx-large;font-family: sans-serif;"><strong>List of Components</strong></div>
		  	
		  	<div class="col-md-8">
		  	<security:authorize access="isAuthenticated()">
			  		<font color="red">${accessMessage}</font>
					<span class=" floatRight"><a href="/opsCenter/logout" data-toggle="tooltip" data-placement="left" title="Exit"><img src="/opsCenter/static/images/exit.png" alt="exit" height="40" width="40"></a></span>
					<span class="floatRight"><a href="<c:url value='${newlink}'/>" data-toggle="tooltip" data-placement="left" title="Add New"><img src="/opsCenter/static/images/add.png" alt="add" height="40" width="40"></a></span>
					<span class="floatRight" style="cursor:pointer"><a class="button" onclick="goBack()" data-toggle="tooltip" data-placement="left" title="Back"><img src="/opsCenter/static/images/back.png" alt="Back"  height="40" width="40"></a></span>
			  		<span class="floatRight my_text_black"><a href="/opsCenter/home" data-toggle="tooltip" data-placement="left" title="Home"><img src="/opsCenter/static/images/home1.png" alt="Home" height="40" width="40"> </a></span>
	   			</security:authorize>
		  	</div>
		  	</div>
		  	<div class="row my_brown">		  	
		  	<div class="column_heading">
		  		<div class="col-md-1 my_text_align"><label><font size="3">&nbsp;</font></label></div>
		  		<div class="col-md-1 my_text_align"><label><font size="3">Component Name</font></label></div>
		  		<div class="col-md-3 my_text_align"><label><font size="3">Change Summary</font></label></div>
		  		<div class="col-md-1 my_text_align"><label><font size="3">Build Date</font></label></div>
		  		<div class="col-md-1 my_text_align"><label><font size="3">Revision Number</font></label></div>
		  		<div class="col-md-4">&nbsp;</div>
		  	    <div class="col-md-1 my_text_align"><font size="4" color="red">${error}</font></div>	
		  	    </div>	  	
		  		</div> 
		  	<c:forEach items="${relCompList}" var="comp" varStatus="counter">
		 		<c:choose>
			    <c:when test="${counter.count%2==0}">
			        	<c:set var="background" value='<%=System.getProperty("row_even") %>'/>
			    </c:when>
			    <c:otherwise>
			        <c:set var="background" value='<%=System.getProperty("row_odd") %>'/>
			    </c:otherwise>
				</c:choose>
				<div class="row" style="background-color:${background}">
		  	<div class="col-md-12">&nbsp;</div>  	
		  	</div>	
		  	<div class="row" style="background-color:${background}">
				<security:authorize access="hasAnyRole('ADMIN','OPS','USER')">
						<c:set var="authenticated" value="${true}"/>
		  		</security:authorize>
		  		<div class="col-md-1 my_text_align"><label><font size="3">&nbsp;</font></label></div>
		  		<c:choose>
			    <c:when test="${authenticated}">
			        <div class="col-md-1 my_text_align">
			        <a href="<c:url value='/relComp/edit-${comp.id}' />"><strong>${comp.name}</strong></a>
			        </div>
			    </c:when>
			    <c:otherwise>
			        <div class="col-md-1 my_text_align"><strong>${comp.name}</strong></div>
			    </c:otherwise>
				</c:choose>
			  	<div class="col-md-3 my_text_align"><strong>${comp.changeSummary}</strong></div>
			  	<div class="col-md-1 my_text_align"><strong>${comp.buildDate}</strong></div>
			  	<div class="col-md-1 my_text_align"><strong>${comp.buildVersion}</strong></div>
		  		<div class="col-md-1 my_text_align"><strong><a href="<c:url value='/maping/listProj/${comp.id}' />">Projects</a></strong></div>
		  		<div class="col-md-1">
					<a href="<c:url value='${deleteurl}/${comp.id}'/>" class="btn btn-danger custom-width"><img src="/opsCenter/static/images/delete.png" alt="exit" height="20" width="30" data-toggle="tooltip" data-placement="left" title="Delete"></a>
				</div>

                
		  	</div>		
		   <div class="row" style="background-color:${background}">
		  	<div class="col-md-12">&nbsp;</div>  	
		  	</div>	
		  	</c:forEach>
		  	</div>
		  	
		  	
		  	
		</div>

</body>
</html>