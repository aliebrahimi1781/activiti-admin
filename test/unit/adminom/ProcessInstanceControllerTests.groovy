package adminom



import org.junit.*
import grails.test.mixin.*

@TestFor(ProcessInstanceController)
@Mock(ProcessInstance)
class ProcessInstanceControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/processInstance/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.processInstanceInstanceList.size() == 0
        assert model.processInstanceInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.processInstanceInstance != null
    }

    void testSave() {
        controller.save()

        assert model.processInstanceInstance != null
        assert view == '/processInstance/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/processInstance/show/1'
        assert controller.flash.message != null
        assert ProcessInstance.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/processInstance/list'

        populateValidParams(params)
        def processInstance = new ProcessInstance(params)

        assert processInstance.save() != null

        params.id = processInstance.id

        def model = controller.show()

        assert model.processInstanceInstance == processInstance
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/processInstance/list'

        populateValidParams(params)
        def processInstance = new ProcessInstance(params)

        assert processInstance.save() != null

        params.id = processInstance.id

        def model = controller.edit()

        assert model.processInstanceInstance == processInstance
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/processInstance/list'

        response.reset()

        populateValidParams(params)
        def processInstance = new ProcessInstance(params)

        assert processInstance.save() != null

        // test invalid parameters in update
        params.id = processInstance.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/processInstance/edit"
        assert model.processInstanceInstance != null

        processInstance.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/processInstance/show/$processInstance.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        processInstance.clearErrors()

        populateValidParams(params)
        params.id = processInstance.id
        params.version = -1
        controller.update()

        assert view == "/processInstance/edit"
        assert model.processInstanceInstance != null
        assert model.processInstanceInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/processInstance/list'

        response.reset()

        populateValidParams(params)
        def processInstance = new ProcessInstance(params)

        assert processInstance.save() != null
        assert ProcessInstance.count() == 1

        params.id = processInstance.id

        controller.delete()

        assert ProcessInstance.count() == 0
        assert ProcessInstance.get(processInstance.id) == null
        assert response.redirectedUrl == '/processInstance/list'
    }
}
