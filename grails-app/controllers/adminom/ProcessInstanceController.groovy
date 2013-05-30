package adminom

import org.springframework.dao.DataIntegrityViolationException

class ProcessInstanceController {

    def filterPaneService
    def orderManagementService

    def index() {
        log.debug "action 'index' - ${params}"
        redirect action: 'list', params: params
    }

    def list() {
        log.debug "action 'list' - ${params}"
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [processInstanceInstanceList: ProcessInstance.list(params), processInstanceInstanceTotal: ProcessInstance.count()]
    }

    def filter = {
        log.debug "action 'filter' - ${params}"
        if(!params.max) params.max = 10
        render( view:'list',
            model:[ processInstanceInstanceList: filterPaneService.filter( params, ProcessInstance ),
            ProcessInstanceCount: filterPaneService.count( params, ProcessInstance ),
            filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
            params:params ] )
    }

    def abortListProcess() {
        log.debug "action 'abortListProcess' - ${params}"
        redirect action:'list'
    }

    def retryListProcess() {
        log.debug "action 'retryListProcess' - ${params}"
        redirect action:'list'
    }

}
