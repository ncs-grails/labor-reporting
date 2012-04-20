<%@ page import="edu.umn.ncs.labor.Period" %>
<!doctype html>
<html>

	<head>
		<meta name="layout" content="ncs">
		<link rel="stylesheet" type="text/css" href="${resource(dir:'css',file:'labor-reporting.css')}" />
		<g:set var="entityName" value="${message(code: 'period.label', default: 'Period')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>

	<body>

		<div class="breadcrumbs">
			<g:link controller="main" action="show">DLR Home</g:link>
			&gt;
			<g:link controller="applicationManagement" action="list">Application Management</g:link>
		</div>

		<div id="list-period" class="content scaffold-list" role="main">

			<h1><g:message code="default.list.label" args="[entityName]" /></h1>

			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			
			<table>
				<thead>
					<tr>
						<th>Id</th>	
						<th>Name</th>	
						<th>Type</th>	
					</tr>
				</thead>
				<tbody>
				<g:each in="${periodInstanceList}" status="i" var="periodInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td>${fieldValue(bean: periodInstance, field: "id")}</td>
						<td> 
							<!-- <g:link action="edit" id="${periodInstance.id}"> -->
								<g:formatDate format="yyyy-MM-dd" date="${periodInstance.startDate}"/>
								- 
								<g:formatDate format="yyyy-MM-dd" date="${periodInstance.endDate}"/>
								<!-- </g:link> -->
							</td>
						<td>${fieldValue(bean: periodInstance, field: "type.name")}</td>
					</tr>
				</g:each>
				</tbody>
			</table>

			<div class="pagination">
				<g:paginate total="${periodInstanceTotal}" />
			</div>

		</div>

	</body>

<
