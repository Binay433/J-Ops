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
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <script src="/opsCenter/static/script/comon.js"></script>	
<title>OPS Center</title>
</head>
<body>

	<div class="generic-container1">
		<div class="panel panel-default1">
	<!-- Default panel contents -->
	<div class="row" style="background-color: <%=System.getProperty("header_background") %>;">
		  	<div class="col-md-4" style="font-size: xx-large;font-family: sans-serif;"><strong>HEALTH CHECKS DATED</strong></div>
		  	
		  	<div class="col-md-8">
		  	<security:authorize access="isAnonymous()">
						<font color="red">${accessMessage}</font>
						<span class="floatRight"><a href="/opsCenter/login" data-toggle="tooltip" data-placement="left" title="Login"><img src="/opsCenter/static/images/exit.png" alt="exit" height="40" width="40"></a></span>
					</security:authorize>
					<security:authorize access="isAuthenticated()">
						<font color="red">${accessMessage}</font>
						<span class="floatRight"><a href="/opsCenter/logout" data-toggle="tooltip" data-placement="left" title="Exit"><img src="/opsCenter/static/images/exit.png" alt="exit" height="40" width="40"></a></span>
						<span class="floatRight"><a href="/opsCenter/healthcheck/monthly" data-toggle="tooltip" data-placement="left" title="Monthly-View"><img src="/opsCenter/static/images/monthView.jpg" alt="Monthly-View"  height="40" width="40"></a></span>
						<span class="floatRight"><a href="#" onclick="goBack()" data-toggle="tooltip" data-placement="left" title="Back"><img src="/opsCenter/static/images/back.png" alt="Back"  height="40" width="40"></a></span>
						<span class="floatRight"><a href="/opsCenter/home" data-toggle="tooltip" data-placement="left" title="Home"><img src="/opsCenter/static/images/home1.png" alt="Home" height="40" width="40"></a></span>
					</security:authorize>	
		  	</div>
		  	</div>
		  		</div>
	<c:choose>
		<c:when test="${serviceid!=null}">
			<c:set var="srvId" value="${serviceid}" />
		</c:when>
	</c:choose>
    <div class="row">
    &nbsp;
    </div>
    		<br>	
	<div class="row">
	
			<div class="col-md-2" align="right">
				&nbsp;
			</div>
			<div class="col-md-1">
				<a
					href="/opsCenter/healthcheck/datewise-${prev}-${currentdate}-${srvId}" data-toggle="tooltip" data-placement="left" title="Previous"
					class="btn btn-level custom-width-lvl"><<</a>
			</div>
			<c:forEach items="${uidates}" var="date">
				<div class="col-md-1"><strong>${date}</strong></div>
			</c:forEach>
			<div class="col-md-1">
				<a href="/opsCenter/healthcheck/datewise-${next}-${currentdate}-${srvId}" data-toggle="tooltip" data-placement="left" title="Next"
					class="btn btn-level custom-width-lvl">>></a>
			</div>
		</div>
		<div class="row">&nbsp;</div>
		<c:forEach items="${finalList}" var="checkRow">
			<div class="row">
                
				<div class="col-md-3" align="right"><br><strong>${checkRow.get(0)}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong></div>				
				<c:forEach items="${checkRow}" var="item" varStatus="counter">
				<c:choose>
						<c:when test="${item=='1'}">
							<c:set var="colColor" value="#bbff99" />
							<c:set var="status" value="Ok" />
						</c:when>
						<c:when test="${item=='OFF'}">
				  			<c:set var="status" value="-"/>
				  			<c:set var="colColor" value="#ffffb3"/>
				  		</c:when>
						<c:when test="${item=='2'}">
							<c:set var="colColor" value=" #ff9933" />
							<c:set var="status" value="Warning" />
						</c:when>
						<c:when test="${item=='3'}">
							<c:set var="colColor" value="#ff3333" />
							<c:set var="status" value="Issue" />
						</c:when>
						<c:otherwise>
							<c:set var="colColor" value="#E5E4E2" />
							<c:set var="status" value="NA" />
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${item!=checkRow.get(0) && item!=checkRow.get(8)}">
							
							<c:choose>
							<c:when test="${item=='3' || item=='2'}">
							<div class="col-md-1 div-border" style="background:${colColor} " data-toggle="tooltip" data-placement="left">
							<c:set var="col" value="${counter.count-2}" />
								<a href="/opsCenter/healthcheck/detail/${checkRow.get(8)}/${dates.get(col)}">${status}</a>
							</div>
							</c:when>
							<c:when test="${item=='1'}">
							<div class="col-md-1 div-border" style="background:${colColor} ">
							<c:set var="col" value="${counter.count-2}" />
								<a href="/opsCenter/healthcheck/detail/${checkRow.get(8)}/${dates.get(col)}">${status}</a>
							</div>
							</c:when>
							
							<c:otherwise>
							<div class="col-md-1 div-border" style="background:${colColor} ">
									${status}
							</div>		
							</c:otherwise>
							</c:choose>
							
						</c:when>
					</c:choose>

				</c:forEach>
			</div>
		</c:forEach>
	</div>
</body>
</html>