<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<c:set var="disable" value="false"/> 	
<security:authorize access="hasRole('USER')">
	<c:set var="disable" value="true"/>
</security:authorize>
<TR id="row4" style="visibility: visible;">
         	<TD style="visibility: hidden;"><form:input type="hidden" path="emergencyChangeComponent[4].id" id="id3"/></TD>
         	<TD><form:input type="text" path="emergencyChangeComponent[4].systemComponent" class="form-control input-sm" disabled="${disable}"/></TD>
         	<TD><form:input type="text" path="emergencyChangeComponent[4].systemPackage"  class="form-control input-sm" disabled="${disable}"/></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[4].tsaDate" id = "datepicker29" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[4].tsahour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[4].tsaminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[4].tsbDate" id = "datepicker30" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[4].tsbhour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[4].tsbminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[4].tscDate" id = "datepicker31" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[4].tschour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[4].tscminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[4].trnDate" id = "datepicker32" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[4].trnhour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[4].trnminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[4].prdDate" id = "datepicker33" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[4].prdhour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[4].prdminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[4].dreDate" id = "datepicker34" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[4].drehour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[4].dreminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
        	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[4].tsdDate" id = "datepicker35" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[4].tsdhour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[4].tsdminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         </TR>
         <TR id="row5" style="visibility: visible;">
         	<TD style="visibility: hidden;"><form:input type="hidden" path="emergencyChangeComponent[5].id" id="id3"/></TD>
         	<TD><form:input type="text" path="emergencyChangeComponent[5].systemComponent" class="form-control input-sm" disabled="${disable}"/></TD>
         	<TD><form:input type="text" path="emergencyChangeComponent[5].systemPackage"  class="form-control input-sm" disabled="${disable}"/></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[5].tsaDate" id = "datepicker36" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[5].tsahour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[5].tsaminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[5].tsbDate" id = "datepicker37" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[5].tsbhour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[5].tsbminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[5].tscDate" id = "datepicker38" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[5].tschour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[5].tscminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[5].trnDate" id = "datepicker39" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[5].trnhour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[5].trnminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[5].prdDate" id = "datepicker40" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[5].prdhour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[5].prdminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[5].dreDate" id = "datepicker41" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[5].drehour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[5].dreminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
        	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[5].tsdDate" id = "datepicker42" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[5].tsdhour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[5].tsdminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         </TR>
         <TR id="row6" style="visibility: visible;">
         	<TD style="visibility: hidden;"><form:input type="hidden" path="emergencyChangeComponent[6].id" id="id3"/></TD>
         	<TD><form:input type="text" path="emergencyChangeComponent[6].systemComponent" class="form-control input-sm" disabled="${disable}"/></TD>
         	<TD><form:input type="text" path="emergencyChangeComponent[6].systemPackage"  class="form-control input-sm" disabled="${disable}"/></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[6].tsaDate" id = "datepicker43" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[6].tsahour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[6].tsaminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[6].tsbDate" id = "datepicker44" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[6].tsbhour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[6].tsbminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[6].tscDate" id = "datepicker45" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[6].tschour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[6].tscminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[6].trnDate" id = "datepicker46" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[6].trnhour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[6].trnminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[6].prdDate" id = "datepicker47" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[6].prdhour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[6].prdminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[6].dreDate" id = "datepicker48" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[6].drehour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[6].dreminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
        	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[6].tsdDate" id = "datepicker49" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[6].tsdhour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[6].tsdminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         </TR>
         <TR id="row7" style="visibility: visible;">
         	<TD style="visibility: hidden;"><form:input type="hidden" path="emergencyChangeComponent[7].id" id="id3"/></TD>
         	<TD><form:input type="text" path="emergencyChangeComponent[7].systemComponent" class="form-control input-sm" disabled="${disable}"/></TD>
         	<TD><form:input type="text" path="emergencyChangeComponent[7].systemPackage"  class="form-control input-sm" disabled="${disable}"/></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[7].tsaDate" id = "datepicker50" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[7].tsahour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[7].tsaminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[7].tsbDate" id = "datepicker51" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[7].tsbhour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[7].tsbminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[7].tscDate" id = "datepicker52" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[7].tschour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[7].tscminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[7].trnDate" id = "datepicker53" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[7].trnhour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[7].trnminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[7].prdDate" id = "datepicker54" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[7].prdhour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[7].prdminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[7].dreDate" id = "datepicker55" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[7].drehour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[7].dreminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
        	<TD width="7%"><form:input type="text" path="emergencyChangeComponent[7].tsdDate" id = "datepicker56" class="form-control input-sm" disabled="${disable}"/>
         	</TD>
         	<TD><label  for="component_description"><strong>Hr&nbsp;&nbsp;</strong></label>
					<form:select path="emergencyChangeComponent[7].tsdhour">
            		<c:forEach items="${change.hourList}" var="hourr">
         				 <form:option value="${hourr}"></form:option>
         			</c:forEach>
        		</form:select>
					<label  for="component_description"><strong>Min</strong></label>
					<form:select path="emergencyChangeComponent[7].tsdminute">
            		<c:forEach items="${change.minutesList}" var="minutes">
         				 <form:option value="${minutes}"></form:option>
         			</c:forEach>
        		</form:select></TD>
         </TR>