<%--
- form.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this artefact. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

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
			<jstl:when test="${command == 'create' && type == 1}">
				<acme:input-textbox code="inventor.item.amount" path="number"/>
				<acme:submit code="inventor.item.toolkit.create" action="/inventor/quantity/create?masterId=${masterId}&type=${type}"/>
			</jstl:when>
			<jstl:when test="${command == 'create' && type == 0}">
				<acme:submit code="inventor.item.toolkit.create" action="/inventor/quantity/create?masterId=${masterId}&type=${type}"/>
			</jstl:when>
			<jstl:when test="${command == 'delete'}">
				<acme:submit code="inventor.item.toolkit.delete" action="/inventor/quantity/delete?masterId=${masterId}&type=${type}"/>
			</jstl:when>
	</jstl:choose>
</acme:form>