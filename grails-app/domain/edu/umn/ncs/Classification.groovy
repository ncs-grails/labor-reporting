package edu.umn.ncs
import org.codehaus.groovy.grails.plugins.orm.auditable.AuditLogEvent

class Classification {

    static auditable = true
	
	String name
	Boolean active = false
	Date dateCreated = new Date()
	String userCreated
	String appCreated = 'labor-reporting'

	String toString() { name }	

    static constraints = {
		name(blank:false, maxSize:96)
		active
		dateCreated
		userCreated(blank:false)
		appCreated(blank:false)
    }


}
