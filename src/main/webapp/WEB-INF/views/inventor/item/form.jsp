<%@ page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<acme:input-textbox code="inventor.item.form.label.name" path="name" />
	<acme:input-textbox code="inventor.item.form.label.code" path="code" />
	<acme:input-textbox code="inventor.item.form.label.technology"
		path="technology" />
	<acme:input-textbox code="inventor.item.form.label.description"
		path="description" />
	<acme:input-money code="inventor.item.form.label.retailPrice"
		path="retailPrice" />
	<jstl:choose>
		<jstl:when
			test="${acme:anyOf(command,'show, update, delete, publish') && acceptedCurrency && retailPriceDefault.getCurrency()!=retailPrice.getCurrency()}">
			<acme:input-money code="patron.patronage.form.label.budget.default"
				path="retailPriceDefault" readonly="true" />
		</jstl:when>
	</jstl:choose>

	<jstl:choose>
		<jstl:when test="${acme:anyOf(command,'create') || published}">

			<acme:input-select code="inventor.item.form.label.itemType"
				path="itemType">

				<acme:input-option code="inventor.item.form.label.tool" value="TOOL"
					selected="${ itemType == 'TOOL' }" />
				<acme:input-option code="inventor.item.form.label.component"
					value="COMPONENT" selected="${ itemType == 'COMPONENT' }" />

			</acme:input-select>

		</jstl:when>

	</jstl:choose>

	<acme:input-url code="inventor.item.form.label.link" path="link" />


	<jstl:choose>
		<jstl:when test="${acme:anyOf(command,'create')}">
			<acme:submit code="inventor.item.form.button.create"
				action="/inventor/item/create" />
		</jstl:when>

		<jstl:when
			test="${acme:anyOf(command,'show, update, delete,publish') && published==false}">
			<acme:submit code="inventor.item.form.button.update"
				action="/inventor/item/update" />
			<acme:submit code="inventor.item.form.button.delete"
				action="/inventor/item/delete" />
			<acme:submit code="inventor.item.form.button.publish"
				action="/inventor/item/publish" />

		</jstl:when>

	</jstl:choose>

</acme:form>