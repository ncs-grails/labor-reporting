package edu.umn.ncs

import org.springframework.dao.DataIntegrityViolationException

class TaskElectronicTechnicalDirectLaborReportController {

	static scaffold = true

   static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [taskElectronicTechnicalDirectLaborReportInstanceList: TaskElectronicTechnicalDirectLaborReport.list(params), taskElectronicTechnicalDirectLaborReportInstanceTotal: TaskElectronicTechnicalDirectLaborReport.count()]
    }

    def create() {
        [taskElectronicTechnicalDirectLaborReportInstance: new TaskElectronicTechnicalDirectLaborReport(params)]
    }

    def save() {
        def taskElectronicTechnicalDirectLaborReportInstance = new TaskElectronicTechnicalDirectLaborReport(params)
        if (!taskElectronicTechnicalDirectLaborReportInstance.save(flush: true)) {
            render(view: "create", model: [taskElectronicTechnicalDirectLaborReportInstance: taskElectronicTechnicalDirectLaborReportInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'taskElectronicTechnicalDirectLaborReport.label', default: 'TaskElectronicTechnicalDirectLaborReport'), taskElectronicTechnicalDirectLaborReportInstance.id])
        redirect(action: "show", id: taskElectronicTechnicalDirectLaborReportInstance.id)
    }

    def show() {
        def taskElectronicTechnicalDirectLaborReportInstance = TaskElectronicTechnicalDirectLaborReport.get(params.id)
        if (!taskElectronicTechnicalDirectLaborReportInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'taskElectronicTechnicalDirectLaborReport.label', default: 'TaskElectronicTechnicalDirectLaborReport'), params.id])
            redirect(action: "list")
            return
        }

        [taskElectronicTechnicalDirectLaborReportInstance: taskElectronicTechnicalDirectLaborReportInstance]
    }

    def edit() {
        def taskElectronicTechnicalDirectLaborReportInstance = TaskElectronicTechnicalDirectLaborReport.get(params.id)
        if (!taskElectronicTechnicalDirectLaborReportInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'taskElectronicTechnicalDirectLaborReport.label', default: 'TaskElectronicTechnicalDirectLaborReport'), params.id])
            redirect(action: "list")
            return
        }

        [taskElectronicTechnicalDirectLaborReportInstance: taskElectronicTechnicalDirectLaborReportInstance]
    }

    def update() {
        def taskElectronicTechnicalDirectLaborReportInstance = TaskElectronicTechnicalDirectLaborReport.get(params.id)
        if (!taskElectronicTechnicalDirectLaborReportInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'taskElectronicTechnicalDirectLaborReport.label', default: 'TaskElectronicTechnicalDirectLaborReport'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (taskElectronicTechnicalDirectLaborReportInstance.version > version) {
                taskElectronicTechnicalDirectLaborReportInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'taskElectronicTechnicalDirectLaborReport.label', default: 'TaskElectronicTechnicalDirectLaborReport')] as Object[],
                          "Another user has updated this TaskElectronicTechnicalDirectLaborReport while you were editing")
                render(view: "edit", model: [taskElectronicTechnicalDirectLaborReportInstance: taskElectronicTechnicalDirectLaborReportInstance])
                return
            }
        }

        taskElectronicTechnicalDirectLaborReportInstance.properties = params

        if (!taskElectronicTechnicalDirectLaborReportInstance.save(flush: true)) {
            render(view: "edit", model: [taskElectronicTechnicalDirectLaborReportInstance: taskElectronicTechnicalDirectLaborReportInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'taskElectronicTechnicalDirectLaborReport.label', default: 'TaskElectronicTechnicalDirectLaborReport'), taskElectronicTechnicalDirectLaborReportInstance.id])
        redirect(action: "show", id: taskElectronicTechnicalDirectLaborReportInstance.id)
    }

    def delete() {
        def taskElectronicTechnicalDirectLaborReportInstance = TaskElectronicTechnicalDirectLaborReport.get(params.id)
        if (!taskElectronicTechnicalDirectLaborReportInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'taskElectronicTechnicalDirectLaborReport.label', default: 'TaskElectronicTechnicalDirectLaborReport'), params.id])
            redirect(action: "list")
            return
        }

        try {
            taskElectronicTechnicalDirectLaborReportInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'taskElectronicTechnicalDirectLaborReport.label', default: 'TaskElectronicTechnicalDirectLaborReport'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'taskElectronicTechnicalDirectLaborReport.label', default: 'TaskElectronicTechnicalDirectLaborReport'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
