package edu.umn.ncs
import org.codehaus.groovy.grails.plugins.orm.auditable.AuditLogEvent

class Activity {

	static auditable = true

	String name
    	Boolean active = false
    	Date dateCreated = new Date()
    	String userCreated
    	String appCreated= 'ncs-dlr'

	String toString() { name }

    	static constraints = {
        	name(blank:false, maxSize:1024)
        	active()
        	dateCreated()
        	userCreated(blank:false)
        	appCreated(blank:false)
    	}		

    	static mapping = { sort "name" }

    	def onDelete = { oldMap ->
        
        	def now = new Date()
		
        	String oldValue = "Activity"
			oldValue += ", name: ${oldMap.name}"
			oldValue += ", active: ${oldMap.obsolete}"
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
                		println "${now}\tError Transacting DELETE:: \t ${it}"
			}
		}        

	} 
    
}
