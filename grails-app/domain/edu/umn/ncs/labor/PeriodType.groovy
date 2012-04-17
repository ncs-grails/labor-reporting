package edu.umn.ncs.labor
import org.codehaus.groovy.grails.plugins.orm.auditable.AuditLogEvent

class PeriodType {

	static auditable = true

	String name

	static constraints = {
		name(blank:false, maxSize:50)
	}

	def onDelete = { oldMap ->

		def now = new Date()

		oldValue = "PeriodType"
		oldValue += ", name: ${oldMap.name}"

		className = this.class.toString().replace('class ', '')

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
