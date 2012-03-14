package edu.umn.ncs



import org.junit.*
import grails.test.mixin.*

@TestFor(TaskOperationsDataElementsController)
@Mock(TaskOperationsDataElements)
class TaskOperationsDataElementsControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/taskOperationsDataElements/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.taskOperationsDataElementsInstanceList.size() == 0
        assert model.taskOperationsDataElementsInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.taskOperationsDataElementsInstance != null
    }

    void testSave() {
        controller.save()

        assert model.taskOperationsDataElementsInstance != null
        assert view == '/taskOperationsDataElements/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/taskOperationsDataElements/show/1'
        assert controller.flash.message != null
        assert TaskOperationsDataElements.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/taskOperationsDataElements/list'


        populateValidParams(params)
        def taskOperationsDataElements = new TaskOperationsDataElements(params)

        assert taskOperationsDataElements.save() != null

        params.id = taskOperationsDataElements.id

        def model = controller.show()

        assert model.taskOperationsDataElementsInstance == taskOperationsDataElements
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/taskOperationsDataElements/list'


        populateValidParams(params)
        def taskOperationsDataElements = new TaskOperationsDataElements(params)

        assert taskOperationsDataElements.save() != null

        params.id = taskOperationsDataElements.id

        def model = controller.edit()

        assert model.taskOperationsDataElementsInstance == taskOperationsDataElements
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/taskOperationsDataElements/list'

        response.reset()


        populateValidParams(params)
        def taskOperationsDataElements = new TaskOperationsDataElements(params)

        assert taskOperationsDataElements.save() != null

        // test invalid parameters in update
        params.id = taskOperationsDataElements.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/taskOperationsDataElements/edit"
        assert model.taskOperationsDataElementsInstance != null

        taskOperationsDataElements.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/taskOperationsDataElements/show/$taskOperationsDataElements.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        taskOperationsDataElements.clearErrors()

        populateValidParams(params)
        params.id = taskOperationsDataElements.id
        params.version = -1
        controller.update()

        assert view == "/taskOperationsDataElements/edit"
        assert model.taskOperationsDataElementsInstance != null
        assert model.taskOperationsDataElementsInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/taskOperationsDataElements/list'

        response.reset()

        populateValidParams(params)
        def taskOperationsDataElements = new TaskOperationsDataElements(params)

        assert taskOperationsDataElements.save() != null
        assert TaskOperationsDataElements.count() == 1

        params.id = taskOperationsDataElements.id

        controller.delete()

        assert TaskOperationsDataElements.count() == 0
        assert TaskOperationsDataElements.get(taskOperationsDataElements.id) == null
        assert response.redirectedUrl == '/taskOperationsDataElements/list'
    }
}
