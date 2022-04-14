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
	
	<acme:button code="patron.patronage.form.button.inventor" action="/any/user-account/show?id=${ inventorId }"/>
	
</acme:form>