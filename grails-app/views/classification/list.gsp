<%@ page import="edu.umn.ncs.labor.Classification" %>
<!doctype html>
<html>i

	<head>
		<meta name="layout" content="ncs">
		<g:set var="entityName" value="${message(code: 'classification.label', default: 'Classification')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>

	<body>

		<a href="#list-classification" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<!-- <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li> -->
			</ul>
		</div>

		<div id="list-classification" class="content scaffold-list" role="main">
		
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
		
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>

			<table>
				<thead>
					<tr>
						<g:sortableColumn property="id" title="${message(code: 'classification.id.label', default: 'Id')}" />
						<g:sortableColumn property="name" title="${message(code: 'classification.name.label', default: 'Name')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${classificationInstanceList}" status="i" var="classificationInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td>${fieldValue(bean: classificationInstance, field: "id")}</td>
						<td><g:link action="show" id="${classificationInstance.id}">${fieldValue(bean: classificationInstance, field: "name")}</g:link></td>
					</tr>
				</g:each>
				</tbody>
			</table>

			<div class="pagination">
				<g:paginate total="${classificationInstanceTotal}" />
			</div>

		</div>

	</body>

</html>
