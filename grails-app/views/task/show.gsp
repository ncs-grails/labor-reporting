
<%@ page import="edu.umn.ncs.labor.Task" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'task.label', default: 'Task')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-task" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-task" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list task">
			
				<g:if test="${taskInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="task.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${taskInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${taskInstance?.active}">
				<li class="fieldcontain">
					<span id="active-label" class="property-label"><g:message code="task.active.label" default="Active" /></span>
					
						<span class="property-value" aria-labelledby="active-label"><g:formatBoolean boolean="${taskInstance?.active}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${taskInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="task.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${taskInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${taskInstance?.userCreated}">
				<li class="fieldcontain">
					<span id="userCreated-label" class="property-label"><g:message code="task.userCreated.label" default="User Created" /></span>
					
						<span class="property-value" aria-labelledby="userCreated-label"><g:fieldValue bean="${taskInstance}" field="userCreated"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${taskInstance?.appCreated}">
				<li class="fieldcontain">
					<span id="appCreated-label" class="property-label"><g:message code="task.appCreated.label" default="App Created" /></span>
					
						<span class="property-value" aria-labelledby="appCreated-label"><g:fieldValue bean="${taskInstance}" field="appCreated"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${taskInstance?.equivalentTasks}">
				<li class="fieldcontain">
					<span id="equivalentTasks-label" class="property-label"><g:message code="task.equivalentTasks.label" default="Equivalent Tasks" /></span>
					
						<g:each in="${taskInstance.equivalentTasks}" var="e">
						<span class="property-value" aria-labelledby="equivalentTasks-label"><g:link controller="task" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${taskInstance?.type}">
				<li class="fieldcontain">
					<span id="type-label" class="property-label"><g:message code="task.type.label" default="Type" /></span>
					
						<span class="property-value" aria-labelledby="type-label"><g:link controller="taskType" action="show" id="${taskInstance?.type?.id}">${taskInstance?.type?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${taskInstance?.id}" />
					<g:link class="edit" action="edit" id="${taskInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
