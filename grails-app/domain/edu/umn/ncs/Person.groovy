package edu.umn.ncs
import org.codehaus.groovy.grails.plugins.orm.auditable.AuditLogEvent

class Person {

	static auditable = true

	String lastName
	String firstName
	String middleInit
	String fullName
	Title title
	String username
	String email
	Boolean isTestAccount = false
	Boolean submitsEffort = false
	Date dateCreated = new Date()
	String userCreated
	String appCreated = 'labor"

	static hasMany = [assignment: assignment]
	static transients = ['fullNameLFM', 'fullNameFML']

	String toString() {
		fullNameLFM
	}

	String getFullNameLFM() {
		"${lastName}, ${firstName} ${middleInit ?: ''}".trim()
	}

	String getFullNameFML() {
		"${firstName} ${middleInit ?: ''} ${lastName}".trim()
	}

	static constraints = {
		username(blank:false, unique:true)
		firstName(blank:false)
		middleInit(blank:true)
		lastName(blank:false)
		fullName(nullable:true)
		title()
		email(blank:false, email:true)
		isTestAccount()
		submitsEffort()
		dateCreated()
		userCreated(blank:false)
		appCreated(blank:false)
	}

	def onDelete = { oldMap ->

		def now = new Date()

		String oldValue = "Person"
			oldValue += ", username: ${oldMap.username}"
			oldValue += ", lastName: ${oldMap.lastName}"
			oldValue += ", firstName: ${oldMap.firstName}"
			oldValue += ", middleInit: ${oldMap.middleInit}"
			oldValue += ", title.id: ${oldMap.title.id}"
			oldValue += ", email: ${oldMap.email}"
			oldValue += ", isTestAccount: ${oldMap.isTestAccount}"
			oldValue += ", submitsEffort: ${oldMap.submitsEffort}"
			oldValue += ", dateCreated: ${oldMap.dateCreated}"
			oldValue += ", userCreated: ${oldMap.userCreated}"
			oldValue += ", appCreated: ${oldMap.appCreated} "
		//println "PRINTLN PersonDomain.onDelete.oldValue: ${oldValue}"

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
