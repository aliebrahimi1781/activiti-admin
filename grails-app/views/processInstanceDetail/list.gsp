
<%@ page import="adminom.ProcessInstanceDetail" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="bootstrap">
        <g:set var="entityName" value="${message(code: 'processInstanceDetail.label', default: 'ProcessInstanceDetail')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <r:require module="filterpane" />
        <r:require module="modal" />

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
                <filterpane:filterPane domain="ProcessInstanceDetail" filterProperties="businessKey, startTime, endTime, processDefinitionId" showSortPanel="false" showTitle="false"/>    
                <filterpane:filterButton text="Filter data" />

                <g:form class="form-horizontal" action="manageMultiple">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>
                                    <g:checkBox id="select_all" checked="${false}"/>
                                    <r:script>
                                    $('th input:checkbox').click(function(e) {
                                        var table = $(e.target).parents('table:first');
                                        $('td input:checkbox', table).attr('checked', e.target.checked);
                                    });
                                    </r:script>
                                </th>
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
                                <td><g:checkBox id="${processInstanceDetailInstance.id}" name="selected" value="${processInstanceDetailInstance.id}" checked="${false}"/></td>
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
                    <div class="form-actions">
                        <a href="#retry-confirm" role="button" class="btn btn-warning" data-toggle="modal"><g:message code='default.button.retry.label'/></a>
                        
                        <div id="retry-confirm" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                <h3 id="myModalLabel">Retry</h3>
                            </div>
                            <div class="modal-body">
                                <p>You are about to retry XXX process execution.</p>
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
                                <p>You are about to abort XXX process execution.</p>
                            </div>
                            <div class="modal-footer">
                                <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
                                <g:actionSubmit class="btn btn-danger" value="Confirm" action="abortProcess" />
                            </div>
                        </div>
                    </div>
                    
                </g:form>
                <div class="pagination">
                    <bootstrap:paginate total="${ProcessInstanceDetailCount == null ? ProcessInstanceDetail.count(): ProcessInstanceDetailCount}" params="${filterParams}" />
                </div>
            </div>

        </div>
                    
    </body>
</html>
