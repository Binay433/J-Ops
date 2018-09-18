<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<c:set var="disable" value="false"/> 	
<security:authorize access="hasRole('USER')">
	<c:set var="disable" value="true"/>
</security:authorize>
<TR id="row0" style="visibility: visible;">
            <TD style="visibility: hidden;"><form:input type="hidden" path="emergencyChangeComponent[0].id" id="id0"/></TD>
         	<TD><form:input type="text" path="emergencyChangeComponent[0].systemComponent" class="form-control input-sm" disabled="${disable}"/></TD>
         	<TD><form:input type="text" path="emergencyChangeComponent[0].systemPackage"  class="form-control input-sm" disabled="${disable}"/></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[0].tsaDate" id = "datepicker1" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[0].tsahour" disabled="${disable}">
            		<c:forEach items="${change.hourList}" var="hourr" >
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[0].tsaminute" disabled="${disable}">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[0].tsbDate" id = "datepicker2" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[0].tsbhour" disabled="${disable}">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[0].tsbminute" disabled="${disable}">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[0].tscDate" id = "datepicker3" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[0].tschour" disabled="${disable}">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[0].tscminute" disabled="${disable}">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[0].trnDate" id = "datepicker4" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[0].trnhour" disabled="${disable}">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[0].trnminute" disabled="${disable}">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[0].prdDate" id = "datepicker5" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[0].prdhour" disabled="${disable}">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[0].prdminute" disabled="${disable}">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[0].dreDate" id = "datepicker6" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[0].drehour" disabled="${disable}">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[0].dreminute" disabled="${disable}">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
        	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[0].tsdDate" id = "datepicker25" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[0].tsdhour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[0].tsdminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
       	</TR>
         <TR id="row1" style="visibility: visible;">
         	<TD style="visibility: hidden;"><form:input type="hidden" path="emergencyChangeComponent[1].id" id="id1" disabled="${disable}"/></TD>
         	<TD><form:input type="text" path="emergencyChangeComponent[1].systemComponent" class="form-control input-sm" disabled="${disable}"/></TD>
         	<TD><form:input type="text" path="emergencyChangeComponent[1].systemPackage"  class="form-control input-sm" disabled="${disable}"/></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[1].tsaDate" id = "datepicker7" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[1].tsahour" disabled="${disable}">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[1].tsaminute" disabled="${disable}">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[1].tsbDate" id = "datepicker8" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[1].tsbhour" disabled="${disable}">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[1].tsbminute" disabled="${disable}">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[1].tscDate" id = "datepicker9" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[1].tschour" disabled="${disable}">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[1].tscminute" disabled="${disable}">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[1].trnDate" id = "datepicker10" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[1].trnhour" disabled="${disable}">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[1].trnminute" disabled="${disable}">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[1].prdDate" id = "datepicker11" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[1].prdhour" disabled="${disable}">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[1].prdminute" disabled="${disable}">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[1].dreDate" id = "datepicker12" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[1].drehour" disabled="${disable}">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[1].dreminute" disabled="${disable}">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
        	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[1].tsdDate" id = "datepicker26" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[1].tsdhour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[1].tsdminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         </TR>
         <TR id="row2" style="visibility: visible;">
         	<TD style="visibility: hidden;"><form:input type="hidden" path="emergencyChangeComponent[2].id" id="id2" disabled="${disable}"/></TD>
         	<TD><form:input type="text" path="emergencyChangeComponent[2].systemComponent" class="form-control input-sm" disabled="${disable}"/></TD>
         	<TD><form:input type="text" path="emergencyChangeComponent[2].systemPackage"  class="form-control input-sm" disabled="${disable}"/></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[2].tsaDate" id = "datepicker13" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[2].tsahour" disabled="${disable}">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[2].tsaminute" disabled="${disable}">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[2].tsbDate" id = "datepicker14" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[2].tsbhour" disabled="${disable}">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[2].tsbminute" disabled="${disable}">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[2].tscDate" id = "datepicker15" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[2].tschour" disabled="${disable}">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[2].tscminute" disabled="${disable}">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[2].trnDate" id = "datepicker16" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[2].trnhour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[2].trnminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[2].prdDate" id = "datepicker17" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[2].prdhour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[2].prdminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[2].dreDate" id = "datepicker18" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[2].drehour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[2].dreminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
        	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[2].tsdDate" id = "datepicker27" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[2].tsdhour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[2].tsdminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         </TR>
         <TR id="row3" style="visibility: visible;">
         	<TD style="visibility: hidden;"><form:input type="hidden" path="emergencyChangeComponent[3].id" id="id3"/></TD>
         	<TD><form:input type="text" path="emergencyChangeComponent[3].systemComponent" class="form-control input-sm" disabled="${disable}"/></TD>
         	<TD><form:input type="text" path="emergencyChangeComponent[3].systemPackage"  class="form-control input-sm" disabled="${disable}"/></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[3].tsaDate" id = "datepicker19" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[3].tsahour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[3].tsaminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[3].tsbDate" id = "datepicker20" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[3].tsbhour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[3].tsbminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[3].tscDate" id = "datepicker21" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[3].tschour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[3].tscminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[3].trnDate" id = "datepicker22" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[3].trnhour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[3].trnminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[3].prdDate" id = "datepicker23" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[3].prdhour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[3].prdminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[3].dreDate" id = "datepicker24" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[3].drehour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[3].dreminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
        	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[3].tsdDate" id = "datepicker28" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[3].tsdhour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[3].tsdminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         </TR>