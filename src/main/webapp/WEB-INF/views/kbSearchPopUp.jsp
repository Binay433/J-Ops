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

<body style="background-color: white">


	<div class="generic-container1">
		<div class="panel panel-default1">
	<form:form method="POST" modelAttribute="search" enctype="multipart/form-data" class="form-horizontal">
	<br>
					<div class="row">
					<div class="form-group col-md-6" align="Right">
							<img src="/opsCenter/static/images/jops1.jpg"/>
					</div>
					<div class="form-group col-md-6">&nbsp;</div>
					</div>
	
				<div class="row">
					<div class="form-group col-md-12">
					<div class="form-group col-md-3">&nbsp;	</div>
					<div class="form-group col-md-6">
							<span class="floatLeft"><form:input type="text" path="searchText" id="searchText" class="form-control input-sm" /></span>
					</div>
					<div class="form-group col-md-3">
					<input type="submit" value="Search" class="btn btn-primary btn-sm" />
					</div>
					<br><br>
					
					<div class="row">
						<c:choose>
								<c:when test="${kblist.size()==0}">
								<div class="row">
									<div class="col-md-12" align="center">
									<font color="blue"> No record found</font>
									</div>
									</div>
								</c:when>
								<c:otherwise>
						<c:forEach items="${kblist}" var="kb" varStatus="counter">
										<div class="row">
										<div class="col-md-12">
											<div class="row">
											<div class="col-md-1" align="Center"><input type="button" value="Select" class="btn btn-primary btn-sm" onclick="setKbId(${kb.id})"/></div>
											<div class="col-md-11"> 
												<div class="row">
													<div class="col-md-11">
															<a href="#" onclick="openKB('/opsCenter/kb/searchpopup-${kb.id}');"><font size="3" color="blue"><strong>${kb.keywords}</strong></font></a>
													</div>
												</div>
												<div class="row">
													<div class="col-md-11">
															<a href="#" onclick="openKB('/opsCenter/kb/searchpopup-${kb.id}');"><font size="3" color="blue"><strong>${kb.description}</strong></font></a>
													</div>
												</div>
											</div>
											</div>
											<div class="row">
												<div class="col-md-1"></div>
												<div class="col-md-11">
												<strong>${kb.solution}</strong>
												</div>
											</div>
											<hr>
										</div>
										</div>
										<br>
						</c:forEach>
						</c:otherwise>
						</c:choose>
					</div>
					
					</div>
				</div>
	</form:form>
    </div>
    <c:choose>
		<c:when test="${pages.size() > 1}">
			<div class="row">
				<div class="col-md-2" align="right">
					<c:choose>
						<c:when test="${previous < 0}">
						Previous
						</c:when>
						<c:otherwise>
							<a href="/opsCenter/kb/popuppage-${txt}-${previous}-${maxResult}">Previous</a>					
						</c:otherwise>
					</c:choose>
				</div>
					<div class="col-md-9" align="center">
						<c:forEach items="${pages}" var="page" varStatus="counter">
							<c:choose>
								<c:when test="${page[0]==offset}">
									${counter.count}
								</c:when>
								<c:otherwise>
									<a href="/opsCenter/kb/popuppage-${txt}-${page[0]}-${maxResult}">${counter.count}&nbsp;</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
					<div class="col-md-1" align="left">
						<c:choose>
							<c:when test="${next==0}">
							Next
						</c:when>
						<c:otherwise>
							<a href="/opsCenter/kb/popuppage-${txt}-${next}-${maxResult}">Next</a>			
						</c:otherwise>
						</c:choose>
					</div>
				</div>
			</c:when>
		</c:choose>
    </div>    

</body>
<script>
function setKbId(val){
	 var rowId=sessionStorage.getItem("rowId");
	 
	 var fieldName ="records["+rowId+"].kbId";
    if (window.opener != null && !window.opener.closed) {
    	window.opener.document.getElementById(fieldName).value=val;
    }
    window.close();
}


function openKB(url){
	 popup = window.open(url, "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=150,left=500,width=600,height=500");
	 popup.focus();
} 

</script>


</html>