<%--
- form.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this artefact. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.toolkit.form.label.code" path="code"/>
	<acme:input-textarea code="inventor.toolkit.form.label.title" path="title"/>
	<acme:input-textbox code="inventor.toolkit.form.label.description" path="description"/>
	<acme:input-textarea code="inventor.toolkit.form.label.assembly-notes" path="assemblyNotes"/>
	<acme:input-url code="inventor.toolkit.form.label.link" path="link"/> 	
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command,'show, delete, update, publish')}">
			<acme:input-money code="inventor.toolkit.form.label.total-price" path="totalPrice" readonly="true"/>
			<acme:button code="inventor.toolkit.form.button.tool" action="/inventor/quantity/list?masterId=${toolkitId}&type=${0}"/>
			<acme:button code="inventor.toolkit.form.button.component" action="/inventor/quantity/list?masterId=${toolkitId}&type=${1}"/> 
			
			<jstl:if test="${ published == false }">
				<acme:submit code="inventor.toolkit.form.button.update" action="/inventor/toolkit/update"/>
				<acme:submit code="inventor.toolkit.form.button.delete" action="/inventor/toolkit/delete"/>
				<acme:submit code="inventor.toolkit.form.button.publish" action="/inventor/toolkit/publish?id=${toolkitId}"/>
			</jstl:if>	
		</jstl:when>
		<jstl:when test="${command=='create'}">
			<acme:submit code="inventor.toolkit.form.button.create" action="/inventor/toolkit/create"/>
		</jstl:when>
	</jstl:choose>	
</acme:form>