<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<h2>
	<acme:message code="patron.dashboard.form.title"/>
</h2>

<h3><acme:message code="patron.dashboard.form.label.totalNumberOfPatronagesByStatus"/></h3>
<table class="table table-sm" id="id-totalNumberOfPatronagesByStatus">
	<caption><acme:message code="patron.dashboard.form.label.totalNumberOfPatronagesByStatus"/></caption>
	<jstl:forEach items="${ totalNumberOfPatronagesByStatus.keySet() }" var="key">
		<tr>
			<jstl:set value="${ totalNumberOfPatronagesByStatus.get(key) }" var="amount"/>
			<jstl:if test="${ amount>0 }">
				<th scope="row" style="width:10%">
					<acme:message code="patron.dashboard.form.status.${ key }" />
				</th>
				<td>
					<acme:print value="${ amount }" />
				</td>
			</jstl:if>
				
		</tr>
	</jstl:forEach>
</table>

<h3><acme:message code="patron.dashboard.form.label.averageBudgetOfPatronagesStatusByCurrency"/></h3>
<table class="table table-sm" id="id-averageBudgetOfPatronagesStatusByCurrency">
	<caption><acme:message code="patron.dashboard.form.label.averageBudgetOfPatronagesStatusByCurrency"/></caption>
	<jstl:forEach items="${ currencies }" var="currency">
		<tr>
			<th scope="row">
				<acme:print value="${ currency }"/>
			</th>
				<jstl:set value="${ averageBudgetOfPatronagesStatusByCurrency.entrySet().stream().filter(e -> e.getKey().getFirst().equals(currency)).iterator() }" var="entrySet" />
				<jstl:if test="${ entrySet.hasNext() }">
				<jstl:forEach items="${ entrySet }" var="entry">
					<tr>
					<td style="width:10%">
						<acme:message code="patron.dashboard.form.status.${ entry.getKey().getSecond() }"/>
					</td>
					<td>
						<acme:print value="${ entry.getValue() }"/>
					</td>
					</tr>
				</jstl:forEach>
				</jstl:if>
		</tr>
	</jstl:forEach>
</table>

<h3><acme:message code="patron.dashboard.form.label.deviationBudgetOfPatronagesStatusByCurrency"/></h3>
<table class="table table-sm" id="id-deviationBudgetOfPatronagesStatusByCurrency">
	<caption><acme:message code="patron.dashboard.form.label.deviationBudgetOfPatronagesStatusByCurrency"/></caption>
	<jstl:forEach items="${ currencies }" var="currency">
		<tr>
			<th scope="row">
				<acme:print value="${ currency }"/>
			</th>
				<jstl:set value="${ deviationBudgetOfPatronagesStatusByCurrency.entrySet().stream().filter(e -> e.getKey().getFirst().equals(currency)).iterator() }" var="entrySet" />
				<jstl:if test="${ entrySet.hasNext() }">
				<jstl:forEach items="${ entrySet }" var="entry">
					<tr>
					<td style="width:10%">
						<acme:message code="patron.dashboard.form.status.${ entry.getKey().getSecond() }"/>
					</td>
					<td>
						<acme:print value="${ entry.getValue() }"/>
					</td>
					</tr>
				</jstl:forEach>
				</jstl:if>
		</tr>
	</jstl:forEach>
</table>

<h3><acme:message code="patron.dashboard.form.label.minimumBudgetOfPatronagesStatusByCurrency"/></h3>
<table class="table table-sm" id="id-averageBudgetOfPatronagesStatusByCurrency" >
	<caption><acme:message code="patron.dashboard.form.label.minimumBudgetOfPatronagesStatusByCurrency"/></caption>
	<jstl:forEach items="${ currencies }" var="currency">
		<tr>
			<th scope="row">
				<acme:print value="${ currency }"/>
			</th>
				<jstl:set value="${ minimumBudgetOfPatronagesStatusByCurrency.entrySet().stream().filter(e -> e.getKey().getFirst().equals(currency)).iterator() }" var="entrySet" />
				<jstl:if test="${ entrySet.hasNext() }">
				<jstl:forEach items="${ entrySet }" var="entry">
					<tr>
					<td style="width:10%">
						<acme:message code="patron.dashboard.form.status.${ entry.getKey().getSecond() }"/>
					</td>
					<td>
						<acme:print value="${ entry.getValue() }"/>
					</td>
					</tr>
				</jstl:forEach>
				</jstl:if>
		</tr>
	</jstl:forEach>
</table>

<h3><acme:message code="patron.dashboard.form.label.maximumBudgetOfPatronagesStatusByCurrency"/></h3>
<table class="table table-sm" id="id-maximumBudgetOfPatronagesStatusByCurrency">
	<caption><acme:message code="patron.dashboard.form.label.maximumBudgetOfPatronagesStatusByCurrency"/></caption>
	<jstl:forEach items="${ currencies }" var="currency">
		<tr>
			<th scope="row">
				<acme:print value="${ currency }"/>
			</th>
				<jstl:set value="${ maximumBudgetOfPatronagesStatusByCurrency.entrySet().stream().filter(e -> e.getKey().getFirst().equals(currency)).iterator() }" var="entrySet" />
				<jstl:if test="${ entrySet.hasNext() }">
				<jstl:forEach items="${ entrySet }" var="entry">
					<tr>
					<td style="width:10%">
						<acme:message code="patron.dashboard.form.status.${ entry.getKey().getSecond() }"/>
					</td>
					<td>
						<acme:print value="${ entry.getValue() }"/>
					</td>
					</tr>
				</jstl:forEach>
				</jstl:if>
		</tr>
	</jstl:forEach>
</table>
