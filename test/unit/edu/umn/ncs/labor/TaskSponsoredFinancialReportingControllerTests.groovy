package edu.umn.ncs



import org.junit.*
import grails.test.mixin.*

@TestFor(TaskSponsoredFinancialReportingController)
@Mock(TaskSponsoredFinancialReporting)
class TaskSponsoredFinancialReportingControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/taskSponsoredFinancialReporting/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.taskSponsoredFinancialReportingInstanceList.size() == 0
        assert model.taskSponsoredFinancialReportingInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.taskSponsoredFinancialReportingInstance != null
    }

    void testSave() {
        controller.save()

        assert model.taskSponsoredFinancialReportingInstance != null
        assert view == '/taskSponsoredFinancialReporting/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/taskSponsoredFinancialReporting/show/1'
        assert controller.flash.message != null
        assert TaskSponsoredFinancialReporting.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/taskSponsoredFinancialReporting/list'


        populateValidParams(params)
        def taskSponsoredFinancialReporting = new TaskSponsoredFinancialReporting(params)

        assert taskSponsoredFinancialReporting.save() != null

        params.id = taskSponsoredFinancialReporting.id

        def model = controller.show()

        assert model.taskSponsoredFinancialReportingInstance == taskSponsoredFinancialReporting
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/taskSponsoredFinancialReporting/list'


        populateValidParams(params)
        def taskSponsoredFinancialReporting = new TaskSponsoredFinancialReporting(params)

        assert taskSponsoredFinancialReporting.save() != null

        params.id = taskSponsoredFinancialReporting.id

        def model = controller.edit()

        assert model.taskSponsoredFinancialReportingInstance == taskSponsoredFinancialReporting
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/taskSponsoredFinancialReporting/list'

        response.reset()


        populateValidParams(params)
        def taskSponsoredFinancialReporting = new TaskSponsoredFinancialReporting(params)

        assert taskSponsoredFinancialReporting.save() != null

        // test invalid parameters in update
        params.id = taskSponsoredFinancialReporting.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/taskSponsoredFinancialReporting/edit"
        assert model.taskSponsoredFinancialReportingInstance != null

        taskSponsoredFinancialReporting.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/taskSponsoredFinancialReporting/show/$taskSponsoredFinancialReporting.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        taskSponsoredFinancialReporting.clearErrors()

        populateValidParams(params)
        params.id = taskSponsoredFinancialReporting.id
        params.version = -1
        controller.update()

        assert view == "/taskSponsoredFinancialReporting/edit"
        assert model.taskSponsoredFinancialReportingInstance != null
        assert model.taskSponsoredFinancialReportingInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/taskSponsoredFinancialReporting/list'

        response.reset()

        populateValidParams(params)
        def taskSponsoredFinancialReporting = new TaskSponsoredFinancialReporting(params)

        assert taskSponsoredFinancialReporting.save() != null
        assert TaskSponsoredFinancialReporting.count() == 1

        params.id = taskSponsoredFinancialReporting.id

        controller.delete()

        assert TaskSponsoredFinancialReporting.count() == 0
        assert TaskSponsoredFinancialReporting.get(taskSponsoredFinancialReporting.id) == null
        assert response.redirectedUrl == '/taskSponsoredFinancialReporting/list'
    }
}
