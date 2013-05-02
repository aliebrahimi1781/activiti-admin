
<%@ page import="adminom.ProcessInstanceDetail" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'processInstanceDetail.label', default: 'ProcessInstanceDetail')} ${processInstanceDetailInstance.id}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">
			
			<div class="span12">

				<div class="page-header">
					<h1><g:message code="default.show.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<div class="container">
					<img src="<g:createLink controller='ProcessInstanceDetail' action='displayDiagram' params='[id:"${processInstanceDetailInstance.id}"]'/>" class="img-polaroid"/>
				</div>

				<dl class="dl-horizontal">
				
					<g:if test="${processInstanceDetailInstance?.businessKey}">
						<dt><g:message code="processInstanceDetail.businessKey.label" default="Business Key" /></dt>
						
							<dd><g:fieldValue bean="${processInstanceDetailInstance}" field="businessKey"/></dd>
						
					</g:if>
				
					<g:if test="${processInstanceDetailInstance?.processDefinitionId}">
						<dt><g:message code="processInstanceDetail.processDefinitionId.label" default="Process Definition Id" /></dt>
						
							<dd><g:fieldValue bean="${processInstanceDetailInstance}" field="processDefinitionId"/><g:link action="show" controller="processDefinition" id="${processInstanceDetailInstance.processDefinitionId}" class="btn btn-mini"><i class="icon-search"></i></g:link></dd>
						
					</g:if>
				
					<g:if test="${processInstanceDetailInstance?.duration}">
						<dt><g:message code="processInstanceDetail.duration.label" default="Duration" /></dt>
						
							<dd><g:fieldValue bean="${processInstanceDetailInstance}" field="duration"/></dd>
						
					</g:if>
				
					<g:if test="${processInstanceDetailInstance?.endActivityId}">
						<dt><g:message code="processInstanceDetail.endActivityId.label" default="End Activity Id" /></dt>
						
							<dd><g:fieldValue bean="${processInstanceDetailInstance}" field="endActivityId"/></dd>
						
					</g:if>
				
					<g:if test="${processInstanceDetailInstance?.endTime}">
						<dt><g:message code="processInstanceDetail.endTime.label" default="End Time" /></dt>
						
							<dd><g:formatDate date="${processInstanceDetailInstance?.endTime}" /></dd>
						
					</g:if>
					
					<g:if test="${processInstanceDetailInstance?.startActivityId}">
						<dt><g:message code="processInstanceDetail.startActivityId.label" default="Start Activity Id" /></dt>
						
							<dd><g:fieldValue bean="${processInstanceDetailInstance}" field="startActivityId"/></dd>
						
					</g:if>
				
					<g:if test="${processInstanceDetailInstance?.startTime}">
						<dt><g:message code="processInstanceDetail.startTime.label" default="Start Time" /></dt>
						
							<dd><g:formatDate date="${processInstanceDetailInstance?.startTime}" /></dd>
						
					</g:if>
				
					<g:if test="${processInstanceDetailInstance?.startUserId}">
						<dt><g:message code="processInstanceDetail.startUserId.label" default="Start User Id" /></dt>
						
							<dd><g:fieldValue bean="${processInstanceDetailInstance}" field="startUserId"/></dd>
						
					</g:if>
				
					
					<g:if test="${processInstanceDetailInstance?.tasks}">
					<dt><g:message code="processInstanceDetail.tasks.label" default="Tasks" /></dt>
					<dd>
						<table class="table table-bordered">
							<thead>
								<tr>
							    	<th><g:message code="task.id.label" default="Task Id" /></th>
							    	<th><g:message code="task.taskName.label" default="Task Name" /></th>
							    	<th><g:message code="task.assignee.label" default="Assignee" /></th>
							    	<th><g:message code="task.description.label" default="Description" /></th>
							    	<th><g:message code="task.owner.label" default="Owner" /></th>
							    	<th><g:message code="task.startTime.label" default="Start Time" /></th>
							    	<th><g:message code="task.taskDueDate.label" default="Due Date" /></th>
							    	<th><g:message code="task.taskDefinitionKey.label" default="Task Definition Key" /></th>
							    	<th><g:message code="task.endTime.label" default="End Time" /></th>
							    	<th><g:message code="task.duration.label" default="Duration" /></th>
							    </tr>
							</thead>
							<tbody>
							<g:each in="${processInstanceDetailInstance.tasks}" var="taskInstance">
							    <tr>
							    	<td>${taskInstance?.id}</td>
							    	<td>${taskInstance?.taskName}</td>
							    	<td>${taskInstance?.assignee}</td>
							    	<td>${taskInstance?.description}</td>
							    	<td>${taskInstance?.owner}</td>
							    	<td>${taskInstance?.startTime}</td>
							    	<td>${taskInstance?.dueDate}</td>
							    	<td>${taskInstance?.taskDefinitionKey}</td>
							    	<td>${taskInstance?.endTime}</td>
							    	<td>${taskInstance.duration}</td>
							    </tr>
							</tbody>
							</g:each>
						</table>
					</dd>
					</g:if>
					
					
					<g:if test="${processInstanceDetailInstance?.activities}">
					<dt><g:message code="processInstanceDetail.activities.label" default="Activities" /></dt>
					<dd>
						<table class="table table-bordered">
							<thead>
								<tr>
							    	<th><g:message code="activity.id.label" default="Activity Id" /></th>
							    	<th><g:message code="activity.activityName.label" default="Activity Name" /></th>
							    	<th><g:message code="activity.activityType.label" default="Activity Type" /></th>
							    	<th><g:message code="activity.duration.label" default="Duration" /></th>
							    	<th><g:message code="activity.endTime.label" default="End Time" /></th>
							    	<th><g:message code="activity.startTime.label" default="Start Time" /></th>
							    </tr>
							</thead>
							<tbody>
							<g:each in="${processInstanceDetailInstance.activities}" var="activityInstance">
							    <tr>
							    	<td>${activityInstance?.id}</td>
							    	<td>${activityInstance?.activityName}</td>
							    	<td>${activityInstance?.activityType}</td>
							    	<td>${activityInstance.duration}</td>
							    	<td>${activityInstance?.endTime}</td>
							    	<td>${activityInstance?.startTime}</td>
							    </tr>
							</tbody>
							</g:each>
						</table>
					</dd>
					</g:if>

					<g:if test="${processInstanceDetailInstance?.variables}">
					<dt><g:message code="processInstanceDetail.variables.label" default="Variables" /></dt>
					<dd>
						<table class="table table-bordered">
							<thead>
								<tr>
							    	<th><g:message code="variable.name.label" default="Variable Name" /></th>
							    	<th><g:message code="variable.type.label" default="Variable Type" /></th>
							    	<th><g:message code="variable.value.label" default="Variable Value" /></th>
							    </tr>
							</thead>
							<tbody>
							<g:each in="${processInstanceDetailInstance.variables}" var="variableInstance">
							    <tr>
							    	<td>${variableInstance?.name}</td>
							    	<td>${variableInstance?.type}</td>
							    	<td>${variableInstance?.value}</td>
							    </tr>
							</tbody>
							</g:each>
						</table>
						
					</dd>
					</g:if>
				
					<g:if test="${processInstanceDetailInstance?.historyVariables}">
					<dt><g:message code="processInstanceDetail.historyVariables.label" default="History Variables" /></dt>
					<dd>
						<table class="table table-bordered">
							<thead>
								<tr>
							    	<th><g:message code="historyVariable.revision.label" default="Revision" /></th>
								    <th><g:message code="historyVariable.variableName.label" default="Variable Name" /></th>
								    <th><g:message code="historyVariable.variableType.label" default="Variable Type" /></th>
								    <th><g:message code="historyVariable.variableValue.label" default="Variable Value" /></th>
							    </tr>
							</thead>
							<tbody>
							<g:each in="${processInstanceDetailInstance.historyVariables}" var="historyVariableInstance">
							    <tr>
							    	<td>${historyVariableInstance.revision}</td>
							    	<td>${historyVariableInstance?.variableName}</td>
							    	<td>${historyVariableInstance?.variableType}</td>
							    	<td>${historyVariableInstance?.variableValue}</td>
							    </tr>
							</tbody>
							</g:each>
						</table>
						
					</dd>
					</g:if>
				</dl>
				

			</div>

		</div>
	</body>
</html>