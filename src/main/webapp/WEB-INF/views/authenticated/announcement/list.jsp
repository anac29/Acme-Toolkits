<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<acme:list>
	<acme:list-column code="authenticated.announcement.list.label.creationMoment" path="creationMoment" width="30%"/>
	<acme:list-column code="authenticated.announcement.list.label.title" path="title" width="70%"/>
</acme:list>
<security:authorize access="hasRole('Administrator')">
	<acme:button code="master.menu.administrator.announcement" action="/administrator/announcement/create"/>
</security:authorize>