<%--
- form.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.patronage-report.form.label.automatic-sequence-number" path="automaticSequenceNumber"/>
	<%-- <acme:input-moment code="inventor.patronage-report.form.label.creation-moment" path="creationMoment"/>--%>
	<acme:input-textarea code="inventor.patronage-report.form.label.memorandum" path="memorandum"/>
	<acme:input-url code="inventor.patronage-report.form.label.link" path="link"/>
	
	<jstl:choose>
    
        <jstl:when test="${acme:anyOf(command,'show, update, delete, create, publish')}">
               <acme:input-select code="inventor.patronage-report.form.label.patronage.code" path="patronageId">
                   <jstl:forEach items="${patronages}" var="patronage">
                    <acme:input-option code="${patronage.getCode()}" value="${patronage.getId()}" selected="${ patronage.getId() == patId }"/>
                </jstl:forEach>
            </acme:input-select>
           
           </jstl:when>
</jstl:choose>


<acme:input-checkbox code="inventor.patronage-report.form.label.confirmation" path="confirmation"/>
<acme:submit code="inventor.patronage-report.form.button.create" action="/inventor/patronage-report/create"/>			
	
	
</acme:form>


