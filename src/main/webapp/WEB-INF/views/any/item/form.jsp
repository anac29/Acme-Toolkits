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

<acme:form readonly="${readonly}">
	<acme:input-textbox code="any.item.form.label.name" path="name"/>
	<acme:input-textbox code="any.item.form.label.code" path="code"/>
	<acme:input-textbox code="any.item.form.label.technology" path="technology"/>
		<acme:input-textarea code="any.item.form.label.description" path="description"/>
	<acme:input-money code="any.item.form.label.retail-price" path="retailPrice"/>
	<acme:input-select code="inventor.item.form.label.itemType" path="itemType">
		
			<acme:input-option code="inventor.item.form.label.tool" value="TOOL" selected="${ itemType == 'TOOL' }"/>
			<acme:input-option code="inventor.item.form.label.component" value="COMPONENT" selected="${ itemType == 'COMPONENT' }"/>
		
		</acme:input-select>
	<acme:input-url code="any.item.form.label.link" path="link"/>
	<hr>
	<h3><acme:message code="any.item.form.label.title"/></h3>
	<acme:input-textbox code="any.item.form.label.name" path="inventorName"/>
	<acme:input-textbox code="any.item.form.label.surname" path="inventorSurname"/>
	<acme:input-email code="any.item.form.label.email" path="inventorEmail"/>	
	<acme:button code="any.item.form.button.toolkit" action="/any/toolkit/list-toolkit?id=${itemId}"/>	




</acme:form>



