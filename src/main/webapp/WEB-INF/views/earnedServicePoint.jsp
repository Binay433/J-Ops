<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Summary of Service Points Earned</title>
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
		  	<div class="col-md-7">&nbsp;</div>
		  	<div class="col-md-5">
		  	<span class="floatRight"><a href="/opsCenter/logout"  data-toggle="tooltip" data-placement="left" title="Exit"><img src="/opsCenter/static/images/exit.png" alt="exit" height="40" width="40"></a></span> 
				<span class="floatRight" style="cursor:pointer"><a class="button" onclick="goBack()"  data-toggle="tooltip" data-placement="left" title="Back"><img src="/opsCenter/static/images/back.png" alt="Back"  height="40" width="40"></a></span>
				<span class="floatRight"><a href="/opsCenter/home"  data-toggle="tooltip" data-placement="left" title="Home"><img src="/opsCenter/static/images/home1.png" alt="Home" height="40" width="40"></a></span>
		  	</div>
		  	</div><br>
		 	<form:form method="POST" modelAttribute="spContainer" class="form-horizontal">
		 	<div class="row">
		 	<div class="col-md-5"  align="right">
		 	<font size="3" style="color: silver;">Service points Earned from approved Knowledge Items</font>
		 	</div>
		 	<div class="col-md-5"  align="right">
		 	<a href="/opsCenter/SP/newSPScreen"><input type="button" value="Add Service Points" class="btn btn-primary btn-sm" /></a>
		 	<a href="/opsCenter/SP/list"><input type="button" value="View Service Points" class="btn btn-primary btn-sm" /></a>
		 	</div>
		 	</div>
		 	<c:choose>
		 		<c:when test="${spContainer.servicePointsRecords.size()==0}">
		 		<div class="row">
						        		<div class="col-md-10"  align="center"><font size="10">No Records found to be appended</font></div>
		 		</div>
		 		</c:when>
		 		<c:otherwise>
			 		<c:forEach items="${spContainer.servicePointsRecords}" var="record" varStatus="counter">
							        		<c:set var="rowCount" value="${counter.count}"/>
							        		<c:choose>
												    <c:when test="${counter.count%2==0}">
												        	<c:set var="background" value='<%=System.getProperty("row_even") %>'/>
												    </c:when>
												    <c:otherwise>
												        <c:set var="background" value='<%=System.getProperty("row_odd") %>'/>
												    </c:otherwise>
											</c:choose>
							        	<div class="row" style="background-color:${background}">
							        		<div class="col-md-1"  align="Right"><font size="3">${counter.count}</font></div>
							        		<div class="col-md-2"><font size="3">${record.user.firstName}&nbsp;${record.user.lastName}</font></div>
							        		<div class="col-md-2"><font size="1">${record.point}</font></div>
							        		<div class="col-md-3">
												<a href="javascript:confirmDelete('/opsCenter/SP/delete-${record.id}-true')"class="btn btn-danger custom-width"><img src="/opsCenter/static/images/delete.png" alt="exit" height="20" width="30" data-toggle="tooltip" data-placement="left" title="Delete"></a>
											</div>
							        	</div>
					 </c:forEach>
					 			<c:choose>
									<c:when test="${spContainer.servicePointsRecords.size() > 0 }">
										<div class="row">
										<div class="col-md-1" >&nbsp;</div>
										<div class="col-md-5"><input type="submit" value="Append" class="btn btn-primary btn-sm" /></div>
										</div>
									</c:when>
								</c:choose>
					
		 		</c:otherwise>
		 	</c:choose>
		 	
		 	
		 	
		 		

	</form:form>
	</div>
	</div>
</body>

</html>