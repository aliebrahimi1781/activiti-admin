package adminom

import org.springframework.dao.DataIntegrityViolationException

class ProcessInstanceController {
	def orderManagementService

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def response = orderManagementService.getProcessInstances()
        if (response == null) {
        	flash.message = message(code: 'default.order.Management.unreachable.message')
            return [processInstanceInstanceList: [], processInstanceInstanceTotal: 0]
        } else {
        	return [processInstanceInstanceList: response.process, processInstanceInstanceTotal: 100]
        }
    }

    def show() {
        def processInstanceInstance = ProcessInstance.get(params.id)
        if (!processInstanceInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'processInstance.label', default: 'ProcessInstance'), params.id])
            redirect action: 'list'
            return
        }

        [processInstanceInstance: processInstanceInstance]
    }

    
}
