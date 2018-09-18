<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="/opsCenter/static/script/comon.js"></script>
<title>OPS Center</title>
</head>
<body>
	<div class="generic-container1">
		<div class="panel panel-default">
			<!-- Default panel contents -->



			<div class="row">
				<div class="row my_brown">
					<div class="row"
						style="background-color: <%=System.getProperty("header_background")%>;">
						<div class="col-md-1"
							style="font-size: xx-large; font-family: sans-serif;">
							<strong>&nbsp;</strong>
						</div>
						<div class="col-md-6"
							style="font-size: xx-large; font-family: sans-serif;">
							<strong>&nbsp;&nbsp;&nbsp;Change Requests Service and month wise</strong>
						</div>
						<div class="col-md-5">
							<span class="floatRight"><a href="/opsCenter/logout"
								data-toggle="tooltip" data-placement="left" title="Exit"><img
									src="/opsCenter/static/images/exit.png" alt="exit" height="40"
									width="40"></a></span>
							<security:authorize access="hasRole('OPS')">
								<span class="floatRight"><a
									href="<c:url value='/change/newchange-'/>"
									data-toggle="tooltip" data-placement="left" title="Add New"><img
										src="/opsCenter/static/images/add.png" alt="add" height="40"
										width="40"></a></span>
							</security:authorize>
							<span class="floatRight" style="cursor: pointer"><a
								class="button" onclick="goBack()" data-toggle="tooltip"
								data-placement="left" title="Back"><img
									src="/opsCenter/static/images/back.png" alt="Back" height="40"
									width="40"></a></span> <span class="floatRight"><a
								href="/opsCenter/home" data-toggle="tooltip"
								data-placement="left" title="Home"><img
									src="/opsCenter/static/images/home1.png" alt="home" height="40"
									width="40"></a></span>
						</div>
					</div>

				</div>
			</div>
		</div>
		<div class="panel panel-default">
		<div class="row">
				<div class="col-md-3" align="right"><a href="/opsCenter/change/servicesummary-${year-1}" data-toggle="tooltip" data-placement="left" title="Previous" class="btn btn-level custom-width-lvl"><<</a></div>
				<div class="col-md-6" align="center"><label><strong><font size="5">Service wise changes summary for the year <b>${year}</b></font></strong></label></div>
				<div class="col-md-3" align="left"><a href="/opsCenter/change/servicesummary-${year+1}" data-toggle="tooltip" data-placement="left" title="Next" class="btn btn-level custom-width-lvl">>></a></div>
		</div>

			<div class="row">
				<div class="col-md-12">
					<table border="1" width="90%" align="center">
						<tr>
						<td style="background-color: #BFC9CA" width="25%"><strong>Service Name</strong></td>
						<c:forEach items="${months}" var="month">
							<td style="background-color: #BFC9CA" align="center"><strong>${month}</strong></td>
						</c:forEach>
						<td style="background-color: #BFC9CA" align="center"><strong>Total</strong></td>
						</tr>
						
						<c:forEach items="${serviceRows}" var="services" varStatus="counter">
								  	<c:choose>
								    <c:when test="${counter.count%2==0}">
								        	<c:set var="background" value='<%=System.getProperty("row_even") %>'/>
								    </c:when>
								    <c:otherwise>
								        <c:set var="background" value='<%=System.getProperty("row_odd") %>'/>
								    </c:otherwise>
								</c:choose>
						<tr style="background-color:${background}">
							<c:forEach items="${services}" var="service">
							<c:choose>
							<c:when test="${service[2]==''}">
								<td align="center">${service[2]}</td>
							</c:when>
							<c:otherwise>
								<td align="center"><a href="<c:url value='/change/listbymonyearserv-${service[0]}-${year}-${service[1]}' />">${service[2]}</a></td>
							</c:otherwise>
							</c:choose>
							</c:forEach>
						</tr>
						</c:forEach>
						
						
					</table>
				</div>
			</div>
			<div class="row">
			<div class="col-md-1">&nbsp;</div>
			<div class="col-md-1" style="background-color:#b3e6ff">
			<table>
				<tr><td><font size="1">Total Changes</font></td></tr>
				<tr><td>${total}</td></tr>
				</table>
			</div>
			<c:forEach items="${statusRow}" var="status" varStatus="counter">
								<c:choose>
								    <c:when test="${counter.count%2==0}">
								        	<c:set var="background" value='#ffe6e6'/>
								    </c:when>
								    <c:otherwise>
								        <c:set var="background" value='#ffffb3'/>
								    </c:otherwise>
								</c:choose>
				<div class="col-md-1" style="background-color:${background}">
				<table>
				<tr><td><font size="1">${status[1]}</font></td></tr>
				<tr><td><a href="<c:url value='/change/listbystatus-${status[0]}-${year}' />"> ${status[2]}</a></td></tr>
				</table>
				 </div>
			</c:forEach>
		</div>
	</div>
	</div>
</body>
</html>