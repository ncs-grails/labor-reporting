<%@ page import="edu.umn.ncs.labor.Person" %>
<!doctype html>
<html>

	<head>
		<meta name="layout" content="ncs">
		<link rel="stylesheet" type="text/css" href="${resource(dir:'css',file:'labor-reporting.css')}" />
		<g:set var="entityName" value="${message(code: 'person.label', default: 'Person')}" />
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

			<div id="list-person" class="content scaffold-list" role="main">

				<!-- TITLE -->
				<h1><g:message code="default.list.label" args="[entityName]" /></h1>

				<!-- ERROR MESSAGE  -->
				<g:if test="${flash.message}"><div class="message" role="status">${flash.message}</div></g:if>

				<!-- LIST  -->
				<table>

					<thead>
						<tr>
							<g:sortableColumn property="id" title="${message(code: 'person.id.label', default: 'Id')}" />
							<g:sortableColumn property="fullNameLFM" title="${message(code: 'person.fullNameLFM.label', default: 'Name')}" />
							<g:sortableColumn property="title" title="${message(code: 'person.title.label', default: 'Title')}" />
							<g:sortableColumn property="username" title="${message(code: 'person.username.label', default: 'Username')}" />
							<g:sortableColumn property="email" title="${message(code: 'person.email.label', default: 'Email')}" />
							<g:sortableColumn property="reportsEffort" title="${message(code: 'person.reportsEffort.label', default: 'Reports Effort')}" />
						</tr>
					</thead>

					<tbody>
						<g:each in="${personInstanceList}" status="i" var="personInstance">
							<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
								<td>${fieldValue(bean: personInstance, field: "id")}</td>
								<td><g:link action="edit" id="${personInstance.id}">${fieldValue(bean: personInstance, field: "fullNameLFM")}</g:link></td>
								<td>${fieldValue(bean: personInstance, field: "title")}</td>
								<td>${fieldValue(bean: personInstance, field: "username")}</td>
								<td>${fieldValue(bean: personInstance, field: "email")}</td>
								<td>${fieldValue(bean: personInstance, field: "reportsEffort")}</td>
							</tr>
						</g:each>
					</tbody>

				</table>

				<!-- PAGINATION -->
				<div class="pagination"><g:paginate total="${personInstanceTotal}" /></div>

			</div>

		</sec:ifAnyGranted>

	</body>

</html>
