package edu.umn.ncs.labor
import org.codehaus.groovy.grails.plugins.orm.auditable.AuditLogEvent

class Task {

    static auditable = true

	TaskType type
	String name
	Boolean active
	Date dateCreated = new Date()
	String userCreated
	String appCreated = 'labor-reporting'

	static hasMany = [correspondingTasks: Task]

    static constraints = {
		name(blank:false, unique:true, maxSize:100)
		active()
		dateCreated(blank:false)
		userCreated(blank:false)
		appCreated(blank:false)
    }

	String toString() { name }

	static mapping = { sort "name" }

    def onDelete = { oldMap ->

		def now = new Date()

        String oldValue = "Task"
        	oldValue += ", name: ${oldMap.name}"
        	oldValue += ", type: ${oldMap.type}"
            oldValue += ", active: ${oldMap.active}"
            oldValue += ", dateCreated: ${oldMap.dateCreated}"
            oldValue += ", userCreated: ${oldMap.userCreated}"
            oldValue += ", appCreated: ${oldMap.appCreated}"

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

