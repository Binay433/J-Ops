<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<body style=background-color:white;>

<div class="row" style="background-color: <%=System.getProperty("header_background") %>;">
		  	<div class="col-md-4" style="font-size: xx-large;font-family: sans-serif;"><strong>&nbsp;&nbsp;SLA REPORT</strong></div>
		  	
		  	<div class="col-md-8">
		  		<security:authorize access="isAnonymous()">
		      		<font color="red">${accessMessage}</font>
		      		<span class="floatRight"><a href="/opsCenter/logout" data-toggle="tooltip" data-placement="left" title="Exit"><img src="/opsCenter/static/images/exit.png" alt="exit" height="40" width="40"></a></span>
	   			</security:authorize>
			  	<security:authorize access="isAuthenticated()">
			  		<font color="red">${accessMessage}</font>
					<span class="floatRight" style="cursor: pointer"><a
					class="button" onclick="goBack()" data-toggle="tooltip" data-placement="left" title="Back"><img
						src="/opsCenter/static/images/back.png" alt="Back" height="40"
						width="40"></a></span>
			  		<span class="floatRight"><a href="/opsCenter/home" data-toggle="tooltip" data-placement="left" title="Home"><img src="/opsCenter/static/images/home1.png" alt="Home" height="40" width="40"></a></span>
	   			</security:authorize>
		  	</div>
		  	</div><br>
			<div class="row">
				<div class="col-md-1">&nbsp;</div>
				<div class="col-md-10">
					<table border="1"  style="width:100%; border: 2px solid black;" class="format">
					 <tr>
						<th class="format">S.No</th>
					  	<th width=15% class="format" >Name</th>		
						<th width=40% >Description</th>	
						<th width=5% class="format" >Target</th>
						<th width=5% class="format" >Current Month</th>			
						<th width=10% class="format" >Avg. SLA required for remaining month to go Green</th>	
						<th width=10% class="format" >Month End SLA Forecast</th>	
						<!--<th class="format" style=background-color:#d3d3d3>${day2}</th>
						<th class="format" style=background-color:#d3d3d3>${day1}</th>-->
					</tr>
					<c:forEach items="${finalList}" var="row" varStatus="count">
						<tr>
							<td class="format" align="center">${row[0]}</td>
						  	<td width=5% class="format" >${row[1]}</td>		
							<td width=40% >${row[2]}</td>	
							<td width=5% class="format" >${row[3]}</td>
							<%-- <td width=10% class="format" >${row[4]}</td> --%>
							<c:if test="${not empty row[8]}">
   							<c:choose>
							<c:when	test="${row[8].equals('R') && !row[4].equals('NA') }">
								<td width=20% class="format" style="background-color: #ff3333">${row[4]}</td>
							</c:when>
							<c:when test="${row[8].equals('A') && !row[4].equals('NA')}">
								<td width=20% class="format" style="background-color: #ffbb99;">${row[4]}</td>
							</c:when>
							<c:when test="${row[8].equals('G') && !row[4].equals('NA')}">
								<td width=20% class="format" style="background-color: #bbff99">${row[4]}</td>
							</c:when>
							<c:otherwise>
								<td width=20% class="format" >NA</td>
							</c:otherwise>
							</c:choose>
							</c:if>
							<c:if test="${empty row[8]}">
							<c:choose>
							<c:when	test="${row[7].equals('R') && !row[4].equals('NA') }">
								<td width=20% class="format" style="background-color: #ff3333">${row[4]}</td>
							</c:when>
							<c:when test="${row[7].equals('A') && !row[4].equals('NA')}">
								<td width=20% class="format" style="background-color: #ffbb99;">${row[4]}</td>
							</c:when>
							<c:when test="${row[7].equals('G') && !row[4].equals('NA')}">
								<td width=20% class="format" style="background-color: #bbff99">${row[4]}</td>
							</c:when>
							<c:otherwise>
								<td width=20% class="format" >NA</td>
							</c:otherwise>
							</c:choose>	
							</c:if>		
							<td width=20% class="format" >${row[5]}</td> 										
    						<c:choose>
							<c:when	test="${row[7].equals('R') && !row[4].equals('NA') }">
								<td width=20% class="format" style="background-color: #ff3333">${row[6]}</td>
							</c:when>
							<c:when test="${row[7].equals('A') && !row[4].equals('NA')}">
								<td width=20% class="format" style="background-color: #ffbb99;">${row[6]}</td>
							</c:when>
							<c:when test="${row[7].equals('G') && !row[4].equals('NA')}">
								<td width=20% class="format" style="background-color: #bbff99">${row[6]}</td>
							</c:when>
							<c:otherwise>
								<td width=20% class="format" >NA</td>
							</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
					</table>
				</div>
			</div><br><br>
			<security:authorize access="hasRole('OPS')">
			<div class="row">
			<form:form action="/opsCenter/serviceSla/sendmail" method="POST" modelAttribute="sendMail" class="form-horizontal">
				<div class="col-md-12">
					<div class="row">
						<form:input type="hidden" path="date" id="date" value="${sendMail.date}"/>
						<div class="col-md-1">&nbsp;</div>
						<div class="col-md-2"><strong>Add Special Comments</strong></div>
						<div class="col-md-8"><form:textarea path="comments" id="comments" rows="4" cols="145"/></div>
					</div>
					<div class="row">
							<div class="col-md-1">&nbsp;</div>
					  		<!-- <div class="col-md-1"><a href="/opsCenter/serviceSla/compact-${currentdate}" class="btn btn-level">Send Mail</a></div>-->
					  		<div class="col-md-1"><input type="submit" value="Send Mail" class="btn btn-primary btn-sm" ${sent}/></div>
					  		<div class="col-md-3"><font size="2" color="red">${mailsent}</font></div>
					</div>
				</div>
			</form:form>
			
			
			
			

		  	</div>
			</security:authorize>
		
<br><br>

	</body>
</html>