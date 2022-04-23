<%@ page language="java" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${ readonly }">

	<acme:input-textbox code="inventor.item.form.label.name" path="name"/>
	<acme:input-textbox code="inventor.item.form.label.code" path="code"/>
	<acme:input-textbox code="inventor.item.form.label.technology" path="technology"/>
	<acme:input-textbox code="inventor.item.form.label.description" path="description"/>
	<acme:input-money code="inventor.item.form.label.retailPrice" path="retailPrice"/>
	<acme:input-select code="inventor.item.form.label.itemType" path="itemType">
		<acme:input-option code="inventor.item.form.label.tool" value="TOOL" selected="${ itemType == 'TOOL' }"/>
		<acme:input-option code="inventor.item.form.label.component" value="COMPONENT" selected="${ itemType == 'COMPONENT' }"/>
	</acme:input-select>
	<acme:input-url code="inventor.item.form.label.link" path="link"/>
	<acme:input-checkbox code="inventor.item.form.label.published" path="published"/>	
	
</acme:form>