<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="patron.patronage.list.label.status" path="status"/>
	<acme:list-column code="patron.patronage.list.label.code" path="code"/>
	<acme:list-column code="patron.patronage.list.label.budget" path="budget"/>
	<acme:list-column code="patron.patronage.list.label.creationMomentDate" path="creationMomentDate"/>
	<acme:list-column code="patron.patronage.list.label.startMomentDate" path="startMomentDate"/>
	<acme:list-column code="patron.patronage.list.label.finalMomentDate" path="finalMomentDate"/>
</acme:list>

<!-- acme:button code="patron.patronage.list.button.create" action="/patron/patronage/create"/>  -->