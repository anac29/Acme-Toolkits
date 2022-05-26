<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>


<acme:form readonly="true">

<h1>
	<acme:message code="administrator.dashboard.form.title" />
</h1>

	<div style="display: flex; flex-direction: row;">
	<h2 style="margin-top:5px;">
	<acme:message
		code="administrator.dashboard.form.label.totalNumberOfComponents" />
	</h2>

	<div style="background:#f2f2f7; border-radius:5px; padding:5px 10px; width:fit-content; display: flex; margin-bottom:5px; margin-left:10px;">
	<h2 style="margin-bottom:0;"><acme:print value="${totalNumberOfComponents}" /></h2></div>

	</div>

<h3>
	<acme:message
		code="administrator.dashboard.form.label.averageRetailPriceOfComponentsByTechnologyCurrency" />
</h3>

<table class="table table-sm"
	id="id-averageRetailPriceOfComponentsByTechnologyCurrency">
	<caption>
		<acme:message
			code="administrator.dashboard.form.label.averageRetailPriceOfComponentsByTechnologyCurrency" />
	</caption>

	<jstl:forEach items="${ technology }" var="technology">

		<tr>
			<th scope="row"><acme:print value="${ technology }" /></th>
			<jstl:set
				value="${ averageRetailPriceOfComponentsByTechnologyCurrency.entrySet().stream().filter(e -> e.getKey().getFirst().equals(technology)).iterator() }"
				var="entrySet" />
			<jstl:if test="${ entrySet.hasNext() }">
				<jstl:forEach items="${ entrySet }" var="entry">
					<tr>
						<td style="width: 10%"><acme:message
								code="administrator.dashboard.form.technology.${ entry.getKey().getSecond() }" />
						</td>
						<td><acme:print value="${ entry.getValue() }" /></td>
					</tr>
				</jstl:forEach>
			</jstl:if>
		</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message
		code="administrator.dashboard.form.label.deviationRetailPriceOfComponentsByTechnologyCurrency" />
</h3>

<table class="table table-sm"
	id="id-deviationRetailPriceOfComponentsByTechnologyCurrency">
	<caption>
		<acme:message
			code="administrator.dashboard.form.label.deviationRetailPriceOfComponentsByTechnologyCurrency" />

	</caption>

	<jstl:forEach items="${ technology }" var="technology">

		<tr>
			<th scope="row"><acme:print value="${ technology }" /></th>
			<jstl:set
				value="${ deviationRetailPriceOfComponentsByTechnologyCurrency.entrySet().stream().filter(e -> e.getKey().getFirst().equals(technology)).iterator() }"
				var="entrySet" />
			<jstl:if test="${ entrySet.hasNext() }">
				<jstl:forEach items="${ entrySet }" var="entry">
					<tr>
						<td style="width: 10%"><acme:message
								code="administrator.dashboard.form.technology.${ entry.getKey().getSecond() }" />
						</td>
						<td><acme:print value="${ entry.getValue() }" /></td>
					</tr>
				</jstl:forEach>
			</jstl:if>
		</tr>
	</jstl:forEach>
</table>


<h3>
	<acme:message
		code="administrator.dashboard.form.label.minimumRetailPriceOfComponentsByTechnologyCurrency" />
</h3>

<table class="table table-sm"
	id="id-minimumRetailPriceOfComponentsByTechnologyCurrency">
	<caption>
		<acme:message
			code="administrator.dashboard.form.label.minimumRetailPriceOfComponentsByTechnologyCurrency" />
	</caption>

	<jstl:forEach items="${ technology }" var="technology">

		<tr>
			<th scope="row"><acme:print value="${ technology }" /></th>
			<jstl:set
				value="${ minimumRetailPriceOfComponentsByTechnologyCurrency.entrySet().stream().filter(e -> e.getKey().getFirst().equals(technology)).iterator() }"
				var="entrySet" />
			<jstl:if test="${ entrySet.hasNext() }">
				<jstl:forEach items="${ entrySet }" var="entry">
					<tr>
						<td style="width: 10%"><acme:message
								code="administrator.dashboard.form.technology.${ entry.getKey().getSecond() }" />
						</td>
						<td><acme:print value="${ entry.getValue() }" /></td>
					</tr>
				</jstl:forEach>
			</jstl:if>
		</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message
		code="administrator.dashboard.form.label.maximumRetailPriceOfComponentsByTechnologyCurrency" />
</h3>

<table class="table table-sm"
	id="id-maximumRetailPriceOfComponentsByTechnologyCurrency">
	<caption>
		<acme:message
			code="administrator.dashboard.form.label.maximumRetailPriceOfComponentsByTechnologyCurrency" />
	</caption>

	<jstl:forEach items="${ technology }" var="technology">

		<tr>
			<th scope="row"><acme:print value="${ technology }" /></th>
			<jstl:set
				value="${ maximumRetailPriceOfComponentsByTechnologyCurrency.entrySet().stream().filter(e -> e.getKey().getFirst().equals(technology)).iterator() }"
				var="entrySet" />
			<jstl:if test="${ entrySet.hasNext() }">
				<jstl:forEach items="${ entrySet }" var="entry">
					<tr>
						<td style="width: 10%"><acme:message
								code="administrator.dashboard.form.technology.${ entry.getKey().getSecond() }" />
						</td>
						<td><acme:print value="${ entry.getValue() }" /></td>
					</tr>
				</jstl:forEach>
			</jstl:if>
		</tr>
	</jstl:forEach>
</table>


<div style="display: flex; flex-direction: row;">
<h2 style="margin-top:5px;">
	<acme:message
		code="administrator.dashboard.form.label.totalNumberOfTools" /></h2>
	<div style="background:#f2f2f7; border-radius:5px; padding:5px 10px; width:fit-content; display: flex; margin-bottom:5px; margin-left:10px;">
	<h2 style="margin-bottom:0;">
	<acme:print value="${totalNumberOfTools}" />
	</h2>
	</div>
</div>
<h3>
	<acme:message
		code="administrator.dashboard.form.label.averageRetailPriceOfToolsByCurrency" />
</h3>

<table class="table table-sm"
	id="id-averageRetailPriceOfToolsByCurrency">
	<caption>
		<acme:message
			code="administrator.dashboard.form.label.averageRetailPriceOfToolsByCurrency" />
	</caption>

	<jstl:forEach items="${ currency }" var="currency">

		<tr>
			<th scope="row"><acme:print value="${ currency }" /></th>
			<jstl:set
				value="${ averageRetailPriceOfToolsByCurrency.entrySet().stream().filter(e -> e.getKey().equals(currency)).iterator() }"
				var="entrySet" />
			<jstl:if test="${ entrySet.hasNext() }">
				<jstl:forEach items="${ entrySet }" var="entry">

					<th scope="row"><acme:print value="${ entry.getValue() }" /></th>

				</jstl:forEach>
			</jstl:if>
		</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message
		code="administrator.dashboard.form.label.deviationRetailPriceOfToolsByCurrency" />
</h3>

<table class="table table-sm"
	id="id-deviationRetailPriceOfToolsByCurrency">
	<caption>
		<acme:message
			code="administrator.dashboard.form.label.deviationRetailPriceOfToolsByCurrency" />
	</caption>

	<jstl:forEach items="${ currency }" var="currency">

		<tr>
			<th scope="row"><acme:print value="${ currency }" /></th>
			<jstl:set
				value="${ deviationRetailPriceOfToolsByCurrency.entrySet().stream().filter(e -> e.getKey().equals(currency)).iterator() }"
				var="entrySet" />
			<jstl:if test="${ entrySet.hasNext() }">
				<jstl:forEach items="${ entrySet }" var="entry">

					<th scope="row"><acme:print value="${ entry.getValue() }" /></th>

				</jstl:forEach>
			</jstl:if>
		</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message
		code="administrator.dashboard.form.label.maximumRetailPriceOfToolsByCurrency" />
</h3>

<table class="table table-sm"
	id="id-maximumRetailPriceOfToolsByCurrency">
	<caption>
		<acme:message
			code="administrator.dashboard.form.label.maximumRetailPriceOfToolsByCurrency" />
	</caption>

	<jstl:forEach items="${ currency }" var="currency">

		<tr>
			<th scope="row"><acme:print value="${ currency }" /></th>
			<jstl:set
				value="${ maximumRetailPriceOfToolsByCurrency.entrySet().stream().filter(e -> e.getKey().equals(currency)).iterator() }"
				var="entrySet" />
			<jstl:if test="${ entrySet.hasNext() }">
				<jstl:forEach items="${ entrySet }" var="entry">

					<th scope="row"><acme:print value="${ entry.getValue() }" /></th>

				</jstl:forEach>
			</jstl:if>
		</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message
		code="administrator.dashboard.form.label.minimumRetailPriceOfToolsByCurrency" />
</h3>

<table class="table table-sm"
	id="id-minimumRetailPriceOfToolsByCurrency">
	<caption>
		<acme:message
			code="administrator.dashboard.form.label.minimumRetailPriceOfToolsByCurrency" />
	</caption>

	<jstl:forEach items="${ currency }" var="currency">

		<tr>
			<th scope="row"><acme:print value="${ currency }" /></th>
			<jstl:set
				value="${ minimumRetailPriceOfToolsByCurrency.entrySet().stream().filter(e -> e.getKey().equals(currency)).iterator() }"
				var="entrySet" />
			<jstl:if test="${ entrySet.hasNext() }">
				<jstl:forEach items="${ entrySet }" var="entry">

					<th scope="row"><acme:print value="${ entry.getValue() }" /></th>

				</jstl:forEach>
			</jstl:if>
		</tr>
	</jstl:forEach>
</table>
<h2>
	<acme:message
		code="administrator.dashboard.form.label.totalNumberOfPatronagesByStatus" />
</h2>

<table class="table table-sm" id="id-totalNumberOfPatronagesByStatus">
	<caption>
		<acme:message
			code="administrator.dashboard.form.label.totalNumberOfPatronagesByStatus" />
	</caption>
	<jstl:forEach items="${ totalNumberOfPatronagesByStatus.keySet() }"
		var="key">
		<tr>
			<jstl:set value="${ totalNumberOfPatronagesByStatus.get(key) }"
				var="amount" />
			<jstl:if test="${ amount>0 }">
				<th scope="row" style="width: 10%"><acme:message
						code="administrator.dashboard.form.status.${ key }" /></th>
				<td><acme:print value="${ amount }" /></td>
			</jstl:if>

		</tr>
	</jstl:forEach>
</table>


<h3>
	<acme:message
		code="administrator.dashboard.form.label.averagePatronagesBudgetByStatus" />
</h3>
<table class="table table-sm" id="id-averagePatronagesBudgetByStatus">
	<caption>
		<acme:message
			code="administrator.dashboard.form.label.averagePatronagesBudgetByStatus" />
	</caption>
	<jstl:forEach items="${ averagePatronagesBudgetByStatus.keySet() }"
		var="key">
		<tr>
			<jstl:set value="${ averagePatronagesBudgetByStatus.get(key) }"
				var="amount" />
			<jstl:if test="${ amount>0 }">
				<th scope="row" style="width: 10%"><acme:message
						code="administrator.dashboard.form.status.${ key }" /></th>
				<td><acme:print value="${ amount }" /></td>
			</jstl:if>

		</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message
		code="administrator.dashboard.form.label.deviationPatronagesBudgetByStatus" />
</h3>
<table class="table table-sm" id="id-deviationPatronagesBudgetByStatus">
	<caption>
		<acme:message
			code="administrator.dashboard.form.label.deviationPatronagesBudgetByStatus" />
	</caption>
	<jstl:forEach items="${ deviationPatronagesBudgetByStatus.keySet() }"
		var="key">
		<tr>
			<jstl:set value="${ deviationPatronagesBudgetByStatus.get(key) }"
				var="amount" />
			<jstl:if test="${ amount>0 }">
				<th scope="row" style="width: 10%"><acme:message
						code="administrator.dashboard.form.status.${ key }" /></th>
				<td><acme:print value="${ amount }" /></td>
			</jstl:if>

		</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message
		code="administrator.dashboard.form.label.minimumPatronagesBudgetByStatus" />
</h3>
<table class="table table-sm" id="id-minimumPatronagesBudgetByStatus">
	<caption>
		<acme:message
			code="administrator.dashboard.form.label.minimumPatronagesBudgetByStatus" />
	</caption>
	<jstl:forEach items="${ minimumPatronagesBudgetByStatus.keySet() }"
		var="key">
		<tr>
			<jstl:set value="${ minimumPatronagesBudgetByStatus.get(key) }"
				var="amount" />
			<jstl:if test="${ amount>0 }">
				<th scope="row" style="width: 10%"><acme:message
						code="administrator.dashboard.form.status.${ key }" /></th>
				<td><acme:print value="${ amount }" /></td>
			</jstl:if>

		</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message
		code="administrator.dashboard.form.label.maximumPatronagesBudgetByStatus" />
</h3>
<table class="table table-sm" id="id-maximumPatronagesBudgetByStatus">
	<caption>
		<acme:message
			code="administrator.dashboard.form.label.maximumPatronagesBudgetByStatus" />
	</caption>
	<jstl:forEach items="${ maximumPatronagesBudgetByStatus.keySet() }"
		var="key">
		<tr>
			<jstl:set value="${ maximumPatronagesBudgetByStatus.get(key) }"
				var="amount" />
			<jstl:if test="${ amount>0 }">
				<th scope="row" style="width: 10%"><acme:message
						code="administrator.dashboard.form.status.${ key }" /></th>
				<td><acme:print value="${ amount }" /></td>
			</jstl:if>

		</tr>
	</jstl:forEach>
</table>
</acme:form>