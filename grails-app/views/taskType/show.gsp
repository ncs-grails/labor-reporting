
<%@ page import="edu.umn.ncs.labor.TaskType" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'taskType.label', default: 'TaskType')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-taskType" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-taskType" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list taskType">
			
				<g:if test="${taskTypeInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="taskType.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${taskTypeInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${taskTypeInstance?.active}">
				<li class="fieldcontain">
					<span id="active-label" class="property-label"><g:message code="taskType.active.label" default="Active" /></span>
					
						<span class="property-value" aria-labelledby="active-label"><g:formatBoolean boolean="${taskTypeInstance?.active}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${taskTypeInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="taskType.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${taskTypeInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${taskTypeInstance?.userCreated}">
				<li class="fieldcontain">
					<span id="userCreated-label" class="property-label"><g:message code="taskType.userCreated.label" default="User Created" /></span>
					
						<span class="property-value" aria-labelledby="userCreated-label"><g:fieldValue bean="${taskTypeInstance}" field="userCreated"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${taskTypeInstance?.appCreated}">
				<li class="fieldcontain">
					<span id="appCreated-label" class="property-label"><g:message code="taskType.appCreated.label" default="App Created" /></span>
					
						<span class="property-value" aria-labelledby="appCreated-label"><g:fieldValue bean="${taskTypeInstance}" field="appCreated"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${taskTypeInstance?.id}" />
					<g:link class="edit" action="edit" id="${taskTypeInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
