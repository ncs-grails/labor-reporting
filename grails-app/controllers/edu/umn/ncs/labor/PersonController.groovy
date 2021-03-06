package edu.umn.ncs.labor

import org.springframework.dao.DataIntegrityViolationException

class PersonController {

	static scaffold = true

   static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {

		log.debug "params = ${params}"

        redirect(action: "list", params: params)

    }

    def list() {

		log.debug "paras: ${params}"

        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		log.debug "params.max = ${params.max}"

        def personInstanceList = Person.list(params)
		log.debug "personInstanceList = ${personInstanceList}"

		def personInstanceTotal = Person.count()
		log.debug "personInstanceTotal = ${personInstanceTotal}"

		[ personInstanceList: personInstanceList, personInstanceTotal: personInstanceTotal ]

    }

    def edit() {

		log.debug "params: ${params}"

        def personInstance = Person.get(params.id)
		log.debug "personInstance: ${personInstance}"

        if (!personInstance) {
		log.debug "if (!personInstance)"

            flash.message = message(code: 'default.not.found.message', args: [message(code: 'person.label', default: 'Person'), params.id])
			log.debug "  => flash.message: ${flash.message}"
            
			redirect(action: "list")
            
			return

        }

        [personInstance: personInstance]

    }

/*	
    def create() {
        [personInstance: new Person(params)]
    }

    def save() {
        def personInstance = new Person(params)
        if (!personInstance.save(flush: true)) {
            render(view: "create", model: [personInstance: personInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'person.label', default: 'Person'), personInstance.id])
        redirect(action: "show", id: personInstance.id)
    }

    def show() {
        def personInstance = Person.get(params.id)
        if (!personInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'person.label', default: 'Person'), params.id])
            redirect(action: "list")
            return
        }

        [personInstance: personInstance]
    }

    def update() {
        def personInstance = Person.get(params.id)
        if (!personInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'person.label', default: 'Person'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (personInstance.version > version) {
                personInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'person.label', default: 'Person')] as Object[],
                          "Another user has updated this Person while you were editing")
                render(view: "edit", model: [personInstance: personInstance])
                return
            }
        }

        personInstance.properties = params

        if (!personInstance.save(flush: true)) {
            render(view: "edit", model: [personInstance: personInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'person.label', default: 'Person'), personInstance.id])
        redirect(action: "show", id: personInstance.id)
    }

    def delete() {
        def personInstance = Person.get(params.id)
        if (!personInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'person.label', default: 'Person'), params.id])
            redirect(action: "list")
            return
        }

        try {
            personInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'person.label', default: 'Person'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'person.label', default: 'Person'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
*/

}
