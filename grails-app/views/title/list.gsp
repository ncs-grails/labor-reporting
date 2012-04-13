<%@ page import="edu.umn.ncs.labor.Title" %>
<!doctype html>
<html>

	<head>
		<meta name="layout" content="ncs">
		<g:set var="entityName" value="${message(code: 'title.label', default: 'Title')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	
	<body>

		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>

		<div id="list-title" class="content scaffold-list" role="main">

			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
				</g:if>

			<table>
				<thead>
					<tr>
						<g:sortableColumn property="id" title="${message(code: 'title.id.label', default: 'Id')}" />
						<g:sortableColumn property="name" title="${message(code: 'title.name.label', default: 'Name')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${titleInstanceList}" status="i" var="titleInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td>${fieldValue(bean: titleInstance, field: "id")}</td>
						<td><g:link action="show" id="${titleInstance.id}">${fieldValue(bean: titleInstance, field: "name")}</g:link></td>
					</tr>
				</g:each>
				</tbody>
			</table>

			<div class="pagination">
				<g:paginate total="${titleInstanceTotal}" />
			</div>

		</div>

	</body>

</html>
