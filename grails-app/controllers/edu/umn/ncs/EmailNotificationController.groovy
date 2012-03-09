package edu.umn.ncs

import org.springframework.dao.DataIntegrityViolationException

class EmailNotificationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [emailNotificationInstanceList: EmailNotification.list(params), emailNotificationInstanceTotal: EmailNotification.count()]
    }

    def create() {
        [emailNotificationInstance: new EmailNotification(params)]
    }

    def save() {
        def emailNotificationInstance = new EmailNotification(params)
        if (!emailNotificationInstance.save(flush: true)) {
            render(view: "create", model: [emailNotificationInstance: emailNotificationInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'emailNotification.label', default: 'EmailNotification'), emailNotificationInstance.id])
        redirect(action: "show", id: emailNotificationInstance.id)
    }

    def show() {
        def emailNotificationInstance = EmailNotification.get(params.id)
        if (!emailNotificationInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'emailNotification.label', default: 'EmailNotification'), params.id])
            redirect(action: "list")
            return
        }

        [emailNotificationInstance: emailNotificationInstance]
    }

    def edit() {
        def emailNotificationInstance = EmailNotification.get(params.id)
        if (!emailNotificationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'emailNotification.label', default: 'EmailNotification'), params.id])
            redirect(action: "list")
            return
        }

        [emailNotificationInstance: emailNotificationInstance]
    }

    def update() {
        def emailNotificationInstance = EmailNotification.get(params.id)
        if (!emailNotificationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'emailNotification.label', default: 'EmailNotification'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (emailNotificationInstance.version > version) {
                emailNotificationInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'emailNotification.label', default: 'EmailNotification')] as Object[],
                          "Another user has updated this EmailNotification while you were editing")
                render(view: "edit", model: [emailNotificationInstance: emailNotificationInstance])
                return
            }
        }

        emailNotificationInstance.properties = params

        if (!emailNotificationInstance.save(flush: true)) {
            render(view: "edit", model: [emailNotificationInstance: emailNotificationInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'emailNotification.label', default: 'EmailNotification'), emailNotificationInstance.id])
        redirect(action: "show", id: emailNotificationInstance.id)
    }

    def delete() {
        def emailNotificationInstance = EmailNotification.get(params.id)
        if (!emailNotificationInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'emailNotification.label', default: 'EmailNotification'), params.id])
            redirect(action: "list")
            return
        }

        try {
            emailNotificationInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'emailNotification.label', default: 'EmailNotification'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'emailNotification.label', default: 'EmailNotification'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
