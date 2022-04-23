<%@ page language="java" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${ readonly }">
	<acme:input-select code="patron.patronage.form.label.status" path="status">
		<acme:input-option code="patron.patronage.form.label.proposed" value="PROPOSED" selected="${ status == 'PROPOSED' }"/>
		<acme:input-option code="patron.patronage.form.label.accepted" value="ACCEPTED" selected="${ status == 'ACCEPTED' }"/>
		<acme:input-option code="patron.patronage.form.label.denied" value="DENIED" selected="${ status == 'DENIED' }"/>
	</acme:input-select>
	<acme:input-textbox code="patron.patronage.form.label.code" path="code"/>
	<acme:input-textbox code="patron.patronage.form.label.legalStuff" path="legalStuff"/>
	<acme:input-money code="patron.patronage.form.label.budget" path="budget"/>
	<acme:input-moment code="patron.patronage.form.label.creationMomentDate" path="creationMomentDate"/>
	<acme:input-moment code="patron.patronage.form.label.startMomentDate" path="startMomentDate"/>
	<acme:input-moment code="patron.patronage.form.label.finalMomentDate" path="finalMomentDate"/>
	<acme:input-url code="patron.patronage.form.label.link" path="link"/>
	
	<hr>
    <h3><acme:message code="patron.patronage.form.label.title"/></h3>
    <acme:input-textbox code="patron.patronage.form.label.name" path="inventorName"/>
    <acme:input-textbox code="patron.patronage.form.label.surname" path="inventorSurname"/>
    <acme:input-email code="patron.patronage.form.label.email" path="inventorEmail"/>	
</acme:form>