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
	<jstl:choose>
		<jstl:when test="${type==1}">
			<acme:list-column code="inventor.item.amount" path="amount"/>
		</jstl:when>
	</jstl:choose>
</acme:list>
	<jstl:choose>
	<jstl:when test="${type == 0}">
		<acme:button code="inventor.item.tool.toolkit.create" action="/inventor/quantity/create?masterId=${masterId}&type=${type}"/>
 		<acme:button code="inventor.item.tool.toolkit.delete" action="/inventor/quantity/delete?masterId=${masterId}&type=${type}"/>
	</jstl:when>
	<jstl:when test="${type == 1}">
		<acme:button code="inventor.item.comp.toolkit.create" action="/inventor/quantity/create?masterId=${masterId}&type=${type}"/>
 		<acme:button code="inventor.item.comp.toolkit.delete" action="/inventor/quantity/delete?masterId=${masterId}&type=${type}"/>
	</jstl:when>
	</jstl:choose>