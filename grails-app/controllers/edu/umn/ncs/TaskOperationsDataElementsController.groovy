package edu.umn.ncs

import org.springframework.dao.DataIntegrityViolationException

class TaskOperationsDataElementsController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [taskOperationsDataElementsInstanceList: TaskOperationsDataElements.list(params), taskOperationsDataElementsInstanceTotal: TaskOperationsDataElements.count()]
    }

    def create() {
        [taskOperationsDataElementsInstance: new TaskOperationsDataElements(params)]
    }

    def save() {
        def taskOperationsDataElementsInstance = new TaskOperationsDataElements(params)
        if (!taskOperationsDataElementsInstance.save(flush: true)) {
            render(view: "create", model: [taskOperationsDataElementsInstance: taskOperationsDataElementsInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'taskOperationsDataElements.label', default: 'TaskOperationsDataElements'), taskOperationsDataElementsInstance.id])
        redirect(action: "show", id: taskOperationsDataElementsInstance.id)
    }

    def show() {
        def taskOperationsDataElementsInstance = TaskOperationsDataElements.get(params.id)
        if (!taskOperationsDataElementsInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'taskOperationsDataElements.label', default: 'TaskOperationsDataElements'), params.id])
            redirect(action: "list")
            return
        }

        [taskOperationsDataElementsInstance: taskOperationsDataElementsInstance]
    }

    def edit() {
        def taskOperationsDataElementsInstance = TaskOperationsDataElements.get(params.id)
        if (!taskOperationsDataElementsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'taskOperationsDataElements.label', default: 'TaskOperationsDataElements'), params.id])
            redirect(action: "list")
            return
        }

        [taskOperationsDataElementsInstance: taskOperationsDataElementsInstance]
    }

    def update() {
        def taskOperationsDataElementsInstance = TaskOperationsDataElements.get(params.id)
        if (!taskOperationsDataElementsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'taskOperationsDataElements.label', default: 'TaskOperationsDataElements'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (taskOperationsDataElementsInstance.version > version) {
                taskOperationsDataElementsInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'taskOperationsDataElements.label', default: 'TaskOperationsDataElements')] as Object[],
                          "Another user has updated this TaskOperationsDataElements while you were editing")
                render(view: "edit", model: [taskOperationsDataElementsInstance: taskOperationsDataElementsInstance])
                return
            }
        }

        taskOperationsDataElementsInstance.properties = params

        if (!taskOperationsDataElementsInstance.save(flush: true)) {
            render(view: "edit", model: [taskOperationsDataElementsInstance: taskOperationsDataElementsInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'taskOperationsDataElements.label', default: 'TaskOperationsDataElements'), taskOperationsDataElementsInstance.id])
        redirect(action: "show", id: taskOperationsDataElementsInstance.id)
    }

    def delete() {
        def taskOperationsDataElementsInstance = TaskOperationsDataElements.get(params.id)
        if (!taskOperationsDataElementsInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'taskOperationsDataElements.label', default: 'TaskOperationsDataElements'), params.id])
            redirect(action: "list")
            return
        }

        try {
            taskOperationsDataElementsInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'taskOperationsDataElements.label', default: 'TaskOperationsDataElements'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'taskOperationsDataElements.label', default: 'TaskOperationsDataElements'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
