package edu.umn.ncs.labor

import org.springframework.dao.DataIntegrityViolationException

class AssignmentController {

	static scaffold = true

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def debug = true

    def index() {
		
		log.debug "params: ${params}"

        redirect(action: "list", params: params)
    
	}
/*
    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [assignmentInstanceList: Assignment.list(params), assignmentInstanceTotal: Assignment.count()]
    }

    def create() {
        [assignmentInstance: new Assignment(params)]
    }

    def save() {
        def assignmentInstance = new Assignment(params)
        if (!assignmentInstance.save(flush: true)) {
            render(view: "create", model: [assignmentInstance: assignmentInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'assignment.label', default: 'Assignment'), assignmentInstance.id])
        redirect(action: "show", id: assignmentInstance.id)
    }
*/
    def show() {

		log.debug "params: ${params}"
	

/*
        def assignmentInstance = Assignment.get(params.id)
        if (!assignmentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'assignment.label', default: 'Assignment'), params.id])
            redirect(action: "list")
            return
        }
        [assignmentInstance: assignmentInstance]
*/

    }

/*
    def edit() {
        def assignmentInstance = Assignment.get(params.id)
        if (!assignmentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'assignment.label', default: 'Assignment'), params.id])
            redirect(action: "list")
            return
        }

        [assignmentInstance: assignmentInstance]
    }

    def update() {
        def assignmentInstance = Assignment.get(params.id)
        if (!assignmentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'assignment.label', default: 'Assignment'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (assignmentInstance.version > version) {
                assignmentInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'assignment.label', default: 'Assignment')] as Object[],
                          "Another user has updated this Assignment while you were editing")
                render(view: "edit", model: [assignmentInstance: assignmentInstance])
                return
            }
        }

        assignmentInstance.properties = params

        if (!assignmentInstance.save(flush: true)) {
            render(view: "edit", model: [assignmentInstance: assignmentInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'assignment.label', default: 'Assignment'), assignmentInstance.id])
        redirect(action: "show", id: assignmentInstance.id)
    }

    def delete() {
        def assignmentInstance = Assignment.get(params.id)
        if (!assignmentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'assignment.label', default: 'Assignment'), params.id])
            redirect(action: "list")
            return
        }

        try {
            assignmentInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'assignment.label', default: 'Assignment'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'assignment.label', default: 'Assignment'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
*/

}
