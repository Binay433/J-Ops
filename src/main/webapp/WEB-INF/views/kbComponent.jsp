<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>jOps Search</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  	<script src="/opsCenter/static/script/comon.js"></script>	
</head>
<body>


	<div class="generic-container1">
		<div class="panel panel-default1">
			<div class="row" style="background-color: <%=System.getProperty("header_background") %>;">
				<div class="col-md-12">
						  	<span class="floatRight"><a href="/opsCenter/logout" data-toggle="tooltip" data-placement="left" title="Exit"><img
										src="/opsCenter/static/images/exit.png" alt="exit" height="40"
										width="40"></a></span>
								<span class="floatRight" style="cursor: pointer"><a href="/opsCenter/kb/search"
									class="button" data-toggle="tooltip" data-placement="left" title="Back"><img
										src="/opsCenter/static/images/back.png" alt="Back" height="40"
										width="40"></a></span> <span class="floatRight"><a
									href="/opsCenter/home" data-toggle="tooltip" data-placement="left" title="Home"><img
										src="/opsCenter/static/images/home1.png" alt="Home" height="40"
										width="40"></a></span>
				</div>
			</div>
	<form:form method="POST" modelAttribute="kbComponent" class="form-horizontal" enctype="multipart/form-data"
	onsubmit="return submitForm();">
					<c:choose>
						<c:when test="${kbComponent.id!=null && kbComponent.createdby.id!=user.id}">
							<c:set var="disabled" value="true"/>
							<div class="row" style="border: thin;border-color: buttontext;">
								<div class="col-md-10" align="right" >
								<table style="font-size: 12; color: blue;">
								<tr><td>Author</td><td align="left">${kbComponent.createdby.firstName}&nbsp;${kbComponent.createdby.lastName}</td></tr>
								<tr><td colspan="2">Please contact author for any query or change in this knowledge item</td></tr>
								</table>
								</div>
								<div class="col-md-2">&nbsp;</div>
							</div>
							
						</c:when>
						<c:otherwise>
							<c:set var="disabled" value="false"/>
						</c:otherwise>
					</c:choose>
					<security:authorize access="hasAnyRole('ADMIN')">
						<c:set var="disabled" value="false"/>
					</security:authorize>	
	
	
	
	
	<br>
		<div class="row" style="border: thin;border-color: buttontext;">
			<div class="col-md-3" align="left">
				<label  for="component_keywords"><strong>Key Words /Search tags (separated by space)</strong></label>
			</div>
			<div class="col-md-6">
					<form:input type="text" path="keywords" id="keywords" maxlength="120" class="form-control input-sm" disabled="${disabled}"/>
					<form:input type="hidden" path="spadded" id="spadded" />
					<div class="has-error">
						<form:errors path="keywords" class="help-inline"/>
					</div>
			</div>
		</div>
		<br>
		<div class="row" style="border: thin;border-color: buttontext;">
			<div class="col-md-3" align="left">
				<label  for="component_description"><strong>Description</strong></label>
			</div>
			<div class="col-md-6">
					<form:textarea id="description" class="form-control input-sm" rows="7" path="description"  disabled="${disabled}"/>
					<div class="has-error">
						<form:errors path="description" class="help-inline"/>
					</div>
			</div>
		</div>
		<br>
		<div class="row" style="border: thin;border-color: buttontext;">
			<div class="col-md-3" align="left">
				<label  for="component_solution"><strong>Suggested Solution</strong></label>
			</div>
			<div class="col-md-6">
					<form:textarea id="solution" class="form-control input-sm" rows="9" path="solution"  disabled="${disabled}"/>
					<div class="has-error">
						<form:errors path="solution" class="help-inline"/>
					</div>
			</div>
		</div>
		<security:authorize access="hasRole('ADMIN')">
		<br>
		<div class="row" style="border: thin;border-color: buttontext;">
			<div class="col-md-3" align="left">
				<label  for="component_solution"><strong>Content Approved ?</strong></label>
			</div>
			<div class="col-md-6">
					<form:checkbox path="approved"/>
					<div class="has-error">
						<form:errors path="approved" class="help-inline"/>
					</div>
			</div>
		</div>
		</security:authorize>
		<br>
		<div class="row" style="border: thin;border-color: buttontext;">
			<div class="col-md-3" align="left">
				<label  for="component_solution"><strong>Documents</strong></label>
			</div>
			<div class="col-md-6">
					<TABLE id="dataTable">
					<c:choose>
						<c:when test="${kbComponent.id==null}">
							<TR>
								<TD  width="350px"><form:input type="file" path="files" id="Upload" title="update"/></TD>
								
								<TD  width="350px"><form:button onclick="deleteRow(this,0)" >
								<img src="/opsCenter/static/images/deleteBin.png" height="20" width="20">
								</form:button> </TD>
							</TR>
						</c:when>
						<c:otherwise>
						<c:choose>
						<c:when test="${kbComponent.documents.size() > 0}">
						<c:forEach items="${kbComponent.documents}" var="document" varStatus="counter">
							<TR hidden="true">
								<TD width="350px"><form:input type="file" path="files" id="Upload" title="update"/></TD>
								<TD  width="350px"><button onclick="deleteRow(this,0)">
								<img src="/opsCenter/static/images/deleteBin.png" height="20" width="20">
								</button>dfgfdg </TD>
							</TR>
							
							<TR>
								<TD  width="350px"><a href="<c:url value='/download-document-${document.id}' />">${document.name}</a></TD>
								<security:authorize access="hasAnyRole('ADMIN')">
								<TD  width="350px"><button onclick="deleteRow(this,${document.id})">
								Delete<img src="/opsCenter/static/images/deleteBin.png" height="20" width="20">
								</button> </TD>
								</security:authorize>
							</TR>
						</c:forEach>
						</c:when>
						<c:otherwise>
						<c:choose>
						<c:when test="${kbComponent.createdby.id==user.id}">
							<TR>
								<TD  width="350px"><form:input type="file" path="files" id="Upload" title="update"/></TD>
								
								<TD  width="350px"><form:button onclick="deleteRow(this,0)" >
								<img src="/opsCenter/static/images/deleteBin.png" height="20" width="20">
								</form:button> </TD>
							</TR>
						</c:when>
						<c:otherwise>
							<security:authorize access="hasAnyRole('ADMIN')">
							<TR>
								<TD  width="350px"><form:input type="file" path="files" id="Upload" title="update"/></TD>
								<TD  width="350px"><form:button onclick="deleteRow(this,0)" >
								<img src="/opsCenter/static/images/deleteBin.png" height="20" width="20">
								</form:button> </TD>
							</TR>
							</security:authorize>
						</c:otherwise>
						</c:choose>
						</c:otherwise>
					</c:choose>
						
						</c:otherwise>
					</c:choose>
					
					
					</TABLE> 
					
					<c:choose>
						<c:when test="${kbComponent.id==null}">
						<TABLE id="buttons">
								<TR>
								<TD ><a href="#" onclick="addRow('dataTable')" >
								<img src="/opsCenter/static/images/addrow.png">
								</a></TD>
								</TR>
						</TABLE>
						</c:when>
						<c:otherwise>
						<c:choose>
						<c:when test="${kbComponent.createdby.id==user.id}">
						
								<TABLE id="buttons">
								<TR>
								<TD ><a href="#" onclick="addRow('dataTable')" >
								<img src="/opsCenter/static/images/addrow.png">
								</a></TD>
								</TR>
								</TABLE>
					
						</c:when>
						<c:otherwise>
						<security:authorize access="hasAnyRole('ADMIN')">
								<TABLE id="buttons">
								<TR>
								<TD ><a href="#" onclick="addRow('dataTable')" >
								<img src="/opsCenter/static/images/addrow.png">
								</a></TD>
								</TR>
								</TABLE>
					</security:authorize>
						</c:otherwise>
					</c:choose>
						</c:otherwise>
					</c:choose>
					
					
					
					
					
					
			</div>
		</div>
		<security:authorize access="isAuthenticated()">
		<div class="row">
		<br>
			<div class="col-md-3">
			&nbsp;
			</div>
			<div class="col-md-6">	
			<c:choose>
				<c:when test="${kbComponent.id==null}">	
				<input type="submit" value="Save Item" onclick="validateForm();" class="btn btn-primary btn-sm" />
				</c:when>
				<c:otherwise>
				<c:choose>
						<c:when test="${kbComponent.createdby.id==user.id}">
				<input type="submit" value="Update" onclick="validateForm();" class="btn btn-primary btn-sm"/>
				</c:when>
				<c:otherwise>
					<security:authorize access="hasAnyRole('ADMIN')">
						<input type="submit" value="Update" onclick="validateForm();" class="btn btn-primary btn-sm"/>
					</security:authorize>
				</c:otherwise>
				</c:choose>
				</c:otherwise>
			</c:choose>
			</div>
		</div>
		</security:authorize>
	</form:form>
    </div>
    </div>    
<script>

var submit =false;

function deleteRow(r,docid) {
	
	var table = document.getElementById("dataTable");
	var rowCount = table.rows.length;
	
	if(rowCount < 2){
		alert("Cannot delete all the rows.");
	}else{
		 var i = r.parentNode.parentNode.rowIndex;
	    	document.getElementById("dataTable").deleteRow(i);
	    	if(docid > 0){
	        	window.open("/opsCenter/doc/delete-"+docid, "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=400,left=500,width=300,height=200");    		
	    	}	
	} 
}

function chooseFile() {
    document.getElementById("Upload").click();
 }
function submitForm(){
	return submit; 
}
function validateForm(){
	var keywords= document.getElementById("keywords").value;
	var description= document.getElementById("description").value;
	if(keywords=="" || description==""){
		alert("Please Enter proper information in Keywords and Description");
	}else{
		submit=true;	
	}
}
</script>
</body>
</html>