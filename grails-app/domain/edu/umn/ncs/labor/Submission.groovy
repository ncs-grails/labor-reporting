package edu.umn.ncs.labor
import org.codehaus.groovy.grails.plugins.orm.auditable.AuditLogEvent

class Submission {

    static auditable = true

	static belongsTo = [assignment: Assignment]

	BigDecimal percentEffort
	Classification classification
	Task task
	Date dateCreated = new Date()
	String userCreted
	String appCreated = 'labor-reporting'

    static constraints = {
		assignment()
		percentEffort(max:100.00, scale:2)
		classification()
		task()
		dateCreated()
		userCreted(blank:false, maxSize:60)
		appCreated(blank:false, maxSize:60)
    }

	String toString() { percentEffort }

    def onDelete = { oldMap ->

        def now = new Date()

        String oldValue = "Submission"
            oldValue += ", assignment.period.startDate ${oldMap.assignment.period.startDate}"
            oldValue += ", assignment.period.endDate ${oldMap.assignment.period.endDate}"
            oldValue += ", percentEffort: ${oldMap.percentEffort}"
            oldValue += ", classification: ${oldMap.classification.name}"
            oldValue += ", task: ${oldMap.task.name}"
            oldValue += ", dateCreated: ${oldMap.dateCreated}"
            oldValue += ", userCreated: ${oldMap.userCreated}"
            oldValue += ", appCreated: ${oldMap.appCreated} "
        //println "PRINTLN ReportedEffortDomain.onDelete.oldValue: ${oldValue}"

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
