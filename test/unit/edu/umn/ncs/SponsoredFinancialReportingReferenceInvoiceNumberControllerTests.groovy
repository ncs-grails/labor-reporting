package edu.umn.ncs



import org.junit.*
import grails.test.mixin.*

@TestFor(SponsoredFinancialReportingReferenceInvoiceNumberController)
@Mock(SponsoredFinancialReportingReferenceInvoiceNumber)
class SponsoredFinancialReportingReferenceInvoiceNumberControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/sponsoredFinancialReportingReferenceInvoiceNumber/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.sponsoredFinancialReportingReferenceInvoiceNumberInstanceList.size() == 0
        assert model.sponsoredFinancialReportingReferenceInvoiceNumberInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.sponsoredFinancialReportingReferenceInvoiceNumberInstance != null
    }

    void testSave() {
        controller.save()

        assert model.sponsoredFinancialReportingReferenceInvoiceNumberInstance != null
        assert view == '/sponsoredFinancialReportingReferenceInvoiceNumber/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/sponsoredFinancialReportingReferenceInvoiceNumber/show/1'
        assert controller.flash.message != null
        assert SponsoredFinancialReportingReferenceInvoiceNumber.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/sponsoredFinancialReportingReferenceInvoiceNumber/list'


        populateValidParams(params)
        def sponsoredFinancialReportingReferenceInvoiceNumber = new SponsoredFinancialReportingReferenceInvoiceNumber(params)

        assert sponsoredFinancialReportingReferenceInvoiceNumber.save() != null

        params.id = sponsoredFinancialReportingReferenceInvoiceNumber.id

        def model = controller.show()

        assert model.sponsoredFinancialReportingReferenceInvoiceNumberInstance == sponsoredFinancialReportingReferenceInvoiceNumber
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/sponsoredFinancialReportingReferenceInvoiceNumber/list'


        populateValidParams(params)
        def sponsoredFinancialReportingReferenceInvoiceNumber = new SponsoredFinancialReportingReferenceInvoiceNumber(params)

        assert sponsoredFinancialReportingReferenceInvoiceNumber.save() != null

        params.id = sponsoredFinancialReportingReferenceInvoiceNumber.id

        def model = controller.edit()

        assert model.sponsoredFinancialReportingReferenceInvoiceNumberInstance == sponsoredFinancialReportingReferenceInvoiceNumber
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/sponsoredFinancialReportingReferenceInvoiceNumber/list'

        response.reset()


        populateValidParams(params)
        def sponsoredFinancialReportingReferenceInvoiceNumber = new SponsoredFinancialReportingReferenceInvoiceNumber(params)

        assert sponsoredFinancialReportingReferenceInvoiceNumber.save() != null

        // test invalid parameters in update
        params.id = sponsoredFinancialReportingReferenceInvoiceNumber.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/sponsoredFinancialReportingReferenceInvoiceNumber/edit"
        assert model.sponsoredFinancialReportingReferenceInvoiceNumberInstance != null

        sponsoredFinancialReportingReferenceInvoiceNumber.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/sponsoredFinancialReportingReferenceInvoiceNumber/show/$sponsoredFinancialReportingReferenceInvoiceNumber.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        sponsoredFinancialReportingReferenceInvoiceNumber.clearErrors()

        populateValidParams(params)
        params.id = sponsoredFinancialReportingReferenceInvoiceNumber.id
        params.version = -1
        controller.update()

        assert view == "/sponsoredFinancialReportingReferenceInvoiceNumber/edit"
        assert model.sponsoredFinancialReportingReferenceInvoiceNumberInstance != null
        assert model.sponsoredFinancialReportingReferenceInvoiceNumberInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/sponsoredFinancialReportingReferenceInvoiceNumber/list'

        response.reset()

        populateValidParams(params)
        def sponsoredFinancialReportingReferenceInvoiceNumber = new SponsoredFinancialReportingReferenceInvoiceNumber(params)

        assert sponsoredFinancialReportingReferenceInvoiceNumber.save() != null
        assert SponsoredFinancialReportingReferenceInvoiceNumber.count() == 1

        params.id = sponsoredFinancialReportingReferenceInvoiceNumber.id

        controller.delete()

        assert SponsoredFinancialReportingReferenceInvoiceNumber.count() == 0
        assert SponsoredFinancialReportingReferenceInvoiceNumber.get(sponsoredFinancialReportingReferenceInvoiceNumber.id) == null
        assert response.redirectedUrl == '/sponsoredFinancialReportingReferenceInvoiceNumber/list'
    }
}
