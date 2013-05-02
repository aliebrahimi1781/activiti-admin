package adminom



import org.junit.*
import grails.test.mixin.*

@TestFor(HistoryVariableController)
@Mock(HistoryVariable)
class HistoryVariableControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/historyVariable/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.historyVariableInstanceList.size() == 0
        assert model.historyVariableInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.historyVariableInstance != null
    }

    void testSave() {
        controller.save()

        assert model.historyVariableInstance != null
        assert view == '/historyVariable/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/historyVariable/show/1'
        assert controller.flash.message != null
        assert HistoryVariable.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/historyVariable/list'

        populateValidParams(params)
        def historyVariable = new HistoryVariable(params)

        assert historyVariable.save() != null

        params.id = historyVariable.id

        def model = controller.show()

        assert model.historyVariableInstance == historyVariable
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/historyVariable/list'

        populateValidParams(params)
        def historyVariable = new HistoryVariable(params)

        assert historyVariable.save() != null

        params.id = historyVariable.id

        def model = controller.edit()

        assert model.historyVariableInstance == historyVariable
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/historyVariable/list'

        response.reset()

        populateValidParams(params)
        def historyVariable = new HistoryVariable(params)

        assert historyVariable.save() != null

        // test invalid parameters in update
        params.id = historyVariable.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/historyVariable/edit"
        assert model.historyVariableInstance != null

        historyVariable.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/historyVariable/show/$historyVariable.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        historyVariable.clearErrors()

        populateValidParams(params)
        params.id = historyVariable.id
        params.version = -1
        controller.update()

        assert view == "/historyVariable/edit"
        assert model.historyVariableInstance != null
        assert model.historyVariableInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/historyVariable/list'

        response.reset()

        populateValidParams(params)
        def historyVariable = new HistoryVariable(params)

        assert historyVariable.save() != null
        assert HistoryVariable.count() == 1

        params.id = historyVariable.id

        controller.delete()

        assert HistoryVariable.count() == 0
        assert HistoryVariable.get(historyVariable.id) == null
        assert response.redirectedUrl == '/historyVariable/list'
    }
}
