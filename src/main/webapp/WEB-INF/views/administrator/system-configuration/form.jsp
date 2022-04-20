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
	<acme:input-textarea code="administrator.configuration.form.label.strong-spam-terms" path="strongSpamTerms"/>
	<acme:input-textarea code="administrator.configuration.form.label.weak-spam-terms" path="weakSpamTerms"/>
	<acme:input-textarea code="administrator.configuration.form.label.accepted-currencies" path="acceptedCurrencies"/>
	<acme:input-textbox code="administrator.configuration.form.label.default-currency" path="defaultCurrency"/>
	<acme:input-double code="administrator.configuration.form.label.weak-threshold" path="weakThreshold"/>
	<acme:input-double code="administrator.configuration.form.label.strong-threshold" path="strongThreshold"/>

</acme:form>
