
<%@ page import="adminom.ProcessInstanceDetail" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="bootstrap">
        <g:set var="entityName" value="${message(code: 'processInstanceDetail.label', default: 'ProcessInstanceDetail')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <filterpane:includes />
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
                <filterpane:filterPane domain="ProcessInstanceDetail" filterProperties="businessKey, startTime, endTime, processDefinitionId" />    
                <filterpane:filterButton text="Whatever You Wish" />
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <g:sortableColumn property="businessKey" title="${message(code: 'processInstanceDetail.businessKey.label', default: 'Business Key')}" />
                            <g:sortableColumn property="processDefinitionId" title="${message(code: 'processInstanceDetail.processDefinitionId.label', default: 'Process Definition Id')}" />
                            <g:sortableColumn property="startTime" title="${message(code: 'processInstanceDetail.startTime.label', default: 'Start Time')}" />
                            <g:sortableColumn property="startActivityId" title="${message(code: 'processInstanceDetail.startActivityId.label', default: 'Start Activity Id')}" />
                            <g:sortableColumn property="startUserId" title="${message(code: 'processInstanceDetail.startUserId.label', default: 'Start User Id')}" />
                            <g:sortableColumn property="endTime" title="${message(code: 'processInstanceDetail.endTime.label', default: 'End Time')}" />
                            <g:sortableColumn property="endActivityId" title="${message(code: 'processInstanceDetail.endActivityId.label', default: 'Activity Id')}" />
                            <g:sortableColumn property="duration" title="${message(code: 'processInstanceDetail.duration.label', default: 'Duration')}" />

                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${processInstanceDetailInstanceList}" var="processInstanceDetailInstance">
                        <tr>
                        
                            <td>${fieldValue(bean: processInstanceDetailInstance, field: "businessKey")}</td>
                            <td>${fieldValue(bean: processInstanceDetailInstance, field: "processDefinitionId")}</td>
                            <td><g:formatDate date="${processInstanceDetailInstance?.startTime}" /></td>
                            <td>${fieldValue(bean: processInstanceDetailInstance, field: "startActivityId")}</td>
                            <td>${fieldValue(bean: processInstanceDetailInstance, field: "startUserId")}</td>
                            <td><g:formatDate date="${processInstanceDetailInstance?.endTime}"/></td>
                            <td>${fieldValue(bean: processInstanceDetailInstance, field: "endActivityId")}</td>
                            <td>${fieldValue(bean: processInstanceDetailInstance, field: "duration")}</td>
                            
                            
                            <td class="link">
                                <g:link action="show" id="${processInstanceDetailInstance.id}" class="btn btn-small">Show &raquo;</g:link>
                            </td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
                <div class="pagination">
                    <bootstrap:paginate total="${ProcessInstanceDetailCount == null ? ProcessInstanceDetail.count(): ProcessInstanceDetailCount}" params="${filterParams}" />
                </div>
            </div>

        </div>
    </body>
</html>