package edu.umn.ncs.labor

import org.springframework.dao.DataIntegrityViolationException

class SponsoredFinancialReportingReferenceInvoiceNumberController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [sponsoredFinancialReportingReferenceInvoiceNumberInstanceList: SponsoredFinancialReportingReferenceInvoiceNumber.list(params), sponsoredFinancialReportingReferenceInvoiceNumberInstanceTotal: SponsoredFinancialReportingReferenceInvoiceNumber.count()]
    }

    def create() {
        [sponsoredFinancialReportingReferenceInvoiceNumberInstance: new SponsoredFinancialReportingReferenceInvoiceNumber(params)]
    }

    def save() {
        def sponsoredFinancialReportingReferenceInvoiceNumberInstance = new SponsoredFinancialReportingReferenceInvoiceNumber(params)
        if (!sponsoredFinancialReportingReferenceInvoiceNumberInstance.save(flush: true)) {
            render(view: "create", model: [sponsoredFinancialReportingReferenceInvoiceNumberInstance: sponsoredFinancialReportingReferenceInvoiceNumberInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'sponsoredFinancialReportingReferenceInvoiceNumber.label', default: 'SponsoredFinancialReportingReferenceInvoiceNumber'), sponsoredFinancialReportingReferenceInvoiceNumberInstance.id])
        redirect(action: "show", id: sponsoredFinancialReportingReferenceInvoiceNumberInstance.id)
    }

    def show() {
        def sponsoredFinancialReportingReferenceInvoiceNumberInstance = SponsoredFinancialReportingReferenceInvoiceNumber.get(params.id)
        if (!sponsoredFinancialReportingReferenceInvoiceNumberInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'sponsoredFinancialReportingReferenceInvoiceNumber.label', default: 'SponsoredFinancialReportingReferenceInvoiceNumber'), params.id])
            redirect(action: "list")
            return
        }

        [sponsoredFinancialReportingReferenceInvoiceNumberInstance: sponsoredFinancialReportingReferenceInvoiceNumberInstance]
    }

    def edit() {
        def sponsoredFinancialReportingReferenceInvoiceNumberInstance = SponsoredFinancialReportingReferenceInvoiceNumber.get(params.id)
        if (!sponsoredFinancialReportingReferenceInvoiceNumberInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'sponsoredFinancialReportingReferenceInvoiceNumber.label', default: 'SponsoredFinancialReportingReferenceInvoiceNumber'), params.id])
            redirect(action: "list")
            return
        }

        [sponsoredFinancialReportingReferenceInvoiceNumberInstance: sponsoredFinancialReportingReferenceInvoiceNumberInstance]
    }

    def update() {
        def sponsoredFinancialReportingReferenceInvoiceNumberInstance = SponsoredFinancialReportingReferenceInvoiceNumber.get(params.id)
        if (!sponsoredFinancialReportingReferenceInvoiceNumberInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'sponsoredFinancialReportingReferenceInvoiceNumber.label', default: 'SponsoredFinancialReportingReferenceInvoiceNumber'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (sponsoredFinancialReportingReferenceInvoiceNumberInstance.version > version) {
                sponsoredFinancialReportingReferenceInvoiceNumberInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'sponsoredFinancialReportingReferenceInvoiceNumber.label', default: 'SponsoredFinancialReportingReferenceInvoiceNumber')] as Object[],
                          "Another user has updated this SponsoredFinancialReportingReferenceInvoiceNumber while you were editing")
                render(view: "edit", model: [sponsoredFinancialReportingReferenceInvoiceNumberInstance: sponsoredFinancialReportingReferenceInvoiceNumberInstance])
                return
            }
        }

        sponsoredFinancialReportingReferenceInvoiceNumberInstance.properties = params

        if (!sponsoredFinancialReportingReferenceInvoiceNumberInstance.save(flush: true)) {
            render(view: "edit", model: [sponsoredFinancialReportingReferenceInvoiceNumberInstance: sponsoredFinancialReportingReferenceInvoiceNumberInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'sponsoredFinancialReportingReferenceInvoiceNumber.label', default: 'SponsoredFinancialReportingReferenceInvoiceNumber'), sponsoredFinancialReportingReferenceInvoiceNumberInstance.id])
        redirect(action: "show", id: sponsoredFinancialReportingReferenceInvoiceNumberInstance.id)
    }

    def delete() {
        def sponsoredFinancialReportingReferenceInvoiceNumberInstance = SponsoredFinancialReportingReferenceInvoiceNumber.get(params.id)
        if (!sponsoredFinancialReportingReferenceInvoiceNumberInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'sponsoredFinancialReportingReferenceInvoiceNumber.label', default: 'SponsoredFinancialReportingReferenceInvoiceNumber'), params.id])
            redirect(action: "list")
            return
        }

        try {
            sponsoredFinancialReportingReferenceInvoiceNumberInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'sponsoredFinancialReportingReferenceInvoiceNumber.label', default: 'SponsoredFinancialReportingReferenceInvoiceNumber'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'sponsoredFinancialReportingReferenceInvoiceNumber.label', default: 'SponsoredFinancialReportingReferenceInvoiceNumber'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
