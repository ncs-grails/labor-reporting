package edu.umn.ncs



import org.junit.*
import grails.test.mixin.*

@TestFor(TaskElectronicTechnicalDirectLaborReportController)
@Mock(TaskElectronicTechnicalDirectLaborReport)
class TaskElectronicTechnicalDirectLaborReportControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/taskElectronicTechnicalDirectLaborReport/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.taskElectronicTechnicalDirectLaborReportInstanceList.size() == 0
        assert model.taskElectronicTechnicalDirectLaborReportInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.taskElectronicTechnicalDirectLaborReportInstance != null
    }

    void testSave() {
        controller.save()

        assert model.taskElectronicTechnicalDirectLaborReportInstance != null
        assert view == '/taskElectronicTechnicalDirectLaborReport/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/taskElectronicTechnicalDirectLaborReport/show/1'
        assert controller.flash.message != null
        assert TaskElectronicTechnicalDirectLaborReport.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/taskElectronicTechnicalDirectLaborReport/list'


        populateValidParams(params)
        def taskElectronicTechnicalDirectLaborReport = new TaskElectronicTechnicalDirectLaborReport(params)

        assert taskElectronicTechnicalDirectLaborReport.save() != null

        params.id = taskElectronicTechnicalDirectLaborReport.id

        def model = controller.show()

        assert model.taskElectronicTechnicalDirectLaborReportInstance == taskElectronicTechnicalDirectLaborReport
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/taskElectronicTechnicalDirectLaborReport/list'


        populateValidParams(params)
        def taskElectronicTechnicalDirectLaborReport = new TaskElectronicTechnicalDirectLaborReport(params)

        assert taskElectronicTechnicalDirectLaborReport.save() != null

        params.id = taskElectronicTechnicalDirectLaborReport.id

        def model = controller.edit()

        assert model.taskElectronicTechnicalDirectLaborReportInstance == taskElectronicTechnicalDirectLaborReport
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/taskElectronicTechnicalDirectLaborReport/list'

        response.reset()


        populateValidParams(params)
        def taskElectronicTechnicalDirectLaborReport = new TaskElectronicTechnicalDirectLaborReport(params)

        assert taskElectronicTechnicalDirectLaborReport.save() != null

        // test invalid parameters in update
        params.id = taskElectronicTechnicalDirectLaborReport.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/taskElectronicTechnicalDirectLaborReport/edit"
        assert model.taskElectronicTechnicalDirectLaborReportInstance != null

        taskElectronicTechnicalDirectLaborReport.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/taskElectronicTechnicalDirectLaborReport/show/$taskElectronicTechnicalDirectLaborReport.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        taskElectronicTechnicalDirectLaborReport.clearErrors()

        populateValidParams(params)
        params.id = taskElectronicTechnicalDirectLaborReport.id
        params.version = -1
        controller.update()

        assert view == "/taskElectronicTechnicalDirectLaborReport/edit"
        assert model.taskElectronicTechnicalDirectLaborReportInstance != null
        assert model.taskElectronicTechnicalDirectLaborReportInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/taskElectronicTechnicalDirectLaborReport/list'

        response.reset()

        populateValidParams(params)
        def taskElectronicTechnicalDirectLaborReport = new TaskElectronicTechnicalDirectLaborReport(params)

        assert taskElectronicTechnicalDirectLaborReport.save() != null
        assert TaskElectronicTechnicalDirectLaborReport.count() == 1

        params.id = taskElectronicTechnicalDirectLaborReport.id

        controller.delete()

        assert TaskElectronicTechnicalDirectLaborReport.count() == 0
        assert TaskElectronicTechnicalDirectLaborReport.get(taskElectronicTechnicalDirectLaborReport.id) == null
        assert response.redirectedUrl == '/taskElectronicTechnicalDirectLaborReport/list'
    }
}
