package edu.umn.ncs



import org.junit.*
import grails.test.mixin.*

@TestFor(ClassificationController)
@Mock(Classification)
class ClassificationControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/classification/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.classificationInstanceList.size() == 0
        assert model.classificationInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.classificationInstance != null
    }

    void testSave() {
        controller.save()

        assert model.classificationInstance != null
        assert view == '/classification/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/classification/show/1'
        assert controller.flash.message != null
        assert Classification.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/classification/list'


        populateValidParams(params)
        def classification = new Classification(params)

        assert classification.save() != null

        params.id = classification.id

        def model = controller.show()

        assert model.classificationInstance == classification
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/classification/list'


        populateValidParams(params)
        def classification = new Classification(params)

        assert classification.save() != null

        params.id = classification.id

        def model = controller.edit()

        assert model.classificationInstance == classification
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/classification/list'

        response.reset()


        populateValidParams(params)
        def classification = new Classification(params)

        assert classification.save() != null

        // test invalid parameters in update
        params.id = classification.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/classification/edit"
        assert model.classificationInstance != null

        classification.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/classification/show/$classification.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        classification.clearErrors()

        populateValidParams(params)
        params.id = classification.id
        params.version = -1
        controller.update()

        assert view == "/classification/edit"
        assert model.classificationInstance != null
        assert model.classificationInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/classification/list'

        response.reset()

        populateValidParams(params)
        def classification = new Classification(params)

        assert classification.save() != null
        assert Classification.count() == 1

        params.id = classification.id

        controller.delete()

        assert Classification.count() == 0
        assert Classification.get(classification.id) == null
        assert response.redirectedUrl == '/classification/list'
    }
}
