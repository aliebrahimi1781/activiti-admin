
<%@ page import="adminom.ProcessInstance" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'processInstance.label', default: 'ProcessInstance')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">

			<div class="span12">
				
				<div class="page-header">
					<h1><g:message code="default.list.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				
				<table class="table table-striped">
					<thead>
						<tr>
						
							<g:sortableColumn property="businessKey" title="${message(code: 'processInstance.businessKey.label', default: 'Business Key')}" />
						
							<g:sortableColumn property="processDefinitionId" title="${message(code: 'processInstance.processDefinitionId.label', default: 'Process Definition Id')}" />
						
							<g:sortableColumn property="startTime" title="${message(code: 'processInstance.startTime.label', default: 'Start Time')}" />
						
							<g:sortableColumn property="startUserId" title="${message(code: 'processInstance.startUserId.label', default: 'Start User Id')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${processInstanceInstanceList}" var="processInstanceInstance">
						<tr>
						
							<td>${fieldValue(bean: processInstanceInstance, field: "businessKey")}</td>
						
							<td><g:link controller="ProcessDefinition" action="show" id="${processInstanceInstance.processDefinitionId}">${fieldValue(bean: processInstanceInstance, field: "processDefinitionId")}</g:link></td>
						
							<td><g:formatDate date="${processInstanceInstance.startTime}" /></td>
						
							<td>${fieldValue(bean: processInstanceInstance, field: "startUserId")}</td>
						
							<td class="link">
								<g:link controller="ProcessInstanceDetail" action="show" id="${processInstanceInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${processInstanceInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
