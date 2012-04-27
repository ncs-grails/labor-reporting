<%@ page import="edu.umn.ncs.labor.Assignment" %>
<!doctype html>
<html>

	<head>
		<meta name="layout" content="ncs">
		<link rel="stylesheet" type="text/css" href="${resource(dir:'css',file:'labor-reporting.css')}" />
		<g:set var="entityName" value="${message(code: 'assignment.label', default: 'Assignment')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>

	<body>

		<sec:ifAnyGranted roles="ROLE_NCS_IT,ROLE_NCS_DLR_MANAGE">

			<!-- NAVIGATION -->
			<div class="breadcrumbs">
				<a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
				&gt;
				<g:link controller="applicationManagement" action="list">Application Management</g:link>
			</div>

			<!-- TITLE -->
			<h1>Effort Assignment</h1>

			<!-- ERROR MESSAGE -->	
			<g:if test="${flash.message}"><div class="message" role="status">${flash.message}</div></g:if>

			<g:form>

				<table>

					<thead>
						<tr>
							<th colspan="11"><strong>${assignmentInstance.period} (Period Id: ${assignmentInstance.period.id})</strong></th>
						</tr>
						<tr>
							<th rowspan="3"></th>
							<th rowspan="2" colspan="2">STAFF<br />(who reports effort)</th>
							<th colspan="5">EFFORT</th>
							<th colspan="2">NOTIFICATION EMAIL</th>
						</tr>
						<tr>
							<th colspan="3">ASSIGNED</th>
							<th rowspan="2">REPORTED</th>
							<th rowspan="2">COMMITTED<br/>(date)</th>
							<th rowspan="2">Dates Email Sent</th>
							<th rowspan="2">Send<br />Now</th>
						</tr>
						<tr>
							<th>Name</th>
							<th>Id</th>
							<th>Previous</th>
							<th>Copy Over</th>
							<th>Current</th>
						</tr>
					</thead>

					<tbody>
						<g:each var="ea" in="${assignmentInstance}">
							<tr>
								<td>${ea.id}</td>
								<td>${ea.person.fullNameLFM}</td>
								<td>${ea.person.id}</td>
								<td>${ea.id}</td>
								<td style="text-align:center;"><input type="checkbox" name"person-${ea.person.id}-copyOver"/></td>
								<td>${ea.id}</td>
								<td>${ea.id}</td>
								<td><g:formatDate date="${ea.certifyDate}" format="yyyy-MM-dd" /></td>
								<td>
									${ea.emails}.join(', ')
								</td>
								<td style="text-align:center;"><input type="checkbox" name"person-${ea.person.id}.sendNow"</td>
							</tr>
						</g:each>
					</tbody>

				</table>

			</g:form>

		</sec:ifAnyGranted>

	</body>

</html>
