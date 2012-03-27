package edu.umn.ncs.labor

import org.springframework.dao.DataIntegrityViolationException

class SubmissionController {

	static scaffold = true

   static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [submissionInstanceList: Submission.list(params), submissionInstanceTotal: Submission.count()]
    }

    def create() {
        [submissionInstance: new Submission(params)]
    }

    def save() {
        def submissionInstance = new Submission(params)
        if (!submissionInstance.save(flush: true)) {
            render(view: "create", model: [submissionInstance: submissionInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'submission.label', default: 'Submission'), submissionInstance.id])
        redirect(action: "show", id: submissionInstance.id)
    }

    def show() {
        def submissionInstance = Submission.get(params.id)
        if (!submissionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'submission.label', default: 'Submission'), params.id])
            redirect(action: "list")
            return
        }

        [submissionInstance: submissionInstance]
    }

    def edit() {
        def submissionInstance = Submission.get(params.id)
        if (!submissionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'submission.label', default: 'Submission'), params.id])
            redirect(action: "list")
            return
        }

        [submissionInstance: submissionInstance]
    }

    def update() {
        def submissionInstance = Submission.get(params.id)
        if (!submissionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'submission.label', default: 'Submission'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (submissionInstance.version > version) {
                submissionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'submission.label', default: 'Submission')] as Object[],
                          "Another user has updated this Submission while you were editing")
                render(view: "edit", model: [submissionInstance: submissionInstance])
                return
            }
        }

        submissionInstance.properties = params

        if (!submissionInstance.save(flush: true)) {
            render(view: "edit", model: [submissionInstance: submissionInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'submission.label', default: 'Submission'), submissionInstance.id])
        redirect(action: "show", id: submissionInstance.id)
    }

    def delete() {
        def submissionInstance = Submission.get(params.id)
        if (!submissionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'submission.label', default: 'Submission'), params.id])
            redirect(action: "list")
            return
        }

        try {
            submissionInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'submission.label', default: 'Submission'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'submission.label', default: 'Submission'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
