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

		<sec:ifAnyGranted roles="ROLE_NCS_IT,ROLE_NCS_DLR_MANAGE">

			<!-- NAVIGATION -->
			<div class="breadcrumbs">
				<a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				&gt;
				<g:link controller="applicationManagement" action="list">Application Management</g:link>
				<!-- <g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link> -->
			</div>

			<div id="list-period" class="content scaffold-list" role="main">

				<!-- TITLE -->
				<h1><g:message code="default.list.label" args="[entityName]" /></h1>

				<!-- ERROR MESSAGE -->	
				<g:if test="${flash.message}"><div class="message" role="status">${flash.message}</div></g:if>
				
				<!-- LIST  -->
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

				<!-- PAGINATION  -->
				<div class="pagination"><g:paginate total="${periodInstanceTotal}" /></div>

			</div>

		</sec:ifAnyGranted>

	</body>

<
