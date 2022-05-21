<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="authenticated.announcement.form.label.title" path="title"/>
	<acme:input-textarea code="authenticated.announcement.form.label.body" path="body"/>
	<acme:input-checkbox code="authenticated.announcement.form.label.flag" path="flag"/>
	<acme:input-textbox code="authenticated.announcement.form.label.link" path="link"/>
	<acme:input-checkbox code="authenticated.announcement.form.label.confirm" path="confirmation"/>
	<acme:submit code="admin.announcement.form.button.create" action="/administrator/announcement/create"/>
</acme:form>