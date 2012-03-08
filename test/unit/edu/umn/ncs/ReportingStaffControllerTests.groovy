package edu.umn.ncs



import org.junit.*
import grails.test.mixin.*

@TestFor(ReportingStaffController)
@Mock(ReportingStaff)
class ReportingStaffControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/reportingStaff/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.reportingStaffInstanceList.size() == 0
        assert model.reportingStaffInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.reportingStaffInstance != null
    }

    void testSave() {
        controller.save()

        assert model.reportingStaffInstance != null
        assert view == '/reportingStaff/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/reportingStaff/show/1'
        assert controller.flash.message != null
        assert ReportingStaff.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/reportingStaff/list'


        populateValidParams(params)
        def reportingStaff = new ReportingStaff(params)

        assert reportingStaff.save() != null

        params.id = reportingStaff.id

        def model = controller.show()

        assert model.reportingStaffInstance == reportingStaff
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/reportingStaff/list'


        populateValidParams(params)
        def reportingStaff = new ReportingStaff(params)

        assert reportingStaff.save() != null

        params.id = reportingStaff.id

        def model = controller.edit()

        assert model.reportingStaffInstance == reportingStaff
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/reportingStaff/list'

        response.reset()


        populateValidParams(params)
        def reportingStaff = new ReportingStaff(params)

        assert reportingStaff.save() != null

        // test invalid parameters in update
        params.id = reportingStaff.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/reportingStaff/edit"
        assert model.reportingStaffInstance != null

        reportingStaff.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/reportingStaff/show/$reportingStaff.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        reportingStaff.clearErrors()

        populateValidParams(params)
        params.id = reportingStaff.id
        params.version = -1
        controller.update()

        assert view == "/reportingStaff/edit"
        assert model.reportingStaffInstance != null
        assert model.reportingStaffInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/reportingStaff/list'

        response.reset()

        populateValidParams(params)
        def reportingStaff = new ReportingStaff(params)

        assert reportingStaff.save() != null
        assert ReportingStaff.count() == 1

        params.id = reportingStaff.id

        controller.delete()

        assert ReportingStaff.count() == 0
        assert ReportingStaff.get(reportingStaff.id) == null
        assert response.redirectedUrl == '/reportingStaff/list'
    }
}
