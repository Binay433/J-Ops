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
<SCRIPT language="javascript">
		function addRow(tableID) {

			var table = document.getElementById(tableID);

			var rowCount = table.rows.length;
			var row = table.insertRow(rowCount);

			var colCount = table.rows[0].cells.length;

			for(var i=0; i<colCount; i++) {

				var newcell	= row.insertCell(i);

				newcell.innerHTML = table.rows[0].cells[i].innerHTML;
				//alert(newcell.childNodes);
				switch(newcell.childNodes[0].type) {
					case "text":
							newcell.childNodes[0].value = "";
							break;
					case "checkbox":
							newcell.childNodes[0].checked = false;
							break;
					case "select-one":
							newcell.childNodes[0].selectedIndex = 0;
							break;
				}
			}
		}

		function deleteRow(tableID) {
			try {
			var table = document.getElementById(tableID);
			var rowCount = table.rows.length;

			for(var i=0; i<rowCount; i++) {
				var row = table.rows[i];
				var chkbox = row.cells[0].childNodes[0];
				if(null != chkbox && true == chkbox.checked) {
					if(rowCount <= 1) {
						alert("Cannot delete all the rows.");
						break;
					}
					table.deleteRow(i);
					rowCount--;
					i--;
				}


			}
			}catch(e) {
				alert(e);
			}
		}

	</SCRIPT>
</head>

<body>
	<form:form method="POST" modelAttribute="contaner" class="form-horizontal" >
	

		<TABLE id="dataTable" width="850px" border="1">
			<TR>
				
				<TD width="10%">&nbsp;</td>
				<TD  width="20%">
	 			<form:select path="servicePointsRecords[0].user" items="${users}" multiple="false"  
	  			itemValue="id" itemLabel="firstName" class="form-control input-sm" disabled="${disable}"/>  
	 			</TD>
				<TD  width="10%"><form:input type="text" path="servicePointsRecords[0].point"/></TD>
				<TD  width="10%"><form:input type="text" path="servicePointsRecords[0].description"/></TD>
				 <TD  width="20%">	<INPUT type="button" value="Add Row" onclick="addRow('dataTable')" /> 
				 	<INPUT type="button" value="Delete Row" onclick="deleteRow('dataTable')" /> 
				 </TD>
			</TR>
		</TABLE>
	<input type="submit" value="SAVE" class="btn btn-primary btn-sm"/>
	</form:form>
</body>
</html>