<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<c:choose>
<c:when test="${success}">
Document uploaded successfully
<button onclick="closeMe();">Close</button>

</c:when>
<c:otherwise>
<form:form method="POST" modelAttribute="relDoc" class="form-horizontal" enctype="multipart/form-data">
<form:input type="hidden" path="id" id="id"/>
<form:input type="hidden" path="type" id="type"/>
<form:input type="hidden" path="parentId" id="parentId"/>
<form:input type="file" path="docFile" id="Upload" title="update"/>
<input type="submit" value="Submit" class="btn btn-primary btn-sm"/>
</form:form>
</c:otherwise>
</c:choose>




</body>
<script type="text/javascript">
function closeMe()
{
    window.opener = self;
    location.reload();
    window.close();
}

</script>


</html>