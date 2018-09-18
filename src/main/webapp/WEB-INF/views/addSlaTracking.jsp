<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>SLA tracking Form</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <script src="/opsCenter/static/script/comon.js"></script>	
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
</head>

<body>

 	<div class="generic-container1">
 		<div class="panel panel-default1">
	<div class="row" style="background-color: <%=System.getProperty("header_background") %>;">
		  	<div class="col-md-4" style="font-size: xx-large;font-family: sans-serif;"><strong>SLA ENTERY</strong></div>
		  	
		  	<div class="col-md-8">
			  		<span class="floatRight"><a href="/opsCenter/logout" data-toggle="tooltip" data-placement="left" title="Exit"><img src="/opsCenter/static/images/exit.png" alt="exit" height="40" width="40"></a></span>				
					<security:authorize access="isAuthenticated()">
			  		<font color="red">${accessMessage}</font>
					<security:authorize access="hasRole('ADMIN')">
					<span class="floatRight"><a href="<c:url value='/services/newservice'/>" data-toggle="tooltip" data-placement="left" title="Add New"><img src="/opsCenter/static/images/add.png" alt="add" height="40" width="40"></a></span>
					</security:authorize>
			  		<span class="floatRight" style="cursor:pointer"><a class="button" onclick="goBack()" data-toggle="tooltip" data-placement="left" title="Back"><img src="/opsCenter/static/images/back.png" alt="Back"  height="40" width="40"></a></span>
			  		<span class="floatRight" style="cursor:pointer"><a href="/opsCenter/home" data-toggle="tooltip" data-placement="left" title="Home"><img src="/opsCenter/static/images/home1.png" alt="Home" height="40" width="40"></a></span>
					</security:authorize>
		  	</div>
		  	</div><br>
 	<form:form method="POST" modelAttribute="entry" class="form-horizontal">
		  
		<div class="row my_brown">
				<div class="row">
							
				        	<div class="col-md-3" align="right"><strong><font size="3">&nbsp;&nbsp;Entry for Date</font></strong></div>
				        	<div class="col-md-2" align="right">
				        		<form:input type="text" path="entrydate" id = "datepicker" value="${entrydate}" class="form-control input-sm"/>
				        		<font color="red">${entrydateErr}</font>
							<input type="button" value="Reload" class="btn btn-primary btn-sm" onclick="reload()"/>
							</div>
							
							
							<!-- <div class="col-md-1" align="left"><input type="date" class="input-dtbtn" onchange="setDate(this.value);"/></div>-->
				</div>
			</div><br>	
			<div class="panel panel-default" style="background-color: #f5f5f5">
				<div class="row">

							<div class="col-md-1">&nbsp;</div>
				        	<div class="col-md-1"><strong><font size="3">Service</font></strong></div>
				        	<div class="col-md-1"><strong><font size="3">SLA Name</font></strong></div>
				        	<div class="col-md-1"><strong><font size="3">Target</font></strong></div>
				        	<div class="col-md-1"><strong><font size="3">Items Passed</font></strong></div>
				        	<div class="col-md-1"><strong><font size="3">Total Items</font></strong></div>
				        	<div class="col-md-1"><strong><font size="3">SLA</font></strong></div>
				        	<div class="col-md-3"><strong><font size="3">Comments</font></strong></div>
				</div>
				<c:choose>
					<c:when test="${commonErr!=null && commonErr!=''}">
				<div class="row">
					<div class="col-md-1">&nbsp;</div>
					<div class="col-md-11" align="left"><font color="red">${commonErr}</font></div>						
				</div>
				</c:when></c:choose>
				<hr>		
				<div class="col-md-12">
				        <c:forEach items="${entry.transactions}" var="record" varStatus="counter">
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
				        		<form:input type="hidden" id="transactions[${counter.index}].serviceSla" path="transactions[${counter.index}].serviceSla" value="${record.serviceSla.id}"/>
				        		</div>
				        		<div class="col-md-1">${record.serviceSla.serviceModel.name}</div>
				        		<div class="col-md-1">${record.serviceSla.name}</div>
				        		<c:choose>
				        			<c:when test="${record.comments!=''}">
				        				<c:set var="visible" value="visible"/>
				        			</c:when>
				        			<c:otherwise>
				        				<c:set var="visible" value="hidden"/>
				        			</c:otherwise>
				        		</c:choose>
				        		
				        		
				        		
				        		<c:choose>
				        		<c:when test="${record.serviceSla.sla_type==0}">
				        		<div class="col-md-1">${record.serviceSla.targetGreen}%</div>
				       			<div class="col-md-1"><form:input type="text" id="transactions[${counter.index}].passedRecord" path="transactions[${counter.index}].passedRecord"  class="form-control input-sm"/></div>
				       			<div class="col-md-1"><form:input type="text" id="transactions[${counter.index}].totalRecord" path="transactions[${counter.index}].totalRecord" onblur="calculate(${counter.index},${record.serviceSla.targetGreen},${record.serviceSla.sla_type});" class="form-control input-sm"/></div>
				       			<div class="col-md-1"><form:input type="text" id="transactions[${counter.index}].percent" path="transactions[${counter.index}].percent" readOnly="true" class="form-control input-sm"/></div>
				        		<div class="col-md-3"><form:textarea id="transactions[${counter.index}].comments" path="transactions[${counter.index}].comments" style="visibility:${visible}" class="form-control input-sm"/></div>
				        		</c:when>
				        		<c:otherwise>
				        		<div class="col-md-1">${record.serviceSla.targetGreen}</div>
				       			<div class="col-md-1"><form:input type="text" id="transactions[${counter.index}].passedRecord" path="transactions[${counter.index}].passedRecord"  class="form-control input-sm" onblur="calculate(${counter.index},${record.serviceSla.targetGreen},${record.serviceSla.sla_type});"/></div>
				       			<div class="col-md-1"><form:input type="text" id="transactions[${counter.index}].totalRecord" path="transactions[${counter.index}].totalRecord" readOnly="true" onblur="calculate(${counter.index},${record.serviceSla.targetGreen});" class="form-control input-sm"/></div>
				       			<div class="col-md-1"><form:input type="text" id="transactions[${counter.index}].percent" path="transactions[${counter.index}].percent" readOnly="true" class="form-control input-sm"/></div>
				        		<div class="col-md-3"><form:textarea id="transactions[${counter.index}].comments" path="transactions[${counter.index}].comments" style="visibility:${visible}" class="form-control input-sm"/></div>
			        		
				        		</c:otherwise>
				        		</c:choose>
				        		
				        	</div>
					    </c:forEach>


				</div>

		  </div>

		
	
		<div class="row">
			<div class="form-group col-md-5" align="right">
				<div class="form-actions">
				<c:choose>
					<c:when test="${edit}">
						<input type="submit" value="Update" class="btn btn-primary btn-sm" onclick="beforeSave(${entry.transactions.size()})"/> <a href="<c:url value='/serviceSla/list' />"><input type="button" value="cancel" class="btn btn-primary btn-sm"/></a>
					</c:when>
					<c:otherwise>
						<input type="submit" value="Submit" onclick="beforeSave(${entry.transactions.size()})" class="btn btn-primary btn-sm"/>&nbsp;
						<input type="submit" value="Cancel" onclick="goBack()" class="btn btn-primary btn-sm"/>
						<!-- <a href="<c:url value='/serviceSla/list' />"><input type="button" value="cancel" class="btn btn-primary btn-sm"/></a>-->
					</c:otherwise>
				</c:choose>
			</div>
			</div>
		</div>
		
	</form:form>
</div></div>
</body>


<script type="text/javascript">

function calculate(row,green,sla_type){
	if(sla_type==0){
	var tot =document.getElementById("transactions["+row+"].totalRecord").value; 
	var pass =document.getElementById("transactions["+row+"].passedRecord").value;
	var percent=pass/tot;
	var num=percent*100;

	if(!isNaN(num)){
		document.getElementById("transactions["+row+"].percent").value=num.toFixed(2);
	}
	if(tot!=0 && pass!=0){
			if(num < green){
				document.getElementById("transactions["+row+"].comments").readOnly=false;
				document.getElementById("transactions["+row+"].comments").style.visibility = "visible"; 
			}else{
				document.getElementById("transactions["+row+"].comments").readOnly=true;
				document.getElementById("transactions["+row+"].comments").style.visibility = "hidden"; 
			}		
		}
	}else{
		
		var pass =document.getElementById("transactions["+row+"].passedRecord").value;
		document.getElementById("transactions["+row+"].percent").value=pass;
		
			if(pass > green){
				document.getElementById("transactions["+row+"].comments").readOnly=false;
				document.getElementById("transactions["+row+"].comments").style.visibility = "visible"; 
			}else{
				document.getElementById("transactions["+row+"].comments").readOnly=true;
				document.getElementById("transactions["+row+"].comments").style.visibility = "hidden"; 
			}
	}
	
}


function beforeSave(count){
	for(var i=0; i<count; i++){
		document.getElementById("transactions["+count+"].percent").readOnly=false;
	}
}


function setDate(date){
	document.getElementById("entrydate").value=date;
}


function reload(){
	var date= document.getElementById("datepicker").value;
	date =replaceSubstring(date,"-", "");
	var url ='/opsCenter/serviceSla/newsladata-'+date;
	window.open(url,"_self");
	
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