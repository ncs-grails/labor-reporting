package edu.umn.ncs



import org.junit.*
import grails.test.mixin.*

@TestFor(EmailNoticeController)
@Mock(EmailNotice)
class EmailNoticeControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/emailNotice/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.emailNoticeInstanceList.size() == 0
        assert model.emailNoticeInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.emailNoticeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.emailNoticeInstance != null
        assert view == '/emailNotice/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/emailNotice/show/1'
        assert controller.flash.message != null
        assert EmailNotice.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/emailNotice/list'


        populateValidParams(params)
        def emailNotice = new EmailNotice(params)

        assert emailNotice.save() != null

        params.id = emailNotice.id

        def model = controller.show()

        assert model.emailNoticeInstance == emailNotice
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/emailNotice/list'


        populateValidParams(params)
        def emailNotice = new EmailNotice(params)

        assert emailNotice.save() != null

        params.id = emailNotice.id

        def model = controller.edit()

        assert model.emailNoticeInstance == emailNotice
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/emailNotice/list'

        response.reset()


        populateValidParams(params)
        def emailNotice = new EmailNotice(params)

        assert emailNotice.save() != null

        // test invalid parameters in update
        params.id = emailNotice.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/emailNotice/edit"
        assert model.emailNoticeInstance != null

        emailNotice.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/emailNotice/show/$emailNotice.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        emailNotice.clearErrors()

        populateValidParams(params)
        params.id = emailNotice.id
        params.version = -1
        controller.update()

        assert view == "/emailNotice/edit"
        assert model.emailNoticeInstance != null
        assert model.emailNoticeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/emailNotice/list'

        response.reset()

        populateValidParams(params)
        def emailNotice = new EmailNotice(params)

        assert emailNotice.save() != null
        assert EmailNotice.count() == 1

        params.id = emailNotice.id

        controller.delete()

        assert EmailNotice.count() == 0
        assert EmailNotice.get(emailNotice.id) == null
        assert response.redirectedUrl == '/emailNotice/list'
    }
}
