package edu.umn.ncs
import org.codehaus.groovy.grails.plugins.orm.auditable.AuditLogEvent

class Assignment {

	static auditable = true

	BigDecimal effort
	Person assignBy
	Date assignDate = new Date()
	Title title
	Person certifyBy
	Date certifyDate
	String appCreate = 'labor'       

	static belongsTo = [assignTo: Person, period: Period]
	static hasMany = [emails: EmailNotification, submittedEffort: Submission]

	static constraints = {
		period()
		effort(max:100, scale:2)
		assignBy(blank:false)
		assignDate()
		completeBy(blank:false)
		title()
		certifyBy(blank:true)
		certifyDate(nullable:true)
		appCreate(blank:false)
	}

	def onDelete = { oldMap ->

		def now = new Date()

		String oldValue = "Assignment associated with"
			oldValue += " & period.id ${oldMap.period.id}"
			oldValue += " effort: ${oldMap.effort}"
			oldValue += ", assignBy.id: ${oldMap.assignBy.id}"
			oldValue += ", assignDate: ${oldMap.assignDate}"
			oldValue += ", completeBy: ${oldMap.completeBy}"
			oldValue += ", title.id: ${oldMap.title.id}"
			oldValue += ", certifyBy.id: ${oldMap.certifyBy}"
			oldValue += ", certifyDate: ${oldMap.certifyDate}"
			oldValue += ", appCreated: ${oldMap.appCreated}"

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
