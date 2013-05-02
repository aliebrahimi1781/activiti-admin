package adminom

import org.springframework.dao.DataIntegrityViolationException

class ProcessDefinitionController {

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [processDefinitionInstanceList: ProcessDefinition.list(params), processDefinitionInstanceTotal: ProcessDefinition.count()]
    }

    def show() {
        def processDefinitionInstance = ProcessDefinition.get(params.id)
        if (!processDefinitionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'processDefinition.label', default: 'ProcessDefinition'), params.id])
            redirect action: 'list'
            return
        }

        [processDefinitionInstance: processDefinitionInstance]
    }

     def displayDiagram() {
        log.debug "displayDiagram id=${params.id}"
        def img = orderManagementService.getProcessDefinitionDiagram(params.id)
        if (!img) return

        response.setIntHeader('Content-length', img.length)
        response.contentType = 'image/png' // or the appropriate image content type
        response.outputStream.write(img)
        response.outputStream.flush()
    }
}
