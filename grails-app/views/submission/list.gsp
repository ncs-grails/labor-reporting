<%@ page import="edu.umn.ncs.labor.Submission" %>
<!doctype html>
<html>

	<head>
		<meta name="layout" content="ncs">
		<link rel="stylesheet" type="text/css" href="${resource(dir:'css',file:'labor-reporting.css')}" />
		<g:set var="entityName" value="${message(code: 'submission.label', default: 'Submission')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>

	<body>

		<div class="breadcrumbs">
			<g:link controller="main" action="show">DLR Home</g:link>
			&gt;
			<g:link controller="applicationManagement" action="list">Application Management</g:link>
		</div>

		<div id="list-submission" class="content scaffold-list" role="main">

			<h1>Reported Effort</h1>
			
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>

			<table>
				<thead>
					<tr>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${submissionInstanceList}" status="i" var="submissionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
					</tr>
				</g:each>
				</tbody>
			</table>

			<div class="pagination">
				<g:paginate total="${submissionInstanceTotal}" />
			</div>

		</div>

	</body>

</html>
