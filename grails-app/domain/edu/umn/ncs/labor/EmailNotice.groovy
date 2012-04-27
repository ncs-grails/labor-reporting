package edu.umn.ncs.labor
import org.joda.time.*
import org.codehaus.groovy.grails.plugins.orm.auditable.AuditLogEvent

class EmailNotice {

	static auditable = true

	static belongsTo = [assignment: Assignment]
	
	Date dateSent = new Date()
	String userSent

    static constraints = {
		dateSent(blank:false)
		userSent(blank:false, maxSize:60)
    }

	String toString() { "${dateSent}" }
	//String toString() { "${dateSent} (${userSent})" }

    def onDelete = { oldMap ->

        def now = new Date()

        String oldValue = "Email Notice"
            oldValue += " assignment.period.startDate ${oldMap.assignment.period.startDate}"
            oldValue += ", dateSent: ${oldMap.dateSent}"
            oldValue += ", userSent: ${oldMap.userSent} "
        	log.debug "PRINTLN NotificationEmailDomain.onDelete.oldValue: ${oldValue}"

        String className = this.class.toString().replace('class ', '')
        log.debug "${now}\tAudit:DELETE::\t${oldValue}"

        def auditLogEventInstance = new AuditLogEvent(
            className: className,
            dateCreated: now,
            eventName: 'DELETE',
            lastUpdated: now,
            oldValue: oldValue,
            persistedObjectId: this.id,
            persistedObjectVersion: this.version
        )

        if ( ! auditLogEventInstance.save() ) {
            auditLogEventInstance.errors.each{
                log.debug "${now}\tError Transacting DELETE:: \t ${it}"
            }
        }

    }

}
