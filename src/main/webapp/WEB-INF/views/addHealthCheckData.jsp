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
		  	<div class="col-md-7" style="font-size: xx-large;font-family: sans-serif;"><strong>START:${recordEntry.name}</strong></div>
		  	
		  	<div class="col-md-5">
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
		  	</div><br>
 	<form:form method="POST" modelAttribute="recordEntry" class="form-horizontal" onsubmit="return beforeSend()">
 		
		  <div class="row my_brown">
			<div class="form-group col-md-12">
				<div class="col-md-1">&nbsp;</div>
				<div class="col-md-2" align="left">
				<label  for="component_description"><font size="3"><strong>&nbsp;&nbsp;Check Name</strong></font></label>
				</div>
				<div class="col-md-3">
					<form:input type="hidden" path="check" id="check" value="${hcId}" class="form-control input-sm"/>
					<form:input type="text" path="name" id="name" class="form-control input-sm" disabled="true"/>	
					<form:input type="hidden" path="saveOption" id="saveOption" class="form-control input-sm" value="save"/>	
				</div>
				<div class="col-md-1">&nbsp;</div>
				<div class="col-md-2" align="left"><strong><font size="3">&nbsp;&nbsp;Entry for Date</font></strong></div>
				        	<div class="col-md-2" align="right">
				        		<form:input type="text" path="entryDate" id = "datepicker" value="${recordEntry.entryDate}" class="datepicker form-control input-sm"/>
				        		<!--<form:input type="text" id="entryDate" path="entryDate" value="${recordEntry.entryDate}" class="form-control input-sm"/>-->
				</div>
			</div>
		</div>
		<div class="row my_brown">
				<div class="col-md-12">
						<div class="row" >
							<div class="col-md-1" align="Right"><strong><font size="3">Number</font></strong></div>
				        	<div class="col-md-2"><strong><font size="3">Service</font></strong></div>
				        	<div class="col-md-2"><strong><font size="3">Component</font></strong></div>
				        	<div class="col-md-2"><strong><font size="3">Status</font></strong></div>
				        	<div class="col-md-4"><strong><font size="3">Knowledge Item</font></strong></div>
						</div>
						<div class="row my_brown">
				&nbsp;
				</div>
							<c:set var="rowCount" value="0"/>
				        <c:forEach items="${recordEntry.records}" var="record" varStatus="counter">
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
				        		<form:input type="hidden" path="records[${counter.index}].healthCheck" value="${record.healthCheck.id}"/>
								<form:input type="hidden" path="records[${counter.index}].serviceModel" value="${record.serviceModel.id}"/>
					        	<div class="col-md-2"><font size="3">${record.serviceModel.name}</font></div>
					        	<form:input type="hidden" path="records[${counter.index}].component" value="${record.component.id}"/>
					        	<div class="col-md-2"><font size="3">${record.component.name}</font></div>
					        	
								<c:choose>
									<c:when test="${record.status=='2' || record.status=='3'}">
									<c:set var="comment" value="visible"/>
									</c:when>
									<c:otherwise>
								<c:set var="comment" value="hidden"/>
									</c:otherwise>
								</c:choose>
					        	
					        	
					        	
					        	<div class="col-md-2"><font size="3">
								<form:select id="records[${counter.index}].status" path="records[${counter.index}].status" onchange="showHideComments(this.value,${counter.index})" class="form-control input-sm">
									<form:option value="0">--select--</form:option>
									<form:option value="1">OK</form:option>
									<form:option value="2">Warning</form:option>
									<form:option value="3">Issue</form:option>
								</form:select>
					        	</font></div>
        	
					        	<div class="col-md-2" id="records[${counter.index}].comments" style="visibility:${comment}">
						        		
						        			<form:input path="records[${counter.index}].kbId" id="records[${counter.index}].kbId" size="5" readonly="true"/>
						        		<a href="#" onclick="searchKB(${counter.index});">Search</a>
								</div>
					        	<div class="col-md-2" id="records[${counter.index}].comments1" style="visibility:${comment}">
								        	<font size="2">
								        		<form:textarea id="records[${counter.index}].comment" path="records[${counter.index}].comments" rows="2" 
								        		cols="30" class="form-control input-sm"/>
								        	</font>					        		
						        	
					
								</div>
				        	</div>
					    </c:forEach>

					<div class="has-error">
						<form:errors path="name" class="help-inline"/>
					</div>
				</div>
		</div>
		  

		
	
		<div class="row">
			<div class="form-group col-md-12" align="center">
				<div class="form-actions">
				<c:choose>
					<c:when test="${edit}">
						<input type="submit" value="Update" class="btn btn-primary btn-sm"/><input type="button" value="cancel" class="btn btn-primary btn-sm"/>
					</c:when>
					<c:otherwise>
						<input type="submit" value="Save" class="btn btn-primary btn-sm" onclick="setSaveOption('save','${rowCount}');"/>&nbsp;<input type="submit" value="Submit & Email" class="btn btn-primary btn-sm" onclick="setSaveOption('submit','${rowCount}');"/>&nbsp; 
						<a href="<c:url value='/healthcheck/list'/>">
						<input type="button" value="cancel" class="btn btn-primary btn-sm"/>
						
						</a>
					</c:otherwise>
				</c:choose>
			</div>
			</div>
		</div>
	</form:form>
	</div>
	</div>
</body>


<script type="text/javascript">
var submit =true;
function setSaveOption(option,rowCount){
	document.getElementById("saveOption").value=option;
	for(var i=1; i< rowCount+1; i++){
		var status =document.getElementById("records["+i+"].status").value;
		var comment =document.getElementById("records["+i+"].comment").value;
		if(status > 1 && (comment.trim()=="" || comment=="undefined")){
			alert("Please enter comments in case of Issue and warning !");
			submit=false;
			return;
		}else if(status==0){
			alert('Please select valid status');
			submit=false;
			return;
		}else{
			submit=true;
		}
	}

}



function showHideComments(action,row){

	if(action==0){
		alert('Please select valid status');
	}else if(action==1){
		document.getElementById("records["+row+"].comments").style.visibility = "hidden"; 
		document.getElementById("records["+row+"].comments1").style.visibility = "hidden"; 
	}else{
		 document.getElementById("records["+row+"].comments").style.visibility = "visible"; 
		 document.getElementById("records["+row+"].comments1").style.visibility = "visible"; 
	}
	
}

function setDate(date){
	document.getElementById("entryDate").value=date;
}

function beforeSend(){
	
	return submit;
}

 function searchKB(rowId){
	 sessionStorage.setItem("rowId", rowId); 
	 popup = window.open("/opsCenter/kb/searchpopup", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=100,left=200,width=1050,height=500");
	 popup.focus();
 } 
</script>




</html>