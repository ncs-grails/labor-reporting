package edu.umn.ncs
import org.joda.time.*
import org.codehaus.groovy.grails.plugins.orm.auditable.AuditLogEvent

/** This class represents emails sent out to staff */
class EmailNotification {

	/** Flags this domain for auditing, on all updates and changes, using the auditable plugin. */
	static auditable = true

	/** Date and time email was sent out. */
	Date dateSent = new Date()

	/** User account of person who sent out the email.  */
	String userSent

	/** Variable "assignment" pertains to the Assigned Effort related to the email. */
	static belongsTo = [assignment: assignment]

	/** Sets default string, for this domain, to the date email was sent out. */	
	String toString() { "${dateSent}" }

	/** Non-default constraints for this class 
	<dl>
		<dt>userSent</dt>
			<dd>cannot be blank</dd>
	</dl>	
	*/	
	static constraints = {
		assignment()
		dateSent()
		userSent(blank:false)
	}

	/** Trigger that saves old activity information to an auditLog instance, for tracking all changes to this class. */
	def onDelete = { oldMap ->

		def now = new Date()

		String oldValue = "Email associated with"
			oldValue += " assignment.id ${oldMap.assignment.id}"
			oldValue += ", dateSent: ${oldMap.dateSent}"
			oldValue += ", userSent: ${oldMap.userSent} "
		//println "PRINTLN EmailNotificationDomain.onDelete.oldValue: ${oldValue}"

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
