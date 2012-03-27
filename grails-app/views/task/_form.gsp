<%@ page import="edu.umn.ncs.labor.Task" %>



<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="task.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="60" required="" value="${taskInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'active', 'error')} ">
	<label for="active">
		<g:message code="task.active.label" default="Active" />
		
	</label>
	<g:checkBox name="active" value="${taskInstance?.active}" />
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'userCreated', 'error')} required">
	<label for="userCreated">
		<g:message code="task.userCreated.label" default="User Created" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="userCreated" required="" value="${taskInstance?.userCreated}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'appCreated', 'error')} required">
	<label for="appCreated">
		<g:message code="task.appCreated.label" default="App Created" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="appCreated" required="" value="${taskInstance?.appCreated}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'equivalentTasks', 'error')} ">
	<label for="equivalentTasks">
		<g:message code="task.equivalentTasks.label" default="Equivalent Tasks" />
		
	</label>
	<g:select name="equivalentTasks" from="${edu.umn.ncs.labor.Task.list()}" multiple="multiple" optionKey="id" size="5" value="${taskInstance?.equivalentTasks*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'type', 'error')} required">
	<label for="type">
		<g:message code="task.type.label" default="Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="type" name="type.id" from="${edu.umn.ncs.labor.TaskType.list()}" optionKey="id" required="" value="${taskInstance?.type?.id}" class="many-to-one"/>
</div>

