<%@page import="com.xchanging.ops.utils.CommonUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Health Check entry Form</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
	<script src="/opsCenter/static/script/comon.js"></script>
</head>

<body>

 	<div class="generic-container1">
	 	<div class="panel panel-default1">
	 	
	 	<div class="row" style="background-color: <%=System.getProperty("header_background") %>;">
		  	<div class="col-md-5" style="font-size: xx-large;font-family: sans-serif;"><strong>Existing Health check records</strong></div>
		  	
		  	<div class="col-md-7">
		  	<span class="floatRight"><a href="/opsCenter/logout"  data-toggle="tooltip" data-placement="left" title="Exit"><img src="/opsCenter/static/images/exit.png" alt="exit" height="40" width="40"></a></span> 
						<security:authorize access="hasRole('ADMIN')">
						<span class="floatRight"><a href="<c:url value='/serviceSla/newservicesla'/>"  data-toggle="tooltip" data-placement="left" title="Add New"><img src="/opsCenter/static/images/add.png" alt="add" height="40" width="40"></a></span>
						</security:authorize> 
						<security:authorize access="hasRole('OPS')">
								<span class="floatRight"><a href="<c:url value='/serviceSla/newsladata'/>" data-toggle="tooltip" data-placement="left" title="Sla-Entry">SLA-Entry</a></span>
						</security:authorize>
						<span class="floatRight" style="cursor:pointer"><a class="button" onclick="goBack()"  data-toggle="tooltip" data-placement="left" title="Back"><img src="/opsCenter/static/images/back.png" alt="Back"  height="40" width="40"></a></span>
						<span class="floatRight"><a href="/opsCenter/home"  data-toggle="tooltip" data-placement="left" title="Home"><img src="/opsCenter/static/images/home1.png" alt="Home" height="40" width="40"></a></span>
		  	</div>
		  	</div>
		</div>
		<div class="row"> 
		<div class="col-md-2"><font size="1">&nbsp;</font></div>
		<div class="col-md-2"><font size="4"><strong>DATE</strong></font></div>
		<div class="col-md-2"><font size="2"><strong><input type="text" id = "datepicker" value="${datestr}" class="datepicker form-control input-sm"/></strong></font></div>
		<div class="col-md-2"><font size="2"><strong><button onclick="reloadPage(${hcId});">Reload page</button></strong></font></div>
		 </div>			
			
			<c:choose>
				<c:when test="${size==0}">
				<div class="row">
				<div class="col-md-2"><font size="1">&nbsp;</font></div>
					<div class="col-md-4"><font size="2">No Record Found for ${displayDate}</font></div>
					</div>
				</c:when>
				<c:otherwise>
				<c:forEach items="${uniqCheck}" var="record" varStatus="counter">
					<c:choose>
						<c:when test="${counter.count%2==0}">
							<c:set var="background" value='<%=System.getProperty("row_even") %>'/>
						</c:when>
						<c:otherwise>
							<c:set var="background" value='<%=System.getProperty("row_odd") %>'/>
						</c:otherwise>
					</c:choose>
					<div class="row" style="background-color:${background}">
					<div class="col-md-1"><font size="2">&nbsp;</font></div>
					<div class="col-md-1"><font size="2">${counter.count}</font></div>
					<div class="col-md-3"><font size="2">${record.get(0)}</font></div>
					<div class="col-md-2"><font size="2">${record.get(1)}</font></div>
					<security:authorize access="hasRole('OPS')">
						<div class="col-md-1">
							<a href="<c:url value='/healthcheck/oldrecords-${record.get(3)}-${record.get(2)}-${currentDate}'/>"
								class="btn btn-success custom-width">Update</a>
						</div>
					</security:authorize>
					</div>
					<div class="row" style="background-color:${background}">
					<div class="col-md-10"><font size="2">&nbsp;</font></div>
					</div>
				</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
		
	
</body>

<script type="text/javascript">

function reloadPage(hcId){
	var date= document.getElementById("datepicker").value;
	date =replaceSubstring(date,"-", "");
	window.open('/opsCenter/healthcheck/oldrecords-'+hcId+'--'+date,"_self");
	
}


function replaceSubstring(inSource, inToReplace, inReplaceWith) {

	  var outString = inSource;
	  while (true) {
	    var idx = outString.indexOf(inToReplace);
	    if (idx == -1) {
	      break;
	    }
	    outString = outString.substring(0, idx) + inReplaceWith +
	      outString.substring(idx + inToReplace.length);
	  }
	  return outString;

	}
</script>

</html>