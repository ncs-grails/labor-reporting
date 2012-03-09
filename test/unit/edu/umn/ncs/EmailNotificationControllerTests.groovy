package edu.umn.ncs



import org.junit.*
import grails.test.mixin.*

@TestFor(EmailNotificationController)
@Mock(EmailNotification)
class EmailNotificationControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/emailNotification/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.emailNotificationInstanceList.size() == 0
        assert model.emailNotificationInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.emailNotificationInstance != null
    }

    void testSave() {
        controller.save()

        assert model.emailNotificationInstance != null
        assert view == '/emailNotification/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/emailNotification/show/1'
        assert controller.flash.message != null
        assert EmailNotification.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/emailNotification/list'


        populateValidParams(params)
        def emailNotification = new EmailNotification(params)

        assert emailNotification.save() != null

        params.id = emailNotification.id

        def model = controller.show()

        assert model.emailNotificationInstance == emailNotification
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/emailNotification/list'


        populateValidParams(params)
        def emailNotification = new EmailNotification(params)

        assert emailNotification.save() != null

        params.id = emailNotification.id

        def model = controller.edit()

        assert model.emailNotificationInstance == emailNotification
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/emailNotification/list'

        response.reset()


        populateValidParams(params)
        def emailNotification = new EmailNotification(params)

        assert emailNotification.save() != null

        // test invalid parameters in update
        params.id = emailNotification.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/emailNotification/edit"
        assert model.emailNotificationInstance != null

        emailNotification.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/emailNotification/show/$emailNotification.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        emailNotification.clearErrors()

        populateValidParams(params)
        params.id = emailNotification.id
        params.version = -1
        controller.update()

        assert view == "/emailNotification/edit"
        assert model.emailNotificationInstance != null
        assert model.emailNotificationInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/emailNotification/list'

        response.reset()

        populateValidParams(params)
        def emailNotification = new EmailNotification(params)

        assert emailNotification.save() != null
        assert EmailNotification.count() == 1

        params.id = emailNotification.id

        controller.delete()

        assert EmailNotification.count() == 0
        assert EmailNotification.get(emailNotification.id) == null
        assert response.redirectedUrl == '/emailNotification/list'
    }
}
