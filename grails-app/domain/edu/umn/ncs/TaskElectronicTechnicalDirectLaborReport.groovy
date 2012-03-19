package edu.umn.ncs
import java.util.Date;
import org.codehaus.groovy.grails.plugins.orm.auditable.AuditLogEvent

class TaskElectronicTechnicalDirectLaborReport {

    static auditable = true

	String name
	Boolean active
	Date dateCreated = new Date()
	String userCreated
	String appCreated = 'labor-reporting'

    static constraints = {
		name(blank:false)
		active()
		dateCreated(blank:false)
		userCreated(blank:false)
		appCreated(blank:false)
    }

	String toString() { name }

	static mapping = { sort "name" }

    def onDelete = { oldMap ->

		def now = new Date()

        String oldValue = "Task for Electronic Technical Direct Labor Report (ETDLR)"
        	oldValue += ", name: ${oldMap.name}"
            oldValue += ", active: ${oldMap.active}"
            oldValue += ", dateCreated: ${oldMap.dateCreated}"
            oldValue += ", userCreated: ${oldMap.userCreated}"
            oldValue += ", appCreated: ${oldMap.appCreated}"
        //println "PRINTLN EtdlrTaskDomain.onDelete.oldValue: ${oldValue}"

        String className = this.class.toString().replace('class ', '')
        //println "${now}\tAudit:DELETE::\t${oldValue}"

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
				println "${now}\tError Transacting DELETE:: \t ${it}"
            }
        }

    }

}

