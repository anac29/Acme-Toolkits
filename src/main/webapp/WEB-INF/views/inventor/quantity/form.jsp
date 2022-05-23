<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-select code="inventor.toolkit.tool" path="item">
	   	<jstl:forEach items="${items}" var="tool">
			<acme:input-option code="${tool.getCode()}: ${tool.getName()}" value="${tool.getId()}"/>
		</jstl:forEach>
	</acme:input-select>
	
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<jstl:if test="${type == 1}">
				<acme:input-integer code="inventor.item.amount" path="number"/>
			</jstl:if>
			<acme:submit code="inventor.item.toolkit.create" action="/inventor/quantity/create?masterId=${masterId}&type=${type}"/>
		</jstl:when>
		<jstl:when test="${command == 'delete'}">
			<acme:submit code="inventor.item.toolkit.delete" action="/inventor/quantity/delete?masterId=${masterId}&type=${type}"/>
		</jstl:when>
	</jstl:choose>
</acme:form>