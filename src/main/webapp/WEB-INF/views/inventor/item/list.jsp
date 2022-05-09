<%@ page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.item.list.label.name" path="name"/>
	<acme:list-column code="inventor.item.list.label.code" path="code"/>
	<acme:list-column code="inventor.item.list.label.technology" path="technology"/>
	<acme:list-column code="inventor.item.list.label.description" path="description"/>
	<acme:list-column code="inventor.item.list.label.retailPrice" path="retailPrice"/>
	<acme:list-column code="inventor.item.list.label.link" path="link"/>
	<acme:list-column code="inventor.item.list.label.published" path="published"/>

	
</acme:list>

	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command,'list-tool')}">
			<acme:button code="inventor.item.list.button.create.tool" action="/inventor/item/create-tool"/>
		</jstl:when>
		<jstl:when test="${acme:anyOf(command,'list-component')}">
			<acme:button code="inventor.item.list.button.create.component" action="/inventor/item/create-component"/>
		</jstl:when>
	</jstl:choose>