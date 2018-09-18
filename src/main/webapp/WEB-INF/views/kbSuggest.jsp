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


	<div class="row generic-container1">
			<div class="row" style="background-color: <%=System.getProperty("header_background") %>;">
				<div class="col-md-12">
						  	<span class="floatRight"><a href="/opsCenter/logout" data-toggle="tooltip" data-placement="left" title="Exit"><img
										src="/opsCenter/static/images/exit.png" alt="exit" height="40"
										width="40"></a></span>
							 <span class="floatRight">
										<a href="/opsCenter/home" data-toggle="tooltip" data-placement="left" title="Home"><img
										src="/opsCenter/static/images/home1.png" alt="Home" height="40"
										width="40"></a></span>
				</div>
			</div>
			<div class="row">
			<div class="col-md-12">
				<form:form method="POST" modelAttribute="suggest" enctype="multipart/form-data" class="form-horizontal">
							<br><br>
							<div class="row">
								<div class="form-group col-md-3" align="right">Suggest Keyword	</div>
								<div class="form-group col-md-1" align="right">&nbsp;</div>
								<div class="form-group col-md-5">
									<span class="floatLeft"><form:input type="text" path="keyword" id="keyword" 
									class="form-control input-sm" /></span>
								</div>
								<div class="form-group col-md-3">&nbsp;	</div>
							</div>
							<div class="row">
								<div class="form-group col-md-3" align="right">Enter description</div>
								<div class="form-group col-md-1" align="right">&nbsp;</div>
								<div class="form-group col-md-5">
									<span class="floatLeft"><form:textarea type="text" path="description" id="description" 
									cols="20" rows="3" class="form-control input-sm" /></span>
								</div>
								<div class="form-group col-md-3">&nbsp;	</div>
							</div>
							<div class="row">
								<div class="form-group col-md-3">&nbsp;	</div>
								<div class="form-group col-md-9">
									<input type="submit" value="Submit" class="btn btn-primary btn-sm" />
								</div>
							</div>

				</form:form>
				</div>
			</div>
			
    </div>
  

</body>
</html>