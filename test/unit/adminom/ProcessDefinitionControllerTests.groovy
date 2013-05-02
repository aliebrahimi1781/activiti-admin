package adminom



import org.junit.*
import grails.test.mixin.*

@TestFor(ProcessDefinitionController)
@Mock(ProcessDefinition)
class ProcessDefinitionControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/processDefinition/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.processDefinitionInstanceList.size() == 0
        assert model.processDefinitionInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.processDefinitionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.processDefinitionInstance != null
        assert view == '/processDefinition/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/processDefinition/show/1'
        assert controller.flash.message != null
        assert ProcessDefinition.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/processDefinition/list'

        populateValidParams(params)
        def processDefinition = new ProcessDefinition(params)

        assert processDefinition.save() != null

        params.id = processDefinition.id

        def model = controller.show()

        assert model.processDefinitionInstance == processDefinition
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/processDefinition/list'

        populateValidParams(params)
        def processDefinition = new ProcessDefinition(params)

        assert processDefinition.save() != null

        params.id = processDefinition.id

        def model = controller.edit()

        assert model.processDefinitionInstance == processDefinition
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/processDefinition/list'

        response.reset()

        populateValidParams(params)
        def processDefinition = new ProcessDefinition(params)

        assert processDefinition.save() != null

        // test invalid parameters in update
        params.id = processDefinition.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/processDefinition/edit"
        assert model.processDefinitionInstance != null

        processDefinition.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/processDefinition/show/$processDefinition.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        processDefinition.clearErrors()

        populateValidParams(params)
        params.id = processDefinition.id
        params.version = -1
        controller.update()

        assert view == "/processDefinition/edit"
        assert model.processDefinitionInstance != null
        assert model.processDefinitionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/processDefinition/list'

        response.reset()

        populateValidParams(params)
        def processDefinition = new ProcessDefinition(params)

        assert processDefinition.save() != null
        assert ProcessDefinition.count() == 1

        params.id = processDefinition.id

        controller.delete()

        assert ProcessDefinition.count() == 0
        assert ProcessDefinition.get(processDefinition.id) == null
        assert response.redirectedUrl == '/processDefinition/list'
    }
}
