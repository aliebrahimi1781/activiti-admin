
<%@ page import="adminom.ProcessDefinition" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'processDefinition.label', default: 'ProcessDefinition')}" />
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
						
							<g:sortableColumn property="category" title="${message(code: 'processDefinition.category.label', default: 'Category')}" />
						
							<g:sortableColumn property="deploymentId" title="${message(code: 'processDefinition.deploymentId.label', default: 'Deployment Id')}" />
						
							<g:sortableColumn property="diagramResourceName" title="${message(code: 'processDefinition.diagramResourceName.label', default: 'Diagram Resource Name')}" />
						
							<g:sortableColumn property="isGraphicNotationDefined" title="${message(code: 'processDefinition.isGraphicNotationDefined.label', default: 'Is Graphic Notation Defined')}" />
						
							<g:sortableColumn property="key" title="${message(code: 'processDefinition.key.label', default: 'Key')}" />
						
							<g:sortableColumn property="name" title="${message(code: 'processDefinition.name.label', default: 'Name')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${processDefinitionInstanceList}" var="processDefinitionInstance">
						<tr>
						
							<td>${fieldValue(bean: processDefinitionInstance, field: "category")}</td>
						
							<td>${fieldValue(bean: processDefinitionInstance, field: "deploymentId")}</td>
						
							<td>${fieldValue(bean: processDefinitionInstance, field: "diagramResourceName")}</td>
						
							<td><g:formatBoolean boolean="${processDefinitionInstance.isGraphicNotationDefined}" /></td>
						
							<td>${fieldValue(bean: processDefinitionInstance, field: "key")}</td>
						
							<td>${fieldValue(bean: processDefinitionInstance, field: "name")}</td>
						
							<td class="link">
								<g:link action="show" id="${processDefinitionInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${processDefinitionInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
