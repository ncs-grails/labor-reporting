package edu.umn.ncs

import org.springframework.dao.DataIntegrityViolationException

class ClassificationController {

	static scaffold = true

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [classificationInstanceList: Classification.list(params), classificationInstanceTotal: Classification.count()]
    }

    def create() {
        [classificationInstance: new Classification(params)]
    }

    def save() {
        def classificationInstance = new Classification(params)
        if (!classificationInstance.save(flush: true)) {
            render(view: "create", model: [classificationInstance: classificationInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'classification.label', default: 'Classification'), classificationInstance.id])
        redirect(action: "show", id: classificationInstance.id)
    }

    def show() {
        def classificationInstance = Classification.get(params.id)
        if (!classificationInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'classification.label', default: 'Classification'), params.id])
            redirect(action: "list")
            return
        }

        [classificationInstance: classificationInstance]
    }

    def edit() {
        def classificationInstance = Classification.get(params.id)
        if (!classificationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'classification.label', default: 'Classification'), params.id])
            redirect(action: "list")
            return
        }

        [classificationInstance: classificationInstance]
    }

    def update() {
        def classificationInstance = Classification.get(params.id)
        if (!classificationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'classification.label', default: 'Classification'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (classificationInstance.version > version) {
                classificationInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'classification.label', default: 'Classification')] as Object[],
                          "Another user has updated this Classification while you were editing")
                render(view: "edit", model: [classificationInstance: classificationInstance])
                return
            }
        }

        classificationInstance.properties = params

        if (!classificationInstance.save(flush: true)) {
            render(view: "edit", model: [classificationInstance: classificationInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'classification.label', default: 'Classification'), classificationInstance.id])
        redirect(action: "show", id: classificationInstance.id)
    }

    def delete() {
        def classificationInstance = Classification.get(params.id)
        if (!classificationInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'classification.label', default: 'Classification'), params.id])
            redirect(action: "list")
            return
        }

        try {
            classificationInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'classification.label', default: 'Classification'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'classification.label', default: 'Classification'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
