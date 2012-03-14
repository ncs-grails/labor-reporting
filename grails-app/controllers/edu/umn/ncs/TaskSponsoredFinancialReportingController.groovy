package edu.umn.ncs

import org.springframework.dao.DataIntegrityViolationException

class TaskSponsoredFinancialReportingController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [taskSponsoredFinancialReportingInstanceList: TaskSponsoredFinancialReporting.list(params), taskSponsoredFinancialReportingInstanceTotal: TaskSponsoredFinancialReporting.count()]
    }

    def create() {
        [taskSponsoredFinancialReportingInstance: new TaskSponsoredFinancialReporting(params)]
    }

    def save() {
        def taskSponsoredFinancialReportingInstance = new TaskSponsoredFinancialReporting(params)
        if (!taskSponsoredFinancialReportingInstance.save(flush: true)) {
            render(view: "create", model: [taskSponsoredFinancialReportingInstance: taskSponsoredFinancialReportingInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'taskSponsoredFinancialReporting.label', default: 'TaskSponsoredFinancialReporting'), taskSponsoredFinancialReportingInstance.id])
        redirect(action: "show", id: taskSponsoredFinancialReportingInstance.id)
    }

    def show() {
        def taskSponsoredFinancialReportingInstance = TaskSponsoredFinancialReporting.get(params.id)
        if (!taskSponsoredFinancialReportingInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'taskSponsoredFinancialReporting.label', default: 'TaskSponsoredFinancialReporting'), params.id])
            redirect(action: "list")
            return
        }

        [taskSponsoredFinancialReportingInstance: taskSponsoredFinancialReportingInstance]
    }

    def edit() {
        def taskSponsoredFinancialReportingInstance = TaskSponsoredFinancialReporting.get(params.id)
        if (!taskSponsoredFinancialReportingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'taskSponsoredFinancialReporting.label', default: 'TaskSponsoredFinancialReporting'), params.id])
            redirect(action: "list")
            return
        }

        [taskSponsoredFinancialReportingInstance: taskSponsoredFinancialReportingInstance]
    }

    def update() {
        def taskSponsoredFinancialReportingInstance = TaskSponsoredFinancialReporting.get(params.id)
        if (!taskSponsoredFinancialReportingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'taskSponsoredFinancialReporting.label', default: 'TaskSponsoredFinancialReporting'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (taskSponsoredFinancialReportingInstance.version > version) {
                taskSponsoredFinancialReportingInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'taskSponsoredFinancialReporting.label', default: 'TaskSponsoredFinancialReporting')] as Object[],
                          "Another user has updated this TaskSponsoredFinancialReporting while you were editing")
                render(view: "edit", model: [taskSponsoredFinancialReportingInstance: taskSponsoredFinancialReportingInstance])
                return
            }
        }

        taskSponsoredFinancialReportingInstance.properties = params

        if (!taskSponsoredFinancialReportingInstance.save(flush: true)) {
            render(view: "edit", model: [taskSponsoredFinancialReportingInstance: taskSponsoredFinancialReportingInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'taskSponsoredFinancialReporting.label', default: 'TaskSponsoredFinancialReporting'), taskSponsoredFinancialReportingInstance.id])
        redirect(action: "show", id: taskSponsoredFinancialReportingInstance.id)
    }

    def delete() {
        def taskSponsoredFinancialReportingInstance = TaskSponsoredFinancialReporting.get(params.id)
        if (!taskSponsoredFinancialReportingInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'taskSponsoredFinancialReporting.label', default: 'TaskSponsoredFinancialReporting'), params.id])
            redirect(action: "list")
            return
        }

        try {
            taskSponsoredFinancialReportingInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'taskSponsoredFinancialReporting.label', default: 'TaskSponsoredFinancialReporting'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'taskSponsoredFinancialReporting.label', default: 'TaskSponsoredFinancialReporting'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
