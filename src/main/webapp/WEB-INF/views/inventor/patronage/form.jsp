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
	<acme:input-textbox code="inventor.patronage.form.label.status" path="status"/>
	<acme:input-textbox code="inventor.patronage.form.label.legalStuff" path="legalStuff"/>
	<acme:input-money code="inventor.patronage.form.label.budget" path="budget"/>
	<acme:input-moment code="inventor.patronage.form.label.creationMomentDate" path="creationMomentDate"/>
	<acme:input-moment code="inventor.patronage.form.label.finalMomentDate" path="finalMomentDate"/>
	<acme:input-url code="inventor.patronage.form.label.link" path="link"/>
	<acme:input-textbox code="inventor.patronage.form.label.inventor" path="inventor"/>
	<acme:input-textbox code="inventor.patronage.form.label.patron" path="patron"/>
</acme:form>
