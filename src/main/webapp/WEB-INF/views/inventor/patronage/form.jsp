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

<acme:form readonly="true">
	<jstl:when test="${command=='create'}">
		<acme:input-select code="inventor.patronage.form.label.status"
			path="status">
			<acme:input-option code="inventor.patronage.form.label.proposed"
				value="PROPOSED" selected="${ status == 'PROPOSED' }" />
			<acme:input-option code="inventor.patronage.form.label.accepted"
				value="ACCEPTED" selected="${ status == 'ACCEPTED' }" />
			<acme:input-option code="inventor.patronage.form.label.denied"
				value="DENIED" selected="${ status == 'DENIED' }" />
		</acme:input-select>
	</jstl:when>
	<acme:input-textbox code="inventor.patronage.form.label.code"
		path="code" />
	<acme:input-textbox code="inventor.patronage.form.label.legalStuff"
		path="legalStuff" />
	<acme:input-money code="inventor.patronage.form.label.budget"
		path="budget" />
	<acme:input-moment
		code="inventor.patronage.form.label.creationMomentDate"
		path="creationMomentDate" />
	<acme:input-moment code="inventor.patronage.form.label.startMomentDate"
		path="startMomentDate" />
	<acme:input-moment code="inventor.patronage.form.label.finalMomentDate"
		path="finalMomentDate" />
	<acme:input-url code="inventor.patronage.form.label.link" path="link" />

	<hr>
	<h3>
		<acme:message code="inventor.patronage.form.label.title" />
	</h3>
	<acme:input-textbox code="inventor.patronage.form.label.name"
		path="patronName" />
	<acme:input-textbox code="inventor.patronage.form.label.surname"
		path="patronSurname" />
	<acme:input-email code="inventor.patronage.form.label.email"
		path="patronEmail" />


	<jstl:choose>
		<jstl:when test="${ status=='PROPOSED' }">
			<acme:button code="inventor.patronage.list.button.update.accept"
				action="/inventor/patronage/update?masterId=${ patronageId }" />
		</jstl:when>

	</jstl:choose>

	<jstl:choose>
		<jstl:when test="${ status=='ACCEPTED' }">
			<acme:button code="inventor.patronage.list.button.create"
				action="/inventor/patronage-report/create?masterId=${ patronageId }" />
		</jstl:when>

	</jstl:choose>
</acme:form>


