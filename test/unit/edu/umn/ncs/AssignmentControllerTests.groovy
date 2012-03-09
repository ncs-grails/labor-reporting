package edu.umn.ncs



import org.junit.*
import grails.test.mixin.*

@TestFor(AssignmentController)
@Mock(Assignment)
class AssignmentControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/assignment/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.assignmentInstanceList.size() == 0
        assert model.assignmentInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.assignmentInstance != null
    }

    void testSave() {
        controller.save()

        assert model.assignmentInstance != null
        assert view == '/assignment/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/assignment/show/1'
        assert controller.flash.message != null
        assert Assignment.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/assignment/list'


        populateValidParams(params)
        def assignment = new Assignment(params)

        assert assignment.save() != null

        params.id = assignment.id

        def model = controller.show()

        assert model.assignmentInstance == assignment
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/assignment/list'


        populateValidParams(params)
        def assignment = new Assignment(params)

        assert assignment.save() != null

        params.id = assignment.id

        def model = controller.edit()

        assert model.assignmentInstance == assignment
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/assignment/list'

        response.reset()


        populateValidParams(params)
        def assignment = new Assignment(params)

        assert assignment.save() != null

        // test invalid parameters in update
        params.id = assignment.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/assignment/edit"
        assert model.assignmentInstance != null

        assignment.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/assignment/show/$assignment.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        assignment.clearErrors()

        populateValidParams(params)
        params.id = assignment.id
        params.version = -1
        controller.update()

        assert view == "/assignment/edit"
        assert model.assignmentInstance != null
        assert model.assignmentInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/assignment/list'

        response.reset()

        populateValidParams(params)
        def assignment = new Assignment(params)

        assert assignment.save() != null
        assert Assignment.count() == 1

        params.id = assignment.id

        controller.delete()

        assert Assignment.count() == 0
        assert Assignment.get(assignment.id) == null
        assert response.redirectedUrl == '/assignment/list'
    }
}
