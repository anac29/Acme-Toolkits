<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="anonymous.user-account.form.label.name" path="identity.name"/>
	<acme:input-textbox code="anonymous.user-account.form.label.surname" path="identity.surname"/>
	<acme:input-email code="anonymous.user-account.form.label.email" path="identity.email"/>
	<acme:input-textbox code="anonymous.user-account.form.label.roles" path="roles"/>
</acme:form>