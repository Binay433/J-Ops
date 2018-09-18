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
				<form:form method="POST" modelAttribute="search" enctype="multipart/form-data" class="form-horizontal">
							<br>
							<div class="row">
							<div class="form-group col-md-6">&nbsp;</div>
							<div class="form-group col-md-6" align="Right">
								<span class="floatRight" style="cursor: pointer">
								<a class="button" href="<c:url value='/kb/newKBcomp'/>" data-toggle="tooltip" data-placement="left" title="add new KB">
								<img src="/opsCenter/static/images/addkb.png" alt="add new KB"></a></span>
							</div>
							</div>

<%-- 							<security:authorize access="hasRole('ADMIN')"> --%>
							<div class="row">
							<div class="form-group col-md-6">&nbsp;</div>
							<div class="form-group col-md-6" align="Right">
								<span class="floatRight" style="cursor: pointer">
								<a class="button" href="<c:url value='/kb/list---'/>" data-toggle="tooltip" data-placement="left" title="Manage">
								<img src="/opsCenter/static/images/manageKb.png" alt="Manage"></a></span>
							</div>
							</div>
<%-- 							</security:authorize> --%>
							<div class="row">
								<div class="form-group col-md-12" align="center">
										<img src="/opsCenter/static/images/jops1.jpg"/>
								</div>
								<div class="form-group col-md-12" align="center">
										(Enclose search text in double quotes for exact search.)
								</div>
							</div>
							<div class="row">
								<div class="form-group col-md-3">&nbsp;	</div>
								<div class="form-group col-md-6">
									<span class="floatLeft"><form:input type="text" path="searchText" id="searchText" class="form-control input-sm" /></span>
								</div>
								<div class="form-group col-md-3">
									<input type="submit" value="Search" class="btn btn-primary btn-sm" />
								</div>
							</div>
							<br><br>
							<div class="row">
								<c:choose>
								<c:when test="${kblist.size()==0}">
										<div class="col-md-12" align="center">
									<font color="blue"> No record found, if you want to raise case please 
									<a href="/opsCenter/kb/suggest">click</a> here </font>
									</div>

								</c:when>
								<c:otherwise>
								<c:forEach items="${kblist}" var="kb" varStatus="counter">
												<div class="col-md-12">
													<div class="row">
													<div class="col-md-1">&nbsp;</div>
													<div class="col-md-11">
															<a href="/opsCenter/kb/search-${kb.id}" ><font size="3" color="blue"><strong>${kb.description}</strong></font></a>
													</div>
													</div>
													<div class="row">
													<div class="col-md-1">&nbsp;</div>
													<div class="col-md-11">
															<a href="/opsCenter/kb/search-${kb.id}" ><font color="green"><strong>${kb.keywords}</strong></font></a>
													</div>
													</div>
													<div class="row">
														<div class="col-md-1">&nbsp;</div>
														<div class="col-md-11">
														<c:choose>
															<c:when test="${kb.solution!=null && kb.solution.length() > 400}">
																${kb.solution.substring(0,400)}														
															</c:when>
															<c:otherwise>
																		${kb.solution}
															</c:otherwise>
														</c:choose>

														</div>
													</div>
													<hr>
												</div>
												
												
												<br>
								</c:forEach>
								</c:otherwise>
								</c:choose>
								<c:choose>
								<c:when test="${pages.size() > 1}">
								<div class="row">
									<div class="col-md-2" align="center">
									<c:choose>
										<c:when test="${previous < 0}">
											Previous
										</c:when>
										<c:otherwise>
											<a href="/opsCenter/kb/page-${txt}-${previous}-${maxResult}">Previous</a>					
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
												<a href="/opsCenter/kb/page-${txt}-${page[0]}-${maxResult}">${counter.count}&nbsp;</a>
											</c:otherwise>
										</c:choose>
					
									</c:forEach>
									</div>
									<div class="col-md-1" align="center">
										<c:choose>
											<c:when test="${next==0}">
												Next
											</c:when>
											<c:otherwise>
												<a href="/opsCenter/kb/page-${txt}-${next}-${maxResult}">Next</a>			
											</c:otherwise>
										</c:choose>
									
									</div>
								</div>
								</c:when>
							</c:choose>
							</div>
				</form:form>
				</div>
			</div>
			
    </div>
  

</body>
</html>