package edu.umn.ncs

import org.springframework.dao.DataIntegrityViolationException

class ReportingStaffController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [reportingStaffInstanceList: ReportingStaff.list(params), reportingStaffInstanceTotal: ReportingStaff.count()]
    }

    def create() {
        [reportingStaffInstance: new ReportingStaff(params)]
    }

    def save() {
        def reportingStaffInstance = new ReportingStaff(params)
        if (!reportingStaffInstance.save(flush: true)) {
            render(view: "create", model: [reportingStaffInstance: reportingStaffInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'reportingStaff.label', default: 'ReportingStaff'), reportingStaffInstance.id])
        redirect(action: "show", id: reportingStaffInstance.id)
    }

    def show() {
        def reportingStaffInstance = ReportingStaff.get(params.id)
        if (!reportingStaffInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'reportingStaff.label', default: 'ReportingStaff'), params.id])
            redirect(action: "list")
            return
        }

        [reportingStaffInstance: reportingStaffInstance]
    }

    def edit() {
        def reportingStaffInstance = ReportingStaff.get(params.id)
        if (!reportingStaffInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reportingStaff.label', default: 'ReportingStaff'), params.id])
            redirect(action: "list")
            return
        }

        [reportingStaffInstance: reportingStaffInstance]
    }

    def update() {
        def reportingStaffInstance = ReportingStaff.get(params.id)
        if (!reportingStaffInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reportingStaff.label', default: 'ReportingStaff'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (reportingStaffInstance.version > version) {
                reportingStaffInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'reportingStaff.label', default: 'ReportingStaff')] as Object[],
                          "Another user has updated this ReportingStaff while you were editing")
                render(view: "edit", model: [reportingStaffInstance: reportingStaffInstance])
                return
            }
        }

        reportingStaffInstance.properties = params

        if (!reportingStaffInstance.save(flush: true)) {
            render(view: "edit", model: [reportingStaffInstance: reportingStaffInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'reportingStaff.label', default: 'ReportingStaff'), reportingStaffInstance.id])
        redirect(action: "show", id: reportingStaffInstance.id)
    }

    def delete() {
        def reportingStaffInstance = ReportingStaff.get(params.id)
        if (!reportingStaffInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'reportingStaff.label', default: 'ReportingStaff'), params.id])
            redirect(action: "list")
            return
        }

        try {
            reportingStaffInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'reportingStaff.label', default: 'ReportingStaff'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'reportingStaff.label', default: 'ReportingStaff'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
