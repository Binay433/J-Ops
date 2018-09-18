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
							<strong>Change Requests month wise</strong>
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
				<div class="col-md-3" align="right"><a href="/opsCenter/change/summary-${year-1}" data-toggle="tooltip" data-placement="left" title="Previous" class="btn btn-level custom-width-lvl"><<</a></div>
				<div class="col-md-6" align="center"><label><strong><font size="5">Month wise changes summary for the year <b>${year}</b></font></strong></label></div>
				<div class="col-md-3" align="left"><a href="/opsCenter/change/summary-${year+1}" data-toggle="tooltip" data-placement="left" title="Next" class="btn btn-level custom-width-lvl">>></a></div>
		</div>
			
			<div class="row">

				<c:forEach items="${statusRows}" var="month">
					<div class="col-md-2 div-month">
					<table border="1" style="width: 100%">
									<c:forEach items="${month}" var="item" varStatus="counter">
										<c:choose>
											<c:when test="${counter.count==1}">
												<tr><td align="center" colspan="2"><font size="3" style="font-weight: bold;">${item[0]}</font></td></tr>
											</c:when>
											<c:otherwise>
												<tr><td><font size="2">${item[0]}</font></td><td align="right" width="10%"><font size="1">
												<a href="/opsCenter/change/listbymonyearstatus-${item[3]}-${year}-${item[2]}">${item[1]}</a>
												</font></td></tr>
											</c:otherwise>
										</c:choose>
									</c:forEach>
					</table>
					</div>
				</c:forEach>

			</div>
			
		</div>
	</div>
</body>
</html>