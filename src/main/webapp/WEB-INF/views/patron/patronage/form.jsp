<%@ page language="java" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<jstl:choose>
		<jstl:when test="${published==true}">
			<acme:input-select code="patron.patronage.form.label.status" path="status">
				<acme:input-option code="patron.patronage.form.label.proposed" value="PROPOSED" selected="${ status == 'PROPOSED' }"/>
				<acme:input-option code="patron.patronage.form.label.accepted" value="ACCEPTED" selected="${ status == 'ACCEPTED' }"/>
				<acme:input-option code="patron.patronage.form.label.denied" value="DENIED" selected="${ status == 'DENIED' }"/>
			</acme:input-select>
		</jstl:when>
	</jstl:choose>
	<acme:input-textbox code="patron.patronage.form.label.code" path="code"/>
	<acme:input-textbox code="patron.patronage.form.label.legalStuff" path="legalStuff"/>
	<acme:input-money code="patron.patronage.form.label.budget" path="budget"/>
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command,'show, update, delete, publish') && budgetDefault.getCurrency()!=budget.getCurrency()}">
			<acme:input-money code="patron.patronage.form.label.budget.default" path="budgetDefault" readonly="true"/>
		</jstl:when>
	</jstl:choose>
	
	<jstl:choose>
		<jstl:when test="${published==true}">
	<acme:input-moment code="patron.patronage.form.label.creationMomentDate" path="creationMomentDate"/>
		</jstl:when>
	</jstl:choose>
	<acme:input-moment code="patron.patronage.form.label.startMomentDate" path="startMomentDate"/>
	<acme:input-moment code="patron.patronage.form.label.finalMomentDate" path="finalMomentDate"/>
	<acme:input-url code="patron.patronage.form.label.link" path="link"/>
	
	<hr>
    <h3><acme:message code="patron.patronage.form.label.title"/></h3>
    <jstl:choose>
    
    	<jstl:when test="${acme:anyOf(command,'show, update, delete, create, publish') && published==false}">
	   		<acme:input-select code="patron.patronage.form.label.inventor" path="inventorId">
	   			<jstl:forEach items="${inventors}" var="inventor">
					<acme:input-option code="${inventor.getUserAccount().getUsername()}" value="${inventor.getId()}" selected="${ inventor.getId() == inventId }"/>
				</jstl:forEach>
			</acme:input-select>
   		
   		</jstl:when>
		<jstl:when test="${command=='show'}">
    
		    <acme:input-textbox code="patron.patronage.form.label.name" path="inventorName"/>
		    <acme:input-textbox code="patron.patronage.form.label.surname" path="inventorSurname"/>
		    <acme:input-email code="patron.patronage.form.label.email" path="inventorEmail"/>
    
   		</jstl:when>
   		
	</jstl:choose>
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command,'show, update, delete, publish') && published == false}"> 
			<acme:submit code="patron.patronage.form.button.update" action="/patron/patronage/update"/>
			<acme:submit code="patron.patronage.form.button.delete" action="/patron/patronage/delete"/>
			<acme:submit code="patron.patronage.form.button.publish" action="/patron/patronage/publish"/>
		</jstl:when>
		<jstl:when test="${command=='create'}">
			<acme:submit code="patron.patronage.form.button.create" action="/patron/patronage/create"/>
		</jstl:when>
	</jstl:choose>	
</acme:form>