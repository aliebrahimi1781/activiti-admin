
<%@ page import="adminom.ProcessDefinition" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'processDefinition.label', default: 'ProcessDefinition')} '${processDefinitionInstance.id}'" />
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
					<img src="<g:createLink controller='ProcessDefinition' action='displayDiagram' params='[id:"${processDefinitionInstance.id}"]'/>" class="img-polaroid"/>
				</div>
				<dl class="dl-horizontal">
				
					<g:if test="${processDefinitionInstance?.version}">
						<dt><g:message code="processDefinition.version.label" default="Version" /></dt>
						
							<dd><g:fieldValue bean="${processDefinitionInstance}" field="version"/></dd>
						
					</g:if>

					<g:if test="${processDefinitionInstance?.category}">
						<dt><g:message code="processDefinition.category.label" default="Category" /></dt>
						
							<dd><g:fieldValue bean="${processDefinitionInstance}" field="category"/></dd>
						
					</g:if>
				
					<g:if test="${processDefinitionInstance?.deploymentId}">
						<dt><g:message code="processDefinition.deploymentId.label" default="Deployment Id" /></dt>
						
							<dd><g:fieldValue bean="${processDefinitionInstance}" field="deploymentId"/></dd>
						
					</g:if>
				
					<g:if test="${processDefinitionInstance?.diagramResourceName}">
						<dt><g:message code="processDefinition.diagramResourceName.label" default="Diagram Resource Name" /></dt>
						
							<dd><g:fieldValue bean="${processDefinitionInstance}" field="diagramResourceName"/></dd>
						
					</g:if>
				
					<g:if test="${processDefinitionInstance?.isGraphicNotationDefined}">
						<dt><g:message code="processDefinition.isGraphicNotationDefined.label" default="Is Graphic Notation Defined" /></dt>
						
							<dd><g:formatBoolean boolean="${processDefinitionInstance?.isGraphicNotationDefined}" /></dd>
						
					</g:if>
				
					<g:if test="${processDefinitionInstance?.key}">
						<dt><g:message code="processDefinition.key.label" default="Key" /></dt>
						
							<dd><g:fieldValue bean="${processDefinitionInstance}" field="key"/></dd>
						
					</g:if>
				
					<g:if test="${processDefinitionInstance?.name}">
						<dt><g:message code="processDefinition.name.label" default="Name" /></dt>
						
							<dd><g:fieldValue bean="${processDefinitionInstance}" field="name"/></dd>
						
					</g:if>
				
					<g:if test="${processDefinitionInstance?.resourceName}">
						<dt><g:message code="processDefinition.resourceName.label" default="Resource Name" /></dt>
						
							<dd><g:fieldValue bean="${processDefinitionInstance}" field="resourceName"/></dd>
						
					</g:if>
				
					<g:if test="${processDefinitionInstance?.startFormResourceKey}">
						<dt><g:message code="processDefinition.startFormResourceKey.label" default="Start Form Resource Key" /></dt>
						
							<dd><g:fieldValue bean="${processDefinitionInstance}" field="startFormResourceKey"/></dd>
						
					</g:if>
				
				</dl>

			</div>

		</div>
	</body>
</html>
