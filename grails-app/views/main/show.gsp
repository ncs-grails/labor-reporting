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

		<!-- page title -->
		<h1>Direct Labor Reporting</h1>

		<!-- Greeting -->
		<span class="fontMaroon">Hello ${personInstance.firstName} ${personInstance.lastName}!</span> 

		<g:render template="/submission/pastEffort" />

	</body>

</html>
