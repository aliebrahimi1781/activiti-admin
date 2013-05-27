
<%@ page import="adminom.ProcessInstanceDetail" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'processInstanceDetail.label', default: 'ProcessInstanceDetail')} ${processInstanceDetailInstance.id}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
		<r:require module="modal" />

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
				<g:form class="form-horizontal" action="manage" id="${processInstanceDetailInstance.id}">
					<div class="form-actions">
                        <a href="#retry-confirm" role="button" class="btn btn-warning" data-toggle="modal"><g:message code='default.button.retry.label'/></a>
                        
                        <div id="retry-confirm" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                <h3 id="myModalLabel">Retry</h3>
                            </div>
                            <div class="modal-body">
                                <p>You are about to retry this process execution.</p>
                            </div>
                            <div class="modal-footer">
                                <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
                                <g:actionSubmit class="btn btn-warning" value="Confirm" action="retryProcess" />
                            </div>
                        </div>
                        
                        <a href="#abort-confirm" role="button" class="btn btn-danger" data-toggle="modal"><g:message code='default.button.abort.label'/></a>
                        
                        <div id="abort-confirm" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                <h3 id="myModalLabel">Retry</h3>
                            </div>
                            <div class="modal-body">
                                <p>You are about to abort this process execution.</p>
                            </div>
                            <div class="modal-footer">
                                <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
                                <g:actionSubmit class="btn btn-danger" value="Confirm" action="abortProcess" />
                            </div>
                        </div>
                    </div>
                    
                    </div>
                </g:form>
				<dl class="dl-horizontal">
				
					<g:if test="${processInstanceDetailInstance?.businessKey}">
						<dt><g:message code="processInstanceDetail.businessKey.label" default="Business Key" /></dt>
						
							<dd><g:fieldValue bean="${processInstanceDetailInstance}" field="businessKey"/></dd>
						
					</g:if>
				
					<g:if test="${processInstanceDetailInstance?.processDefinitionId}">
						<dt><g:message code="processInstanceDetail.processDefinitionId.label" default="Process Definition Id" /></dt>
						
							<dd><g:fieldValue bean="${processInstanceDetailInstance}" field="processDefinitionId"/><g:link action="show" controller="processDefinition" id="${processInstanceDetailInstance.processDefinitionId}" class="btn btn-mini"><i class="icon-search"></i></g:link></dd>
						
					</g:if>
				
					<g:if test="${processInstanceDetailInstance?.startTime}">
						<dt><g:message code="processInstanceDetail.startTime.label" default="Start Time" /></dt>
						
							<dd><g:formatDate date="${processInstanceDetailInstance?.startTime}" /></dd>
						
					</g:if>
				
					<g:if test="${processInstanceDetailInstance?.endTime}">
						<dt><g:message code="processInstanceDetail.endTime.label" default="End Time" /></dt>
						
							<dd><g:formatDate date="${processInstanceDetailInstance?.endTime}" /></dd>
						
					</g:if>
					
					<g:if test="${processInstanceDetailInstance?.duration}">
						<dt><g:message code="processInstanceDetail.duration.label" default="Duration" /></dt>
						
							<dd><g:fieldValue bean="${processInstanceDetailInstance}" field="duration"/></dd>
						
					</g:if>
				
					<g:if test="${processInstanceDetailInstance?.startActivityId}">
						<dt><g:message code="processInstanceDetail.startActivityId.label" default="Start Activity Id" /></dt>
						
							<dd><g:fieldValue bean="${processInstanceDetailInstance}" field="startActivityId"/></dd>
						
					</g:if>
				
					<g:if test="${processInstanceDetailInstance?.endActivityId}">
						<dt><g:message code="processInstanceDetail.endActivityId.label" default="End Activity Id" /></dt>
						
							<dd><g:fieldValue bean="${processInstanceDetailInstance}" field="endActivityId"/></dd>
						
					</g:if>
				
					<g:if test="${processInstanceDetailInstance?.startUserId}">
						<dt><g:message code="processInstanceDetail.startUserId.label" default="Start User Id" /></dt>
						
							<dd><g:fieldValue bean="${processInstanceDetailInstance}" field="startUserId"/></dd>
						
					</g:if>
				</dl>
				
					
				<g:if test="${processInstanceDetailInstance?.tasks}">
				<h5><g:message code="processInstanceDetail.tasks.label" default="Tasks" /></h5>
					<table class="table table-bordered">
						<thead>
							<tr>
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
						    	<td>${taskInstance?.taskName}</td>
						    	<td>${taskInstance?.assignee}</td>
						    	<td>${taskInstance?.description}</td>
						    	<td>${taskInstance?.owner}</td>
						    	<td><g:formatDate date="${taskInstance?.startTime}"/></td>
						    	<td><g:formatDate date="${taskInstance?.dueDate}"/></td>
						    	<td>${taskInstance?.taskDefinitionKey}</td>
						    	<td><g:formatDate date="${taskInstance?.endTime}"/></td>
						    	<td>${taskInstance.duration}</td>
						    </tr>
						</tbody>
						</g:each>
					</table>
				</g:if>
				
					
				<g:if test="${processInstanceDetailInstance?.activities}">
					<h5><g:message code="processInstanceDetail.activities.label" default="Activities" /></h5>
					<table class="table table-bordered">
						<thead>
							<tr>
								<th/>
						    	<th><g:message code="activity.activityName.label" default="Activity Name" /></th>
						    	<th><g:message code="activity.activityType.label" default="Activity Type" /></th>
						    	<th><g:message code="activity.startTime.label" default="Start Time" /></th>
						    	<th><g:message code="activity.endTime.label" default="End Time" /></th>
						    	<th><g:message code="activity.duration.label" default="Duration" /></th>
						    	<th/>
						    </tr>
						</thead>
						<tbody>
						<g:each in="${processInstanceDetailInstance.activities}" var="activityInstance">
						    <tr>
						    	<td>${activityInstance?.id}</td>
						    	<td>${activityInstance?.activityName}</td>
						    	<td>${activityInstance?.activityType}</td>
						    	<td><g:formatDate date="${activityInstance?.startTime}"/></td>
						    	<td><g:formatDate date="${activityInstance?.endTime}"/></td>
						    	<td>${activityInstance.duration}</td>
                                <td>
                                	<button class="manage-activity btn" data-toggle="modal" href="${g.createLink(controller:'processInstanceDetail',action:'activityManage', params:[activityid:activityInstance.id])}">Manage</button>
                                	
                                </td>
						    </tr>
						</tbody>
						</g:each>
					</table>
					<div id="manage-activity-modal" class="modal hide" tabindex="-1"></div>
                	<r:script>
                		var $modal = $('#manage-activity-modal');

						$('.manage-activity').on('click', function(){
						  // create the backdrop and wait for next modal to be triggered
						  $('body').modalmanager('loading');
						  var $url= $(this).attr('href')
						  setTimeout(function(){
						     $modal.load($url, '', function(){
						      $modal.modal();
						    });
						  }, 1000);
						});
                	</r:script>
				</g:if>
				
				<g:if test="${processInstanceDetailInstance?.variables}">
					<h5><g:message code="processInstanceDetail.variables.label" default="Variables" /></h5>
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
				</g:if>
			
				<g:if test="${processInstanceDetailInstance?.historyVariables}">
					<h5><g:message code="processInstanceDetail.historyVariables.label" default="History Variables" /></h5>
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
					
				</g:if>
				

			</div>

		</div>
		
	</body>
</html>
