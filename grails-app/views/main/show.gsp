<%@ page contentType="text/html;charset=UTF-8" %>
<html>  

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="ncs" />
		<title>National Children's Study -  Direct Labor Reporting</title>
		<link rel="stylesheet" type="text/css" href="${resource(dir:'css', file:'labor-reporting.css')}" />
	</head>

	<body>

		<!-- NAVIGATION -->
		<sec:ifAnyGranted roles="ROLE_NCS_IT,ROLE_NCS_DLR_MANAGE">
			<div class="breadcrumbs">
				<g:link controller="applicationManagement" action="list">Application Management</g:link>
			</div>
		</sec:ifAnyGranted>		

		<!-- TITLE -->
		<h1>Labor Reporting</h1>

		<!-- GREETING -->
		Hello ${personInstance.firstName} ${personInstance.lastName}!

		<!-- CURRENT EFFORT TO REPORT  -->
		<g:render template="/submission/currentEffort" />

		<!-- PAST REPORTED EFFORT  -->
		<g:render template="/submission/pastEffort" />

	</body>

</html>
