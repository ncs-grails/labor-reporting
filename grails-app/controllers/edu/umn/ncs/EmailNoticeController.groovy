package edu.umn.ncs

import org.springframework.dao.DataIntegrityViolationException

class EmailNoticeController {

	static scaffold = true

   static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [emailNoticeInstanceList: EmailNotice.list(params), emailNoticeInstanceTotal: EmailNotice.count()]
    }

    def create() {
        [emailNoticeInstance: new EmailNotice(params)]
    }

    def save() {
        def emailNoticeInstance = new EmailNotice(params)
        if (!emailNoticeInstance.save(flush: true)) {
            render(view: "create", model: [emailNoticeInstance: emailNoticeInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'emailNotice.label', default: 'EmailNotice'), emailNoticeInstance.id])
        redirect(action: "show", id: emailNoticeInstance.id)
    }

    def show() {
        def emailNoticeInstance = EmailNotice.get(params.id)
        if (!emailNoticeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'emailNotice.label', default: 'EmailNotice'), params.id])
            redirect(action: "list")
            return
        }

        [emailNoticeInstance: emailNoticeInstance]
    }

    def edit() {
        def emailNoticeInstance = EmailNotice.get(params.id)
        if (!emailNoticeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'emailNotice.label', default: 'EmailNotice'), params.id])
            redirect(action: "list")
            return
        }

        [emailNoticeInstance: emailNoticeInstance]
    }

    def update() {
        def emailNoticeInstance = EmailNotice.get(params.id)
        if (!emailNoticeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'emailNotice.label', default: 'EmailNotice'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (emailNoticeInstance.version > version) {
                emailNoticeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'emailNotice.label', default: 'EmailNotice')] as Object[],
                          "Another user has updated this EmailNotice while you were editing")
                render(view: "edit", model: [emailNoticeInstance: emailNoticeInstance])
                return
            }
        }

        emailNoticeInstance.properties = params

        if (!emailNoticeInstance.save(flush: true)) {
            render(view: "edit", model: [emailNoticeInstance: emailNoticeInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'emailNotice.label', default: 'EmailNotice'), emailNoticeInstance.id])
        redirect(action: "show", id: emailNoticeInstance.id)
    }

    def delete() {
        def emailNoticeInstance = EmailNotice.get(params.id)
        if (!emailNoticeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'emailNotice.label', default: 'EmailNotice'), params.id])
            redirect(action: "list")
            return
        }

        try {
            emailNoticeInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'emailNotice.label', default: 'EmailNotice'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'emailNotice.label', default: 'EmailNotice'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
