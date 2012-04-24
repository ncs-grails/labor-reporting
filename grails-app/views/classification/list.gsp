<%@ page import="edu.umn.ncs.labor.Classification" %>
<!doctype html>
<html>i

	<head>
		<meta name="layout" content="ncs">
		<link rel="stylesheet" type="text/css" href="${resource(dir:'css',file:'labor-reporting.css')}" />
		<g:set var="entityName" value="${message(code: 'classification.label', default: 'Classification')}" />
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

			<div id="list-classification" class="content scaffold-list" role="main">
			
				<!-- TITLE -->
				<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			
				<!-- ERROR MESSAGE -->	
				<g:if test="${flash.message}"><div class="message" role="status">${flash.message}</div></g:if>
				
				<!-- LIST  -->
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

				<div class="pagination"><g:paginate total="${classificationInstanceTotal}" /></div>

			</div>

		</sec:ifAnyGranted>

	</body>

</html>
