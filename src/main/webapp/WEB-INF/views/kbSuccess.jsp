<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>KB Success</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body>
<table width="100%">
<tr><td width="100%" align="center">&nbsp;</td></tr>
<tr><td width="100%" align="center">&nbsp;</td></tr>
<tr><td width="100%" align="center">&nbsp;</td></tr>
<tr><td width="100%" align="center">&nbsp;</td></tr>
<tr><td width="100%" align="center">&nbsp;</td></tr>
<tr><td width="100%" align="center">&nbsp;</td></tr>
<tr><td width="100%" align="center">${message}</td></tr>
<tr><td width="100%" align="center"><a href="/opsCenter/kb/list---"><input type="button" value="Ok"></a> </td></tr>
</table>

</body>
</html>