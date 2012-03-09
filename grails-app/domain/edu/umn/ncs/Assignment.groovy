package edu.umn.ncs
import org.codehaus.groovy.grails.plugins.orm.auditable.AuditLogEvent

/**
This class represents the total amount of work effort assigned to staff, for a given period.
*/
class Assignment {

	/** Flags this domain for auditing, on all updates and changes, using the auditable plugin */
	static auditable = true

	BigDecimal effort
	Person assignBy
	Date assignDate = new Date()
	Person certifyBy
	Date certifyDate
	Title title
	String appCreate = 'labor'       

	static belongsTo = [person: Person, period: Period]
	static hasMany = [emails: EmailNotification, submittedEffort: Submission]

	static constraints = {
		effort(max:100, scale:2)
		assignBy(blank:false)
		assignDate()
		certifyBy(nullable:true)
		certifyDate(nullable:true)
		title()
		appCreate(blank:false)
	}

	/** Trigger that saves old activity information to an auditLog instance, for tracking all changes to this class */
	def onDelete = { oldMap ->

		def now = new Date()

		String oldValue = "Assignmentt associated with"
			oldValue += " person.id ${oldMap.reportingStaff.id}"
			oldValue += " & period.id ${oldMap.period.id}"
			oldValue += ", title.id: ${oldMap.laborCategory.id}"
			oldValue += ", effort: ${oldMap.assignedEffort}"
			oldValue += ", assignDate: ${oldMap.dateAssigned}"
			oldValue += ", assignBy.id: ${oldMap.assigningStaff.id}"
			oldValue += ", appCreated: ${oldMap.appCreated}"
			oldValue += ", certifyDate: ${oldMap.dateCommitted}"
			oldValue += ", certifyBy.id: "
			oldValue += "${oldMap.commitingStaff}" ? "${null} " : "${oldMap.commitingStaff.id} "
			//oldValue += ", commitingStaff.id: ${oldMap.commitingStaff.id} "

		String className = this.class.toString().replace('class ', '')

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
