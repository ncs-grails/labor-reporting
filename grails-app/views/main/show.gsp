<%@ page contentType="text/html;charset=UTF-8" %>
<html>  

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="ncs" />
		<title>National Children's Study -  Direct Labor Reporting</title>
		<link rel="stylesheet" type="text/css" href="${resource(dir:'css', file:'labor_reporting.css')}" />
	</head>

	<body>

		<!-- Navigation -->
		<div class="breadcrumbs">
			<g:link controller="applicationManagement" action="list">Application Management</g:link>
		</div>

		<!-- Title -->
		<h1>Labor Reporting</h1>

		<!-- Greeting -->
		Hello ${personInstance.firstName} ${personInstance.lastName}!

		<g:render template="/submission/currentEffort" />
		<g:render template="/submission/pastEffort" />

	</body>

</html>
