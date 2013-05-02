package adminom



import org.junit.*
import grails.test.mixin.*

@TestFor(ProcessInstanceDetailController)
@Mock(ProcessInstanceDetail)
class ProcessInstanceDetailControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/processInstanceDetail/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.processInstanceDetailInstanceList.size() == 0
        assert model.processInstanceDetailInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.processInstanceDetailInstance != null
    }

    void testSave() {
        controller.save()

        assert model.processInstanceDetailInstance != null
        assert view == '/processInstanceDetail/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/processInstanceDetail/show/1'
        assert controller.flash.message != null
        assert ProcessInstanceDetail.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/processInstanceDetail/list'

        populateValidParams(params)
        def processInstanceDetail = new ProcessInstanceDetail(params)

        assert processInstanceDetail.save() != null

        params.id = processInstanceDetail.id

        def model = controller.show()

        assert model.processInstanceDetailInstance == processInstanceDetail
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/processInstanceDetail/list'

        populateValidParams(params)
        def processInstanceDetail = new ProcessInstanceDetail(params)

        assert processInstanceDetail.save() != null

        params.id = processInstanceDetail.id

        def model = controller.edit()

        assert model.processInstanceDetailInstance == processInstanceDetail
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/processInstanceDetail/list'

        response.reset()

        populateValidParams(params)
        def processInstanceDetail = new ProcessInstanceDetail(params)

        assert processInstanceDetail.save() != null

        // test invalid parameters in update
        params.id = processInstanceDetail.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/processInstanceDetail/edit"
        assert model.processInstanceDetailInstance != null

        processInstanceDetail.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/processInstanceDetail/show/$processInstanceDetail.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        processInstanceDetail.clearErrors()

        populateValidParams(params)
        params.id = processInstanceDetail.id
        params.version = -1
        controller.update()

        assert view == "/processInstanceDetail/edit"
        assert model.processInstanceDetailInstance != null
        assert model.processInstanceDetailInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/processInstanceDetail/list'

        response.reset()

        populateValidParams(params)
        def processInstanceDetail = new ProcessInstanceDetail(params)

        assert processInstanceDetail.save() != null
        assert ProcessInstanceDetail.count() == 1

        params.id = processInstanceDetail.id

        controller.delete()

        assert ProcessInstanceDetail.count() == 0
        assert ProcessInstanceDetail.get(processInstanceDetail.id) == null
        assert response.redirectedUrl == '/processInstanceDetail/list'
    }
}
