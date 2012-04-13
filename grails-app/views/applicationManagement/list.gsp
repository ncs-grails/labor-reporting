<%@ page contentType="text/html;charset=UTF-8" %>
<html>  

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="ncs" />
		<title>Application Management for Labor Reporting</title>
		<link rel="stylesheet" type="text/css" href="${resource(dir:'css', file:'labor_reporting.css')}" />
	</head>

	<body>

		<!-- GREETING -->
		<span class="fontMaroon">Hello ${personInstance.firstName} ${personInstance.lastName}!</span> 

		<sec:ifAnyGranted roles="ROLE_NCS_IT,ROLE_NCS_DLR_MANAGE"></sec:ifAnyGranted>

			<!-- MENU SELECTION -->
			<dl>

				<!-- effort assignment -->
				<dt class="basicMenu"><g:link controller="assignment" action="show">Effort Assignment</g:link> - enter assigned effort for reporting period</dt>

				<!-- reported effort -->
				<dt class="basicMenu"><g:link controller="submission" action="list">Reported Effort</g:link> - add, view and edit reported effort</dt>

				<!-- staff -->
				<dt class="basicMenu"><g:link controller="person" action="list">Staff Information</g:link> - add, view and edit staff information</dt>

				<!-- job title -->
				<dt class="basicMenu"><g:link controller="title" action="list">Job Title</g:link> - add, view and edit job titles</dt>

				<!-- reporting period -->
				<dt class="basicMenu"><g:link controller="period" action="list">Reporting Period</g:link> - view reporting periods</dt>

				<!-- clasification -->
				<dt class="basicMenu"><g:link controller="classification" action="list">Classification</g:link> - add, view and edit classification</dt>

				<!-- task -->
				<dt class="basicMenu"><g:link controller="task" action="list">Task</g:link> - add, view and edit task</dt>

				<!-- reference invoice number -->
				<dt class="basicMenu"><g:link controller="reportingPeriodedit list">Reference Invoice Number	</g:link> - enter the RIN for each reporting period</dt>

				<!-- reports -->
				<dt class="basicMenu"><g:link action="report">Reports</g:link> - view and edit report properties, view completed reports, and generate reports for dissemination</dt>

			</dl>

		<sec:ifAnyGranted roles="ROLE_NCS_IT,ROLE_NCS_DLR_MANAGE"></sec:ifAnyGranted>

	</body>

</html>
