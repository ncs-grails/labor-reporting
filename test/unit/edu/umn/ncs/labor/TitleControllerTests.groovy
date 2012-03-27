package edu.umn.ncs



import org.junit.*
import grails.test.mixin.*

@TestFor(TitleController)
@Mock(Title)
class TitleControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/title/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.titleInstanceList.size() == 0
        assert model.titleInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.titleInstance != null
    }

    void testSave() {
        controller.save()

        assert model.titleInstance != null
        assert view == '/title/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/title/show/1'
        assert controller.flash.message != null
        assert Title.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/title/list'


        populateValidParams(params)
        def title = new Title(params)

        assert title.save() != null

        params.id = title.id

        def model = controller.show()

        assert model.titleInstance == title
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/title/list'


        populateValidParams(params)
        def title = new Title(params)

        assert title.save() != null

        params.id = title.id

        def model = controller.edit()

        assert model.titleInstance == title
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/title/list'

        response.reset()


        populateValidParams(params)
        def title = new Title(params)

        assert title.save() != null

        // test invalid parameters in update
        params.id = title.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/title/edit"
        assert model.titleInstance != null

        title.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/title/show/$title.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        title.clearErrors()

        populateValidParams(params)
        params.id = title.id
        params.version = -1
        controller.update()

        assert view == "/title/edit"
        assert model.titleInstance != null
        assert model.titleInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/title/list'

        response.reset()

        populateValidParams(params)
        def title = new Title(params)

        assert title.save() != null
        assert Title.count() == 1

        params.id = title.id

        controller.delete()

        assert Title.count() == 0
        assert Title.get(title.id) == null
        assert response.redirectedUrl == '/title/list'
    }
}
