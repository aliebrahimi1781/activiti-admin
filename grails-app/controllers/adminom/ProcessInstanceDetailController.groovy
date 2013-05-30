package adminom

import org.springframework.dao.DataIntegrityViolationException

class ProcessInstanceDetailController {

    def orderManagementService

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
