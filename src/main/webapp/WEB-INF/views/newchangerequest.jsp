<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Change Request Form</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
  	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
 	<script src="/opsCenter/static/script/comon.js"></script> 

</head>

<body>

 	<div class="generic-container1">

	<div class="row" style="background-color: <%=System.getProperty("header_background") %>;">
		  	<div class="col-md-6" style="font-size: xx-large;font-family: sans-serif;"><strong>J-OPS : Emergency Change Request</strong></div>
		  	
		  	<div class="col-md-6">
	<span class="floatRight"><a href="#" onclick="goBack()" data-toggle="tooltip" data-placement="left" title="Back"><img src="/opsCenter/static/images/back.png" alt="Back"  height="40" width="40"></a></span>
 	<span class="floatRight"><a href="/opsCenter/home" data-toggle="tooltip" data-placement="left" title="Home"><img src="/opsCenter/static/images/home1.png" alt="Home"  height="40" width="40"></a></span>
		  	</div>
	</div>
	<div class="row">&nbsp;</div>
		
		

		
		
		
 	<form:form method="POST" modelAttribute="change" class="form-horizontal" >
 	
			<c:set var="disable" value="false"/> 	
 			<security:authorize access="hasRole('USER')">
				<c:set var="disable" value="true"/>
			</security:authorize>
 	
		<form:input type="hidden" path="id" id="id"/>
 		<form:input type="hidden" path="saveOnly" id="saveOnly"/>
	<div class="row" style="border: thin;border-color: buttontext;">
		<div class="col-md-3" align="left">
			<label  for="component_description"><strong>Change Category</strong></label>
		</div>
		<div class="col-md-3">
			<form:select path="changeCategory" class="form-control input-sm" disabled="${disable}">
				<form:option value=" Break Fix "></form:option>
				<form:option value=" E-CAB "></form:option>
				<form:option value=" Expedite "></form:option>
			</form:select>
				<div class="has-error">
					<form:errors path="changeCategory" class="help-inline"/>
				</div>
		</div>
		<div class="col-md-3" align="left">
			<label  for="component_description"><strong>Change Environment</strong></label>
		</div>
		<div class="col-md-3">
			<form:select path="changeEnvironment" class="form-control input-sm" disabled="${disable}">
				<form:option value=" Production "></form:option>
				<form:option value=" FOF "></form:option>
				<form:option value=" UAT "></form:option>
				<form:option value=" SecA "></form:option>
				<form:option value=" System Test "></form:option>
				<form:option value=" DRE "></form:option>
			</form:select>
				<div class="has-error">
					<form:errors path="changeEnvironment" class="help-inline"/>
				</div>
		</div>
	</div>
	<br>
	<div class="row" >
		<div class="col-md-3" align="left">
			<label  for="component_description"><strong>Email To</strong></label>
		</div>
			<div class="col-md-3">
				<form:input type="text" path="emailTo" id="emailTo" class="form-control input-sm" readonly="true"/>
				<div class="has-error">
					<form:errors path="emailTo" class="help-inline"/>
				</div>
			</div>
		<div class="col-md-3" align="left">
			<label  for="component_description"><strong>Email CC</strong></label>
		</div>
			<div class="col-md-3">
				<form:input type="text" path="emailCc" id="emailCc" class="form-control input-sm" readonly="true"/>
			<div class="has-error">
				<form:errors path="emailCc" class="help-inline"/>
			</div>
		</div>
	</div>
	<br>

<div class="row">
	<div class="col-md-3" align="left">
			<label  for="component_description"><strong>RCM Email</strong></label>
	</div>
	<div class="col-md-3">
				<form:input type="text" path="rcmMail" id="rcmMail" class="form-control input-sm" readonly="true"/>
				<div class="has-error">
					<form:errors path="rcmMail" class="help-inline"/>
				</div>
	</div>
	<div class="col-md-3" align="left">
			<label  for="component_description"><strong>Also CC</strong></label>
	</div>
	<div class="col-md-3">
				<form:input type="text" path="additionalCc" id="additionalCc" class="form-control input-sm" disabled="${disable}"/>
				(please use comma to separate multiple email IDs)
				<div class="has-error">
					<form:errors path="additionalCc" class="help-inline"/>
				</div>
	</div>
</div>
<br>
<div class="row">
				
				<div class="col-md-3" align="left">
				<label  for="name"><strong>Subject Line</strong></label>
				</div>
				<div class="col-md-3">
					<form:input type="text" path="subjectline" id="crq" class="form-control input-sm" disabled="${disable}"/>
					<div class="has-error">
						<form:errors path="subjectline" class="help-inline"/>
					</div>
				</div>
				<div class="col-md-6" align="left">	&nbsp;</div>
				
				<div class="col-md-3" align="left">
				<label  for="name"><strong>Defect Count</strong></label>
				</div>
				<div class="col-md-3">
					<form:input type="text" path="defectCount" id="defectCount" class="form-control input-sm" disabled="${disable}"/>
					<div class="has-error">
						<form:errors path="defectCount" class="help-inline"/>
					</div>
				</div>
			</div>
			<br>
<div class="row">
			
				<div class="col-md-3" align="left">
				<label  for="name"><strong>CRQ Reference</strong></label>
				</div>
				<div class="col-md-3">
					<form:input type="text" path="crqRef" id="crq" class="form-control input-sm" disabled="${disable}"/>
					<div class="has-error">
						<form:errors path="crqRef" class="help-inline"/>
					</div>
				</div>
			
		
		
		
		
				<div class="col-md-3" align="left">
				<label  for="name"><strong>Incidence Ref:</strong></label>
				</div>
				<div class="col-md-3">
					<form:input type="text" path="incidenceRef" id="incidenceRef" class="form-control input-sm" disabled="${disable}"/>
					<div class="has-error">
						<form:errors path="incidenceRef" class="help-inline"/>
					</div>
				</div>
			</div>
		
		<br>
		<div class="row">
			
				<div class="col-md-3" align="left">
				<label  for="name"><strong>RCM Lead</strong></label>
				</div>
				<div class="col-md-3">
					<form:input type="text" path="rcmLead" id="rcmLead" class="form-control input-sm" disabled="${disable}"/>
					<div class="has-error">
						<form:errors path="rcmLead" class="help-inline"/>
					</div>
				</div>
				
				<div class="col-md-3" align="left">
				<label  for="name"><strong>Requestor</strong></label><br>
				
				</div>
				<div class="col-md-3">
					<form:input type="text" path="changeRequestor" id="changeRequestor" class="form-control input-sm" disabled="${disable}"/>
					<div class="has-error">
						<form:errors path="changeRequestor" class="help-inline"/>
					</div>
				</div>
			
		</div>
		<br>
		<div class="row">
			
				<div class="col-md-3" align="left">
				<label  for="component_description"><strong>Summary:</strong></label>
				<label  for="name">&nbsp;&nbsp;&nbsp;i.e. brief description of change</label>
				</div>
				<div class="col-md-3">
					<form:textarea id="description" class="form-control input-sm" rows="4" path="description" disabled="${disable}"/>
					<div class="has-error">
						<form:errors path="description" class="help-inline"/>
					</div>
				</div>
				
				<div class="col-md-3" align="left">
				<label  for="component_description"><strong>Impact Assessment:</strong></label><br>
				<label  for="name">i.e. impact of implementing change and impact of not doing change in proposed timeframe</label>
				</div>
				<div class="col-md-3">
					<form:textarea id="impactAssesment" class="form-control input-sm" rows="4" path="impactAssesment" disabled="${disable}"/>
					<div class="has-error">
						<form:errors path="impactAssesment" class="help-inline"/>
					</div>
				</div>
			
		</div>
		<br>
		<div class="row">
			
				<div class="col-md-3" align="left">
				<label  for="component_description"><strong>Reason for Urgency:</strong></label><br>
				<label  for="name">&nbsp;&nbsp;&nbsp;i.e. why must change be implemented with this degree<br>&nbsp;&nbsp; of urgency, e.g. why Expedited and not a Release</label>
				</div>
				<div class="col-md-3">
					<form:textarea id="urgencyReason" class="form-control input-sm" rows="4" path="urgencyReason" disabled="${disable}"/>
					<div class="has-error">
						<form:errors path="urgencyReason" class="help-inline"/>
					</div>
				</div>
				
				<div class="col-md-3" align="left">
				<label  for="component_description"><strong>Risk mitigation:</strong></label><br>
				<label  for="name">i.e. what risks are there to the production services and what steps have been taken to minimize the potential impact of change.
Describe what testing will be done and how easy it will be to back out change.
</label>
				</div>
				<div class="col-md-3">
					<form:textarea id="riskMitigation" class="form-control input-sm" rows="4" path="riskMitigation" disabled="${disable}"/>
					<div class="has-error">
						<form:errors path="riskMitigation" class="help-inline"/>
					</div>
				</div>
			
		</div>
		<br>
		<div class="row">
			
				<div class="col-md-3" align="left">
				<label  for="component_description"><strong>Customer(s) affected:</strong></label><br>
				<label  for="name">&nbsp;&nbsp;&nbsp;i.e. which customers will be impacted by change</label>
				</div>
				<div class="col-md-3">
					<form:textarea id="customerAffected" class="form-control input-sm" rows="4" path="customerAffected" disabled="${disable}"/>
					<div class="has-error">
						<form:errors path="customerAffected" class="help-inline"/>
					</div>
				</div>
				
				<div class="col-md-3" align="left">
				<label  for="component_description"><strong>Communication:</strong></label>
				<label  for="name">i.e. what comms will be sent, whether Market Comm and/or Service Centre Notification will be sent and forecast dates of publication (incl. for Training & Testing)</label>
				</div>
				<div class="col-md-3">
					<form:textarea id="communication" class="form-control input-sm" rows="4" path="communication" disabled="${disable}"/>
					<div class="has-error">
						<form:errors path="communication" class="help-inline"/>
					</div>
				</div>
			
		</div>
		<br>
		
		<div class="row">
			
				<div class="col-md-3" align="left">
				<label  for="component_description"><strong>Back-out Plan:</strong></label><br>
				<label  for="name">&nbsp;&nbsp;&nbsp;i.e. what is the back-out plan and whether it can be achieved &nbsp;&nbsp;&nbsp;within the change window</label>
				</div>
				<div class="col-md-3">
					<form:textarea id="backoutPlan" class="form-control input-sm" rows="4" path="backoutPlan" disabled="${disable}"/>
					<div class="has-error">
						<form:errors path="backoutPlan" class="help-inline"/>
					</div>
				</div>
			
				<div class="col-md-3" align="left"><br>
				<label  for="name"><strong>System/Support Manager</strong></label>
				</div>
				<div class="col-md-3">
				<br>
					<form:input type="text" path="systemManager" id="systemManager" class="form-control input-sm" disabled="${disable}"/>
					<div class="has-error">
						<form:errors path="systemManager" class="help-inline"/>
					</div>
				</div>
				
				</div>
				
				
				
		</div>
		<br>
		

		<br>
		<div class="row">
			
				<div class="col-md-3" align="left">
				<label  for="component_description"><strong>Status</strong></label>
				</div>
				<div class="col-md-3">
					<form:select path="status" class="form-control input-sm" onchange="showRemarks(this.value)" disabled="${disable}">
            		<c:forEach items="${change.statusList}" var="statuss">
         				 <form:option value="${statuss.getKey()}">${statuss.getValue()}</form:option>
         			</c:forEach>
        		</form:select>
					<div class="has-error">
						<form:errors path="status" class="help-inline"/>
					</div>
				</div>
				
				
				
				<div class="col-md-3" align="left">
				<!--  <label class="col-md-3 control-lable" for="services"><strong>Services</strong></label> -->
				<label  for="component_description"><strong>Services</strong></label>
				</div>
				<div class="col-md-3">
						  
						<form:select path="services" items="${serviceModels}" itemValue="id" itemLabel="name" class="form-control input-sm" disabled="${disable}"/>
						
						<div class="has-error">
						<form:errors path="services" class="help-inline"/>
					</div>
				</div>
			
			
		</div>
		<br>
		<div class="row">
			
				<div class="col-md-3" align="left">
				<label  for="component_description"><strong>Critical</strong></label><br>
				<label  for="name">&nbsp;&nbsp;&nbsp;i.e. Number of Criticals increased in Sonar check.</label>
				</div>
				<div class="col-md-3">
					<form:input type="text" id="critical" class="form-control input-sm" path="critical" disabled="${disable}"/>
					<div class="has-error">
						<form:errors path="critical" class="help-inline"/>
					</div>
				</div>
			
				<div class="col-md-3" align="left"><br>
				<label  for="name"><strong>Blocker</strong></label>
				<label  for="name">&nbsp;&nbsp;&nbsp;i.e. Number of Blockers increased in Sonar check.</label>
				</div>
				<div class="col-md-3">
					<form:input type="text" path="bloker" id="blokerr" class="form-control input-sm" disabled="${disable}"/>
					<div class="has-error">
						<form:errors path="bloker" class="help-inline"/>
					</div>
				</div>
				
				</div>
				
				
		<br>
		<div class="row">
			
				<div class="col-md-3" align="left">
				<label  for="component_description"><strong>Align to</strong></label>
				</div>
				
				<div class="col-md-9">
					<form:checkboxes items="${environments}" path="environments" itemValue="id" itemLabel="name"/>
					<%-- <form:select path="services" items="${environments}" itemLabel="name" itemValue="id" class="form-control input-sm" disabled="${disable}"/> --%>
				</div>
			
			
		</div>
		
		<c:set var="remarkdiv" value="hidden"/>
		<c:choose>
			<c:when test="${change.status=='4' || change.status=='5' || change.status=='6'|| change.status=='7'}">
				<c:set var="remarkdiv" value="visible"/>
			</c:when>
		</c:choose>
		
		<div id = "remarkdiv" class="row" style="visibility: ${remarkdiv};">
			<div class="col-md-3" align="left">
				<label  for="component_description"><strong>Remarks</strong></label>
			</div>
			<div class="col-md-3">
					<form:textarea id="remarksid" class="form-control input-sm" rows="3" path="remarks" disabled="${disable}"/>
					<div class="has-error">
						<form:errors path="remarks" class="help-inline"/>
					</div>
				</div>
		</div>
		<br>
		<div class="row">
			
		</div>
		<!--  <input type="button" id = "addRowId" value="Add Row" onclick="addRow('row')" class="btn btn-primary btn-sm" /> 
		<input type="button" id = "deleteRowId" value="Delete Row" onclick="deleteRow('row')" class="btn btn-primary btn-sm" /> -->
		
		<TABLE id="dataTable" border="1" style="margin-left: 1%;">
        <TR>
        	<TD style="visibility: hidden;"></TD>
            <TD><label  for="component_description"><strong>System Component:</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></TD>
            <TD><label  for="component_description"><strong>System Package:</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></TD>
            <TD><label  for="component_description"><strong>TSA Date </strong> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></TD>
           	<TD><label  for="component_description"><strong>TSA Time </strong></label></TD>
            <TD><label  for="component_description"><strong>TSB Date </strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></TD>
            <TD><label  for="component_description"><strong>TSB Time </strong></label></TD>
            <TD><label  for="component_description"><strong>TSC Date </strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></TD>
           	<TD><label  for="component_description"><strong>TSC Time </strong></label></TD>
            <TD><label  for="component_description"><strong>TRN Date </strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></TD>
            <TD><label  for="component_description"><strong>TRN Time </strong></label></TD>
            <TD><label  for="component_description"><strong>PRD Date </strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></TD>
           	<TD><label  for="component_description"><strong>PRD Time </strong></label></TD>
            <TD><label  for="component_description"><strong>DRE Date </strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></TD>
           	<TD><label  for="component_description"><strong>DRE Time </strong></label></TD>
           	<TD><label  for="component_description"><strong>TSD Date </strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></TD>
           	<TD><label  for="component_description"><strong>TSD Time </strong></label></TD>
         </TR>
          
         <jsp:include page="newchangerow.jsp"/>
         <jsp:include page="newAdditionalChangeRows.jsp"/>
         
    	</TABLE>
		
		
	
		<br>
	<security:authorize access="hasRole('OPS')">
		<div class="row">
			<div class="form-group col-md-12" align="center">
				<div class="form-actions">
				<c:choose>
				<c:when test="${edit && (cStatus == 3 || cStatus == 4)}">
						<input type="submit" value="Update" class="btn btn-primary btn-sm" onclick="return beforeSubmit()"/> &nbsp;&nbsp;&nbsp; <input type="submit" id="updateEmailId" style="visibility: hidden;" value="Update and Email" class="btn btn-primary btn-sm" onclick="sendMail()"/>
					</c:when>
					<c:when test="${edit && cStatus!='3'}">
						<input type="submit" value="Update" class="btn btn-primary btn-sm" onclick="return beforeSubmit()"/> &nbsp;&nbsp;&nbsp; <input type="submit" id="updateEmailId" value="Update and Email" class="btn btn-primary btn-sm" onclick="sendMail()"/>
					</c:when>
					<c:when test="${edit && cStatus!='4'}">
						<input type="submit" value="Update" class="btn btn-primary btn-sm" onclick="return beforeSubmit()"/> &nbsp;&nbsp;&nbsp; <input type="submit" id="updateEmailId" value="Update and Email" class="btn btn-primary btn-sm" onclick="sendMail()"/>
					</c:when>
					<c:otherwise>
						<input type="submit" value="Save" class="btn btn-primary btn-sm" onclick="return beforeSubmit()"/> &nbsp;&nbsp;&nbsp; <input type="submit" value="Submit and Email" class="btn btn-primary btn-sm" onclick="sendMail()"/>
					</c:otherwise>
				</c:choose>
			</div>
			</div>
		</div>
		</security:authorize>
		
		
		<!--  <input type="button" value="Add Row" onclick="addRow('row')"> -->



	</form:form>
	</div>
	
</body>

<script type="text/javascript">
var cnt1=0;
var cnt2=4;
function setDate(date){
	alert("");
	//document.getElementById("changedate").value=date;
}

function beforeSubmit(){
/* 	if(document.getElementById('remarkdiv').style.visibility == "visible"){
		if(document.getElementById("remarksid").value == "" || document.getElementById("remarksid").value == null){
			alert("Please add Remarks");
			
		}
		return false;
	}
	else{
		return submit;
	} */
	return submit;
}

function sendMail(){
	
	document.getElementById("saveOnly").value=false;
	beforeSubmit();
}

function addRow(itemID){ 
    // Toggle visibility between none and '' 
    if(cnt2>=1){
    	cnt1 = 0;
    	document.getElementById('addRowId').disabled="";
    }
    cnt1=parseInt(cnt1)+parseInt(1);
    //alert("Hello"+(itemID+cnt));
    if(cnt1>3)
    	{
    	alert("Maximum number of Rows have been added and no new row can be added.");
    	document.getElementById('addRowId').disabled="disabled";
    	}
    else{
    //document.getElementById(itemID).style.display = "";
    document.getElementById(itemID+cnt1).style.visibility = "visible";
    }
    //alert("Hello1"+cnt);
     
}

function deleteRow(itemID){ 
    // Toggle visibility between none and '' 
    
    cnt2=parseInt(cnt2)-parseInt(1);
    //alert("Hello"+(itemID+cnt));
    if(cnt2<1)
    	{
    	alert("There is only 1 row left and it can not be deleted.");
    	document.getElementById('deleteRowId').disabled="disabled";
    	}
    else{
    //document.getElementById(itemID).style.display = "";
    document.getElementById(itemID+cnt2).style.visibility = "hidden";
    }
    //alert("Hello1"+cnt);
     
}

function showRemarks(selectValue){
	if(selectValue == 3 || selectValue == 4){
		
		document.getElementById('updateEmailId').style.visibility = "hidden";
	}
	else{
		document.getElementById('updateEmailId').style.visibility = "visible";
	}
	
	//alert("I m here with value = "+selectValue);
	if(selectValue == 4 || selectValue == 5 || selectValue == 6 || selectValue == 7){
		document.getElementById("remarksid").value = "";
		document.getElementById('remarkdiv').style.visibility = "visible";
	}
	else{
		document.getElementById("remarksid").value = "";
		document.getElementById('remarkdiv').style.visibility = "hidden";
		
	}
}

</script>
</html>