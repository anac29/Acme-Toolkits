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
	<acme:input-textbox code="any.toolkit.form.label.code" path="code"/>
	<acme:input-textarea code="any.toolkit.form.label.title" path="title"/>
	<acme:input-textbox code="any.toolkit.form.label.description" path="description"/>
	<acme:input-textarea code="any.toolkit.form.label.assembly-notes" path="assemblyNotes"/>
	<acme:input-url code="any.toolkit.form.label.link" path="link"/>
	<acme:input-money code="any.toolkit.form.label.total-price" path="totalPrice"/> 
	<acme:button code="any.toolkit.form.button.tool" action="/any/item/list-tool-toolkit?id=${ toolkitId }"/>		
	<acme:button code="any.toolkit.form.button.component" action="/any/item/list-component-toolkit?id=${ toolkitId }"/>		
	

	<hr>
	<h3><acme:message code="any.toolkit.form.label.title"/></h3>
	<acme:input-textbox code="any.toolkit.form.label.name" path="inventorName"/>
	<acme:input-textbox code="any.toolkit.form.label.surname" path="inventorSurname"/>
	<acme:input-email code="any.toolkit.form.label.email" path="inventorEmail"/>
</acme:form>
