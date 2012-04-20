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

		<div class="breadcrumbs">
			<g:link controller="main" action="show">DLR Home</g:link>
			&gt;
			<g:link controller="applicationManagement" action="list">Application Management</g:link>
		</div>

		<div id="show-assignment" class="content scaffold-show" role="main">
			<h1>Effort Assignment<h1>
				<g:if test="${flash.message}">
					<div class="message" role="status">${flash.message}</div>
				</g:if>
				<ol class="property-list assignment">
				
				</ol>
			<g:form>

				<!--			
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${assignmentInstance?.id}" />
					<g:link class="edit" action="edit" id="${assignmentInstance?.id}">
						<g:message code="default.button.edit.label" default="Edit" />
					</g:link>
					<g:actionSubmit 
						class="delete" 
						action="delete" 
						value="${message(code: 'default.button.delete.label', default: 'Delete')}" 
						onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
				-->
			</g:form>

		</div>

	</body>

</html>
