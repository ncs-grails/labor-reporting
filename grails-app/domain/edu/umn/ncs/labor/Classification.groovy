package edu.umn.ncs.labor
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
		name(blank:false, unique:true, maxSize:96)
		active()
		dateCreated()
		userCreated(blank:false, maxSize:60)
		appCreated(blank:false, maxSize:60)
    }

	static mapping = { sort "name"}

	def onDelete = { oldMap ->

		def now = new Date()

		String oldValue = "Classification"
			oldValue += ", name: ${oldMap.name}"
			oldValue += ", active: ${oldMap.active}"
			oldValue += ", dateCreated: ${oldMap.dateCreated}"
			oldValue += ", userCreated: ${oldMap.userCreated}"
			oldValue += ", appCreated: ${oldMap.appCreated} "
			//println "PRINTLN StudyActivityDomain.onDelete.oldValue: ${oldValue}"

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
				//println "${now}\tError Transacting DELETE:: \t ${it}"
			}

		}
	}
}
