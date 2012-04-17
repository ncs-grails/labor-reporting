package edu.umn.ncs.labor
import org.springframework.dao.DataIntegrityViolationException

class TitleController {

	static scaffold = true

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {

		log.debug "params: ${params}"

		redirect(action: "list", params: params)

	}

	def list() {

		log.debug "params: ${params}"

		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		log.debug "params.max: ${params.max}"

		def titleInstanceList = Title.findAllWhere(active: true)
		log.debug "titleInstanceList = ${titleInstanceList}"

		def titleInstanceTotal = Title.countByActive(true)
		log.debug "titleInstanceTotal = ${titleInstanceTotal}"

		[ titleInstanceList: titleInstanceList, titleInstanceTotal: titleInstanceTotal ]

	}

/*	
	def create() {

		log.debug "params: ${params}"

		[titleInstance: new Title(params)]

	}

	def show() {

		def titleInstance = Title.get(params.id)
		if (!titleInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'title.label', default: 'Title'), params.id])
			redirect(action: "list")
			return
		}

		[titleInstance: titleInstance]

	}


	def save() {
		def titleInstance = new Title(params)
		if (!titleInstance.save(flush: true)) {
			render(view: "create", model: [titleInstance: titleInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'title.label', default: 'Title'), titleInstance.id])
		redirect(action: "show", id: titleInstance.id)
	}

	def edit() {
		def titleInstance = Title.get(params.id)
		if (!titleInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'title.label', default: 'Title'), params.id])
			redirect(action: "list")
			return
		}

		[titleInstance: titleInstance]
	}

	def update() {
		def titleInstance = Title.get(params.id)
		if (!titleInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'title.label', default: 'Title'), params.id])
			redirect(action: "list")
			return
		}

		if (params.version) {
			def version = params.version.toLong()
			if (titleInstance.version > version) {
				titleInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
				[message(code: 'title.label', default: 'Title')] as Object[],
				"Another user has updated this Title while you were editing")
				render(view: "edit", model: [titleInstance: titleInstance])
				return
			}
		}

		titleInstance.properties = params

		if (!titleInstance.save(flush: true)) {
			render(view: "edit", model: [titleInstance: titleInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'title.label', default: 'Title'), titleInstance.id])
		redirect(action: "show", id: titleInstance.id)
	}

	def delete() {
		def titleInstance = Title.get(params.id)
		if (!titleInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'title.label', default: 'Title'), params.id])
			redirect(action: "list")
			return
		}

		try {
			titleInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'title.label', default: 'Title'), params.id])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'title.label', default: 'Title'), params.id])
			redirect(action: "show", id: params.id)
		}
	}

*/

}
