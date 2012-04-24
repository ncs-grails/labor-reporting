<%@ page import="edu.umn.ncs.labor.Task" %>
<!doctype html>
<html>

	<head>
		<meta name="layout" content="ncs">
		<link rel="stylesheet" type="text/css" href="${resource(dir:'css',file:'labor-reporting.css')}" />
		<g:set var="entityName" value="${message(code: 'task.label', default: 'Task')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>

	<body>

		<sec:ifAnyGranted roles="ROLE_NCS_IT,ROLE_NCS_DLR_MANAGE">

			<!-- NAVIGATION -->
			<div class="breadcrumbs">
				<a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
				&gt;
				<g:link controller="applicationManagement" action="list">Application Management</g:link>
				<!-- <g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link> -->
			</div>

			<div id="list-task" class="content scaffold-list" role="main">

				<!-- TITLE -->
				<h1><g:message code="default.list.label" args="[entityName]" /></h1>

				<!-- ERROR MESSAGE -->	
				<g:if test="${flash.message}"><div class="message" role="status">${flash.message}</div></g:if>
				
				<!-- LIST  -->
				<table>
					<thead>
						<tr>
							<g:sortableColumn property="id" title="${message(code: 'task.id.label', default: 'Id')}" />
							<g:sortableColumn property="name" title="${message(code: 'task.name.label', default: 'Name')}" />
							<th><g:message code="task.type.label" default="Type" /></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${taskInstanceList}" status="i" var="taskInstance">
						<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
							<td>${fieldValue(bean: taskInstance, field: "id")}</td>
							<td><g:link action="show" id="${taskInstance.id}">${fieldValue(bean: taskInstance, field: "name")}</g:link></td>
							<td>${fieldValue(bean: taskInstance, field: "type")}</td>
						</tr>
					</g:each>
					</tbody>
				</table>

				<!-- PAGINATION  -->
				<div class="pagination"><g:paginate total="${taskInstanceTotal}" /></div>

			</div>

		</sec:ifAnyGranted>

	</body>

</html>
