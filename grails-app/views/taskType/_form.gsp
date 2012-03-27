<%@ page import="edu.umn.ncs.labor.TaskType" %>



<div class="fieldcontain ${hasErrors(bean: taskTypeInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="taskType.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="100" required="" value="${taskTypeInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: taskTypeInstance, field: 'active', 'error')} ">
	<label for="active">
		<g:message code="taskType.active.label" default="Active" />
		
	</label>
	<g:checkBox name="active" value="${taskTypeInstance?.active}" />
</div>

<div class="fieldcontain ${hasErrors(bean: taskTypeInstance, field: 'userCreated', 'error')} required">
	<label for="userCreated">
		<g:message code="taskType.userCreated.label" default="User Created" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="userCreated" required="" value="${taskTypeInstance?.userCreated}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: taskTypeInstance, field: 'appCreated', 'error')} required">
	<label for="appCreated">
		<g:message code="taskType.appCreated.label" default="App Created" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="appCreated" required="" value="${taskTypeInstance?.appCreated}"/>
</div>

