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
	<acme:input-money code="inventor.toolkit.form.label.total-price" path="totalPrice"/> 
	<acme:input-checkbox code="inventor.toolkit.form.label.published" path="published"/> 
	<acme:button code="inventor.toolkit.form.button.tool" action="/inventor/item/list-tool-toolkit?id=${ toolkitId }"/>
	<acme:button code="inventor.toolkit.form.button.component" action="/inventor/item/list-component-toolkit?id=${ toolkitId }"/>	
	
</acme:form>