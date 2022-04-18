<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="authenticated.announcement.list.label.creation_moment" path="creationMoment" width="30%"/>
	<acme:list-column code="authenticated.announcement.list.label.title" path="title" width="70%"/>
</acme:list>	
