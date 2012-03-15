package edu.umn.ncs
import org.codehaus.groovy.grails.plugins.orm.auditable.AuditLogEvent

class Title {

	static auditable = true

	String name
	Boolean active = false
	Date dateCreated = new Date()
	String userCreated
	String appCreated = 'labor-reporting'

	String toString() { name }

    static constraints = {

		name(blank:false, maxSize:128)
		active()
		dateCreated()
		userCreated(blank:false)
		appCreated(blank:false)
    }

}
