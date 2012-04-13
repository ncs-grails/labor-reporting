<%@ page import="edu.umn.ncs.labor.Task" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'task.label', default: 'Task')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-task" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-task" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'task.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="active" title="${message(code: 'task.active.label', default: 'Active')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'task.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="userCreated" title="${message(code: 'task.userCreated.label', default: 'User Created')}" />
					
						<g:sortableColumn property="appCreated" title="${message(code: 'task.appCreated.label', default: 'App Created')}" />
					
						<th><g:message code="task.type.label" default="Type" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${taskInstanceList}" status="i" var="taskInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${taskInstance.id}">${fieldValue(bean: taskInstance, field: "name")}</g:link></td>
					
						<td><g:formatBoolean boolean="${taskInstance.active}" /></td>
					
						<td><g:formatDate date="${taskInstance.dateCreated}" /></td>
					
						<td>${fieldValue(bean: taskInstance, field: "userCreated")}</td>
					
						<td>${fieldValue(bean: taskInstance, field: "appCreated")}</td>
					
						<td>${fieldValue(bean: taskInstance, field: "type")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${taskInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
