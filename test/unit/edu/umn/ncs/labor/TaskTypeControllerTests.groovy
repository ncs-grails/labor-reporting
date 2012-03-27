package edu.umn.ncs.labor



import org.junit.*
import grails.test.mixin.*

@TestFor(TaskTypeController)
@Mock(TaskType)
class TaskTypeControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/taskType/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.taskTypeInstanceList.size() == 0
        assert model.taskTypeInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.taskTypeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.taskTypeInstance != null
        assert view == '/taskType/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/taskType/show/1'
        assert controller.flash.message != null
        assert TaskType.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/taskType/list'


        populateValidParams(params)
        def taskType = new TaskType(params)

        assert taskType.save() != null

        params.id = taskType.id

        def model = controller.show()

        assert model.taskTypeInstance == taskType
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/taskType/list'


        populateValidParams(params)
        def taskType = new TaskType(params)

        assert taskType.save() != null

        params.id = taskType.id

        def model = controller.edit()

        assert model.taskTypeInstance == taskType
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/taskType/list'

        response.reset()


        populateValidParams(params)
        def taskType = new TaskType(params)

        assert taskType.save() != null

        // test invalid parameters in update
        params.id = taskType.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/taskType/edit"
        assert model.taskTypeInstance != null

        taskType.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/taskType/show/$taskType.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        taskType.clearErrors()

        populateValidParams(params)
        params.id = taskType.id
        params.version = -1
        controller.update()

        assert view == "/taskType/edit"
        assert model.taskTypeInstance != null
        assert model.taskTypeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/taskType/list'

        response.reset()

        populateValidParams(params)
        def taskType = new TaskType(params)

        assert taskType.save() != null
        assert TaskType.count() == 1

        params.id = taskType.id

        controller.delete()

        assert TaskType.count() == 0
        assert TaskType.get(taskType.id) == null
        assert response.redirectedUrl == '/taskType/list'
    }
}
