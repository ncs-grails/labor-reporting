package edu.umn.ncs



import org.junit.*
import grails.test.mixin.*

@TestFor(PeriodController)
@Mock(Period)
class PeriodControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/period/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.periodInstanceList.size() == 0
        assert model.periodInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.periodInstance != null
    }

    void testSave() {
        controller.save()

        assert model.periodInstance != null
        assert view == '/period/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/period/show/1'
        assert controller.flash.message != null
        assert Period.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/period/list'


        populateValidParams(params)
        def period = new Period(params)

        assert period.save() != null

        params.id = period.id

        def model = controller.show()

        assert model.periodInstance == period
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/period/list'


        populateValidParams(params)
        def period = new Period(params)

        assert period.save() != null

        params.id = period.id

        def model = controller.edit()

        assert model.periodInstance == period
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/period/list'

        response.reset()


        populateValidParams(params)
        def period = new Period(params)

        assert period.save() != null

        // test invalid parameters in update
        params.id = period.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/period/edit"
        assert model.periodInstance != null

        period.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/period/show/$period.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        period.clearErrors()

        populateValidParams(params)
        params.id = period.id
        params.version = -1
        controller.update()

        assert view == "/period/edit"
        assert model.periodInstance != null
        assert model.periodInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/period/list'

        response.reset()

        populateValidParams(params)
        def period = new Period(params)

        assert period.save() != null
        assert Period.count() == 1

        params.id = period.id

        controller.delete()

        assert Period.count() == 0
        assert Period.get(period.id) == null
        assert response.redirectedUrl == '/period/list'
    }
}
