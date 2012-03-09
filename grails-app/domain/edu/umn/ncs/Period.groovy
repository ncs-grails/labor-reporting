package edu.umn.ncs
import org.joda.time.*
import org.codehaus.groovy.grails.plugins.orm.auditable.AuditLogEvent

/**
This class represents the time frame staff worked on the study.
*/
class Period {

	/** Flags this domain for auditing, on all updates and changes, using the auditable plugin */
	static auditable = true

	/** Date period begins */
	Date startDate

	/** Date period ends */
	Date endData

	/** A reporting period can have many assigned efforts */
	static hasMany = [assignments: assigment]

	/** Sets default string for this domain to the period's start and end date */	
	String toString() { "${startDate}-${endDate}" }
    
	static constraints = {
		startDate()
		endDate()
	}

	/** Trigger that saves old period information to an auditLog instance, for tracking all changes to this class */
	def onDelete = { oldMap ->
		
		def now = new Date()
		
		String oldValue = "Period"
			oldValue += ", startDate: ${oldMap.startDate}"
			oldValue += ", endDate: ${oldMap.endDate}"
		//println "PRINTLN PeriodDomain.onDelete.oldValue: ${oldValue}"
			
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
