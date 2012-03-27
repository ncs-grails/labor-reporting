package edu.umn.ncs.labor

import org.springframework.dao.DataIntegrityViolationException

class TaskTypeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [taskTypeInstanceList: TaskType.list(params), taskTypeInstanceTotal: TaskType.count()]
    }

    def create() {
        [taskTypeInstance: new TaskType(params)]
    }

    def save() {
        def taskTypeInstance = new TaskType(params)
        if (!taskTypeInstance.save(flush: true)) {
            render(view: "create", model: [taskTypeInstance: taskTypeInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'taskType.label', default: 'TaskType'), taskTypeInstance.id])
        redirect(action: "show", id: taskTypeInstance.id)
    }

    def show() {
        def taskTypeInstance = TaskType.get(params.id)
        if (!taskTypeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'taskType.label', default: 'TaskType'), params.id])
            redirect(action: "list")
            return
        }

        [taskTypeInstance: taskTypeInstance]
    }

    def edit() {
        def taskTypeInstance = TaskType.get(params.id)
        if (!taskTypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'taskType.label', default: 'TaskType'), params.id])
            redirect(action: "list")
            return
        }

        [taskTypeInstance: taskTypeInstance]
    }

    def update() {
        def taskTypeInstance = TaskType.get(params.id)
        if (!taskTypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'taskType.label', default: 'TaskType'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (taskTypeInstance.version > version) {
                taskTypeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'taskType.label', default: 'TaskType')] as Object[],
                          "Another user has updated this TaskType while you were editing")
                render(view: "edit", model: [taskTypeInstance: taskTypeInstance])
                return
            }
        }

        taskTypeInstance.properties = params

        if (!taskTypeInstance.save(flush: true)) {
            render(view: "edit", model: [taskTypeInstance: taskTypeInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'taskType.label', default: 'TaskType'), taskTypeInstance.id])
        redirect(action: "show", id: taskTypeInstance.id)
    }

    def delete() {
        def taskTypeInstance = TaskType.get(params.id)
        if (!taskTypeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'taskType.label', default: 'TaskType'), params.id])
            redirect(action: "list")
            return
        }

        try {
            taskTypeInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'taskType.label', default: 'TaskType'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'taskType.label', default: 'TaskType'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
