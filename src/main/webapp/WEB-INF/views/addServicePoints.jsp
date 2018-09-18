<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <script src="/opsCenter/static/script/comon.js"></script>	
<title>Service Points</title>

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
	<form:form method="POST" modelAttribute="sp" class="form-horizontal">
	
		<div class="row">
			<div class="col-md-1" >&nbsp;</div>
			<div class="col-md-3" >Select User</div>
			<div class="col-md-3" ><form:select path="user" items="${users}" multiple="false"  
	  			itemValue="id" itemLabel="firstName" class="form-control input-sm"/></div>
		</div>
		<div class="row">
			<div class="col-md-1" >&nbsp;</div>	
			<div class="col-md-3" >Service Points</div>
			<div class="col-md-3" >
			<form:input path="point" id="point" class="form-control input-sm" onkeyup="isNumber(this)"/>
			</div>
		</div>
		<div class="row">
			<div class="col-md-1" >&nbsp;</div>
			<div class="col-md-3" >Description</div>
			<div class="col-md-3" >
			<form:textarea path="description" id="description" class="form-control input-sm"/>
			</div>
		</div>
		<div class="row">
			<div class="col-md-1" >&nbsp;</div>
			<div class="col-md-6" ><input type="submit" value="Add" class="btn btn-primary btn-sm"/></div>
			
		</div>
	
	</form:form>
	
	<c:forEach items="${spList}" var="record" varStatus="counter">
				<c:choose>
						    <c:when test="${counter.count%2==0}">
								<c:set var="background" value='<%=System.getProperty("row_even") %>'/>
							</c:when>
							<c:otherwise>
							    <c:set var="background" value='<%=System.getProperty("row_odd") %>'/>
						    </c:otherwise>
				</c:choose>
				<div class="row" style="background-color:${background}">
						<div class="col-md-1">
							<label><strong>&nbsp;</strong></label>
						</div>
						<div class="col-md-2">
							<label><strong>${record.user.firstName}  ${record.user.lastName}</strong></label>
						</div>
						<div class="col-md-2">
							<label><strong>${record.point}</strong></label>
						</div>
						<div class="col-md-3">
							<label><strong>${record.description}</strong></label>
						</div>
						<div class="col-md-3">
							<a href="javascript:confirmDelete('/opsCenter/SP/delete-${record.id}-false')"class="btn btn-danger custom-width"><img src="/opsCenter/static/images/delete.png" alt="exit" height="20" width="30" data-toggle="tooltip" data-placement="left" title="Delete"></a>
						</div>

				</div>
			</c:forEach>
			<c:choose>
				<c:when test="${spList.size() > 0 }">
				<div class="row">
				<div class="col-md-1" >&nbsp;</div>
				<div class="col-md-5"><a href="/opsCenter/SP/updateAll"><input type="submit" value="Submit" class="btn btn-primary btn-sm"/></a></div>
					
				</div>
					
				</c:when>
			</c:choose>
</div>
</div>
	
	
</body>
<script language="JavaScript" type="text/JavaScript"> 
function isNumber(field) { 
        //var re = /^[0-9]*$/; 
        var re =/^[-]?\d{0,5}(\.\d{0,2})?$/
        if (!re.test(field.value)) { 
            alert('Value must be all numberic charcters, including "." or "," non numerics will be removed from field!'); 
            field.value = field.value.replace(/[^0-9]/g,""); 
        } 
    }
    
    
    

</script> 
</html>