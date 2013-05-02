package adminom

import org.springframework.dao.DataIntegrityViolationException

class ProcessInstanceDetailController {

    def filterPaneService

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [processInstanceDetailInstanceList: ProcessInstanceDetail.list(params), processInstanceDetailInstanceTotal: ProcessInstanceDetail.count()]
    }

    def filter = {
        if(!params.max) params.max = 10
        log.debug params
        render( view:'list',
            model:[ processInstanceDetailInstanceList: filterPaneService.filter( params, ProcessInstanceDetail ),
            ProcessInstanceDetailCount: filterPaneService.count( params, ProcessInstanceDetail ),
            filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
            params:params ] )
    }

    def show() {
        def processInstanceDetailInstance = ProcessInstanceDetail.get(params.id)
        if (!processInstanceDetailInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'processInstanceDetail.label', default: 'ProcessInstanceDetail'), params.id])
            redirect action: 'list'
            return
        }

        [processInstanceDetailInstance: processInstanceDetailInstance]
    }

    def displayDiagram() {
    	log.debug "displayDiagram id=${params.id}"
	    def img = orderManagementService.getProcessInstanceDiagram(params.id)
	    if (!img) return

	    response.setIntHeader('Content-length', img.length)
	    response.contentType = 'image/png' // or the appropriate image content type
	    response.outputStream.write(img)
	    response.outputStream.flush()
	}
    
}
