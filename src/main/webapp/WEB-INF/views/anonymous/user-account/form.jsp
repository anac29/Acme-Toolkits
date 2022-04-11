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
	<acme:input-textbox code="anonymous.user-account.form.label.username" path="username"/>
	<acme:input-password code="anonymous.user-account.form.label.password" path="password"/>
	<acme:input-password code="anonymous.user-account.form.label.confirmation" path="confirmation"/>
	
	<acme:input-textbox code="anonymous.user-account.form.label.name" path="identity.name"/>
	<acme:input-textbox code="anonymous.user-account.form.label.surname" path="identity.surname"/>
	<acme:input-email code="anonymous.user-account.form.label.email" path="identity.email"/>
	 
	<acme:input-checkbox code="anonymous.user-account.form.label.accept" path="accept"/>
	
	<acme:submit code="anonymous.user-account.form.button.create" action="/anonymous/user-account/create"/>  	
</acme:form>
