<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
		<script src="/opsCenter/static/script/comon.js"></script>
<title>OPS Center</title>
</head>
<body>

	<!--<div class="generic-container">-->
		<div class="panel panel-default1">
	<!-- Default panel contents -->
	<div class="row" style="background-color: <%=System.getProperty("header_background") %>;">
		  	<div class="col-md-5" style="font-size: xx-large;font-family: sans-serif;"><strong>&nbsp;&nbsp;&nbsp;&nbsp;HEALTH CHECKS MONTHLY</strong></div>
		  	
		  	<div class="col-md-7">
		  	<security:authorize access="isAnonymous()">
		<font color="red">${accessMessage}</font>
		<span class="well floatRight"><a href="/opsCenter/login" data-toggle="tooltip" data-placement="left" title="Login">Login</a></span>
	</security:authorize>
	<security:authorize access="isAuthenticated()">
		<font color="red">${accessMessage}</font>
		<span class="floatRight"><a href="/opsCenter/logout" data-toggle="tooltip" data-placement="left" title="Exit"><img src="/opsCenter/static/images/exit.png" alt="exit" height="40" width="40"></a></span>
		<span class="floatRight" style="cursor: pointer"><a
					class="button" onclick="goBack()" data-toggle="tooltip" data-placement="left" title="Back"><img
						src="/opsCenter/static/images/back.png" alt="Back" height="40"
						width="40"></a></span> <span class="floatRight"><a
					href="/opsCenter/home" data-toggle="tooltip" data-placement="left" title="Home"><img
						src="/opsCenter/static/images/home1.png" alt="Home" height="40"
						width="40"></a></span>
	</security:authorize>
		  	</div>
		  	</div>	
</div>

    <table class="table" border="0">
    <thead>
      <tr>
        <th width="23%">&nbsp;</th>
			<c:forEach items="${months}" var="month">
				<th width="6%">${month}</th>
			</c:forEach>
			<th>&nbsp;</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${finalList}" var="checkRow">
			<tr>


				<td><div style="text-align: right;"><a href="/opsCenter/healthcheck/datewise---${checkRow.get(13)}" style="text-decoration: none;"><strong>${checkRow.get(0)}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong></a></div></td>
				<c:forEach items="${checkRow}" var="item" varStatus="row">
				<c:choose>
						<c:when test="${item=='OK'}">
							<c:set var="status" value="OK" />
							<c:set var="colColor" value="#bbff99" />
						</c:when>
						<c:when test="${item=='ISSUE'}">
							<c:set var="colColor" value="#ff3333" />
							<c:set var="status" value="Issue" />
						</c:when>
						<c:otherwise>
							<c:set var="colColor" value="#E5E4E2" />
							<c:set var="status" value="-" />
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${item!=checkRow.get(0) && item!=checkRow.get(13)}">
						<c:choose>
						<c:when test="${status=='Issue'}">
						<td style="background:${colColor} "><a href="/opsCenter/healthcheck/datewise--month(${row.count-1 })-${checkRow.get(13)}">${status}</a></td>
						</c:when>
						<c:otherwise>
						<td style="background:${colColor} ">${status}</td>
						</c:otherwise>
						</c:choose>
							
						</c:when>
					</c:choose>

				</c:forEach>
			</tr>
		</c:forEach>

    </tbody>
  </table>
    
</body>
</html>