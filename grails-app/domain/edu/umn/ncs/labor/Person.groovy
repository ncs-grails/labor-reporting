package edu.umn.ncs.labor
import org.codehaus.groovy.grails.plugins.orm.auditable.AuditLogEvent

class Person {

    static auditable = true

	String lastName
	String firstName
	String middleInitial
	String fullName
	String username
	Title title
	String email
	Boolean testAccount = false
	Boolean reportsEffort = false
	Date dateCreated = new Date()
	String userCreated 
	String appCreated = 'labor-reporting'

	static hasMany = [assignment: Assignment]
	static transients = ['fullNameLFM', 'fullNameFML']

    static constraints = {
		lastName(blank:false, maxSize:60)
		firstName(blank:false, maxSize:60)
		middleInitial(blank:true, maxSize:1)
		fullName(blank:true, maxSize:121)
		username(blank:false, unique:true)
		title()
		email(blank:false, email:true)
		testAccount()
		reportsEffort()
		dateCreated()
		userCreated(blank:false, maxSize:60) 
		appCreated(blank:false, maxSize:60)
    }

	String toString() { fullNameLFM }

	String getFullNameLFM() {
		"${lastName}, ${firstName} ${middleInitial ?: ''}".trim()
	}

	String getFullNameFML() {
		"${firstName} ${middleInitial ?: ''} ${lastName}".trim()
	}

    def onDelete = { oldMap ->

        def now = new Date()

        String oldValue = "Person"
            oldValue += ", lastName: ${oldMap.lastName}"
            oldValue += ", firstName: ${oldMap.firstName}"
            oldValue += ", middleInitial: ${oldMap.middleInitial}"
            oldValue += ", username: ${oldMap.username}"
            oldValue += ", title: ${oldMap.title.name}"
            oldValue += ", email: ${oldMap.email}"
            oldValue += ", testAccount: ${oldMap.testAccount}"
            oldValue += ", reportsEffort: ${oldMap.reportsEffort}"
            oldValue += ", dateCreated: ${oldMap.dateCreated}"
            oldValue += ", userCreated: ${oldMap.userCreated}"
            oldValue += ", appCreated: ${oldMap.appCreated} "

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
                log.debug "${now}\tError Transacting DELETE:: \t ${it}"
            }
        }

    }

}
