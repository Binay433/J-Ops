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
		  	
	<!--<div class="generic-container">
		<div class="panel panel-default">-->
			  <!-- Default panel contents -->
		  	<div class="row">
		  	
		  	<div class="row" style="background-color: <%=System.getProperty("header_background") %>;">
		  	<div class="col-md-4" style="font-size: xx-large;font-family: sans-serif;"><strong>&nbsp;&nbsp;SLA DATED</strong></div>
		  	
		  	<div class="col-md-8">
		  		<security:authorize access="isAnonymous()">
		      		<font color="red">${accessMessage}</font>
		      		<span class="floatRight"><a href="/opsCenter/login"><img src="/opsCenter/static/images/login.png" alt="exit" height="40" width="40"></a></span>
	   			</security:authorize>
			  	<security:authorize access="isAuthenticated()">
			  		<font color="red">${accessMessage}</font>
					<span class="floatRight"><a href="/opsCenter/logout" data-toggle="tooltip" data-placement="left" title="Exit"><img src="/opsCenter/static/images/exit.png" alt="exit" height="40" width="40"></a></span>
			  		<span class="floatRight"><a href="/opsCenter/serviceSla/compact--${currentdate}" data-toggle="tooltip" data-placement="left" title="Email"><img src="/opsCenter/static/images/email.png" alt="Back"  height="40" width="40"></a></span>
			  		<span class="floatRight" style="cursor:pointer"><a class="button" onclick="goBack()" data-toggle="tooltip" data-placement="left" title="Back"><img src="/opsCenter/static/images/back.png" alt="Back"  height="40" width="40"></a></span>
			  		<span class="floatRight"><a href="/opsCenter/home" data-toggle="tooltip" data-placement="left" title="Home"><img src="/opsCenter/static/images/home1.png" alt="Home" height="40" width="40"></a></span>
	   			</security:authorize>
		  	</div>
		  	</div>
			  
		  	
		  	</div>	
		  	<div class="row">
		  	&nbsp;
		  	</div>  	
		  	<c:choose>
		  		<c:when test="${serviceid!=null}">
		  			<c:set var="srvId" value="${serviceid}"/>
		  		</c:when>
		  	</c:choose>
		  	<div class="row">
		  		<div class="col-md-2">&nbsp;</div>
		  		<div class="col-md-1"><a href="/opsCenter/serviceSla/datewise-${prev}-${currentdate}" class="btn btn-level custom-width-lvl" data-toggle="tooltip" data-placement="left" title="Previous"><<</a></div>
		  		  	<c:forEach items="${uidates}" var="date">
				  		<div class="col-md-1"><strong>${date}</strong></div>
				  	</c:forEach>
				 		  		<div class="col-md-1"><a href="/opsCenter/serviceSla/datewise-${next}-${currentdate}" class="btn btn-level custom-width-lvl" data-toggle="tooltip" data-placement="right" title="Next">>></a></div> 
		  	</div>
		  	<c:forEach items="${finalList}" var="sla" varStatus="row">
		  		<div class="row">
					
		  			<div class="col-md-3" style="text-align: right;"><br><strong>${sla.get(0)}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong></div>
		  			<c:forEach items="${sla}" var="item" varStatus="counter">
		  						<c:choose>
				  					<c:when test="${dates.contains(item)}">
				  						<c:set var="ival" value="NA"/>
				  						<c:set var="icol" value="#E5E4E2"/>
				  					</c:when>
				  					<c:when test="${item=='OFF'}">
				  						<c:set var="ival" value="-"/>
				  						<c:set var="icol" value="#ffffb3"/>
				  					</c:when>
				  					<c:otherwise>
				  						<c:set var="ival" value="${item}"/>
				  						<c:choose>
				  							<c:when test="${sla.get(sla.size()-1)=='0'}">
				  								<c:choose>
						  							<c:when test="${item > sla.get(8) || item =='100.0' || item =='100' || item =='0' || item =='0.0'}">
						  							<c:set var="icol" value="#bbff99"/>
						  							</c:when>
						  							<c:when test="${item > sla.get(9) && item < sla.get(8)}">
						  							<c:set var="icol" value="#ffbb99"/>
						  							</c:when>
						  							<c:otherwise>
						  								<c:set var="icol" value="#ff3333"/>
						  							</c:otherwise>
				  								</c:choose>
				  							</c:when>
				  							<c:otherwise>
				  								<c:choose>
						  							<c:when test="${item < sla.get(8)}">
						  							<c:set var="icol" value="#bbff99"/>
						  							</c:when>
						  							<c:when test="${item < sla.get(9) && item > sla.get(8)}">
						  							<c:set var="icol" value="#ffbb99"/>
						  							</c:when>
						  							<c:otherwise>
						  								<c:set var="icol" value="#ff3333"/>
						  							</c:otherwise>
				  								</c:choose>
				  							</c:otherwise>
				  						</c:choose>
				  					</c:otherwise>
			  					</c:choose>
		  						<c:choose>
						  		<c:when test="${counter.count > 1 && counter.count<9}">
						  		<c:choose>
						  			<c:when test="${icol=='#ffbb99' || icol=='#ff3333'}">
				  					<div class="col-md-1 div-border" style="background:${icol} "><a href="#" onclick="showComments('/opsCenter/serviceSla/comments-${slaDates[row.count-2]}-${sla.get(10)}');" >${ival}</a></div>
						  			</c:when>
						  			<c:otherwise>
						  			<div class="col-md-1 div-border" style="background:${icol} ">${ival}</div>
						  			</c:otherwise>
						  		</c:choose>

				  				</c:when>
				  				</c:choose>
		  				
		  			</c:forEach>
		  		</div>
		  	</c:forEach>
			
		<!--</div>
   	</div>-->
</body>

<script type="text/javascript">

function showComments(url) {
    var myWindow = window.open(url,'_blank','height=300,width=300,left=150,top=150,resizable=no,scrollbars=no,toolbar=0,menubar=0,location=0,directories=0,channelmode=0,titlebar=no,addressbar=no, status=0')
}


</script>



</html>