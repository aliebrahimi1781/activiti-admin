package adminom

import org.springframework.dao.DataIntegrityViolationException

class ProcessInstanceDetailController {

    def filterPaneService
    def orderManagementService

    def index() {
        log.debug "action 'index' - ${params}"
        redirect action: 'list', params: params
    }

    def list() {
        log.debug "action 'list' - ${params}"
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [processInstanceDetailInstanceList: ProcessInstanceDetail.list(params), processInstanceDetailInstanceTotal: ProcessInstanceDetail.count()]
    }

    def filter = {
        log.debug "action 'filter' - ${params}"
        if(!params.max) params.max = 10
        render( view:'list',
            model:[ processInstanceDetailInstanceList: filterPaneService.filter( params, ProcessInstanceDetail ),
            ProcessInstanceDetailCount: filterPaneService.count( params, ProcessInstanceDetail ),
            filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
            params:params ] )
    }

    def abortProcess() {
        log.debug "action 'abortProcess' - ${params}"
        redirect action:'list'
    }

    def retryProcess() {
        log.debug "action 'retryProcess' - ${params}"
        redirect action:'list'
    }

    def show() {
        log.debug "action 'show' - ${params}"
        def processInstanceDetailInstance = ProcessInstanceDetail.get(params.id)
        if (!processInstanceDetailInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'processInstanceDetail.label', default: 'ProcessInstanceDetail'), params.id])
            redirect action: 'list'
            return
        }

        [processInstanceDetailInstance: processInstanceDetailInstance]
    }

    def activityManage() {
        log.debug "action 'show' - ${params}"
        def activity = Activity.get(params.activityid)
        log.debug activity
        render(view:'activity', model:[activityInstance:activity])
    }

    def displayDiagram() {
        log.debug "action 'displayDiagram' - ${params}"
	    def img = orderManagementService.getProcessInstanceDiagram(params.id)
	    if (!img) return

	    response.setIntHeader('Content-length', img.length)
	    response.contentType = 'image/png' // or the appropriate image content type
	    response.outputStream.write(img)
	    response.outputStream.flush()
	}
    
}
