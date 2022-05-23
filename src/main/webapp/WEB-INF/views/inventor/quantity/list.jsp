<%@ page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="inventor.item.list.label.name" path="item.name"/>
	<acme:list-column code="inventor.item.list.label.code" path="item.code"/>
	<acme:list-column code="inventor.item.list.label.technology" path="item.technology"/>
	<acme:list-column code="inventor.item.list.label.description" path="item.description"/>
	<acme:list-column code="inventor.item.list.label.retailPrice" path="item.retailPrice"/>
	<acme:list-column code="inventor.item.list.label.link" path="item.link"/>
	<jstl:choose>
		<jstl:when test="${type==1}">
			<acme:list-column code="inventor.item.amount" path="number"/>
		</jstl:when>
	</jstl:choose>
</acme:list>

<jstl:if test="${ published == false }">
	<jstl:if test="${ itemsLeft > 0 }">
		<acme:button code="inventor.item.quantity.add.${ type }" action="/inventor/quantity/create?masterId=${masterId}&type=${type}"/>
	</jstl:if>
	<jstl:if test="${ itemsIn > 0 }">
		<acme:button code="inventor.item.quantity.delete.${ type }" action="/inventor/quantity/delete?masterId=${masterId}&type=${type}"/>
	</jstl:if>
</jstl:if>
