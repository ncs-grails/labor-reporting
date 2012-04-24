<%@ page contentType="text/html;charset=UTF-8" %>
<html>  

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="ncs" />
		<title>Application Management for Labor Reporting</title>
		<link rel="stylesheet" type="text/css" href="${resource(dir:'css', file:'labor-reporting.css')}" />
	</head>

	<body>

		<!-- NAVIGATION -->
		<sec:ifAnyGranted roles="ROLE_NCS_IT,ROLE_NCS_DLR_MANAGE">
			<div class="breadcrumbs">
				<a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
			</div>
		</sec:ifAnyGranted>
		
		<!-- TITLE -->
		<h1>Application Management</h1>

		<sec:ifAnyGranted roles="ROLE_NCS_IT,ROLE_NCS_DLR_MANAGE">

			<!-- MENU SELECTION -->

				<h2>Main</h2>

				<ul>

					<!-- effort assignment -->
					<li class="basicMenu"><g:link controller="assignment" action="show">Effort Assignment</g:link> - enter assigned effort for reporting period</li>

					<!-- reported effort -->
					<li class="basicMenu"><g:link controller="submission" action="list">Reported Effort</g:link> - add, view and edit reported effort</li>

					<!-- staff -->
					<li class="basicMenu"><g:link controller="person" action="list">Staff Information</g:link> - add, view and edit staff information</li>

					<!-- job title -->
					<li class="basicMenu"><g:link controller="title" action="list">Job Title</g:link> - add, view and edit job titles</li>

					<!-- reporting period -->
					<li class="basicMenu"><g:link controller="period" action="list">Reporting Period</g:link> - view reporting periods</li>

					<!-- clasification -->
					<li class="basicMenu"><g:link controller="classification" action="list">Classification</g:link> - add, view and edit classification</li>

					<!-- task -->
					<li class="basicMenu"><g:link controller="task" action="list">Task</g:link> - add, view and edit task</li>

					<!-- reports -->
					<li class="basicMenu"><g:link action="report">Reports</g:link> - view and edit report properties, view completed reports, and generate reports for dissemination</li>

				</ul>

				<h2>Specific to Reporting Source</h2>

				<ul>

					<!-- reference invoice number -->
					<li class="basicMenu"><g:link controller="reportingPeriodedit list">Reference Invoice Number (RIN) for Sponsored Financial Reporting (SFR)</g:link> - enter the RIN for each reporting period</li>

				</ul>

		</sec:ifAnyGranted>

		<sec:ifNotGranted roles="ROLE_NCS" >
			<p>Access denied</p>
		</sec:ifNotGranted>

	</body>

</html>
