
<%@ page import="adminom.ProcessInstance" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'processInstance.label', default: 'ProcessInstance')} ${processInstanceInstance?.id}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">
			
			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">${entityName}</li>
						<li>
							<g:link class="list" action="list">
								<i class="icon-list"></i>
								<g:message code="default.list.label" args="[entityName]" />
							</g:link>
						</li>
						<li>
							<g:link class="create" action="create">
								<i class="icon-plus"></i>
								<g:message code="default.create.label" args="[entityName]" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>
			
			<div class="span9">

				<div class="page-header">
					<h1><g:message code="default.show.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<dl>
				
					<g:if test="${processInstanceInstance?.businessKey}">
						<dt><g:message code="processInstance.businessKey.label" default="Business Key" /></dt>
						
							<dd><g:fieldValue bean="${processInstanceInstance}" field="businessKey"/></dd>
						
					</g:if>
				
					<g:if test="${processInstanceInstance?.processDefinitionId}">
						<dt><g:message code="processInstance.processDefinitionId.label" default="Process Definition Id" /></dt>
						
							<dd><g:fieldValue bean="${processInstanceInstance}" field="processDefinitionId"/></dd>
						
					</g:if>
				
					<g:if test="${processInstanceInstance?.startTime}">
						<dt><g:message code="processInstance.startTime.label" default="Start Time" /></dt>
						
							<dd><g:formatDate date="${processInstanceInstance?.startTime}" /></dd>
						
					</g:if>
				
					<g:if test="${processInstanceInstance?.startUserId}">
						<dt><g:message code="processInstance.startUserId.label" default="Start User Id" /></dt>
						
							<dd><g:fieldValue bean="${processInstanceInstance}" field="startUserId"/></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${processInstanceInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${processInstanceInstance?.id}">
							<i class="icon-pencil"></i>
							<g:message code="default.button.edit.label" default="Edit" />
						</g:link>
						<button class="btn btn-danger" type="submit" name="_action_delete">
							<i class="icon-trash icon-white"></i>
							<g:message code="default.button.delete.label" default="Delete" />
						</button>
					</div>
				</g:form>

			</div>

		</div>
	</body>
</html>
