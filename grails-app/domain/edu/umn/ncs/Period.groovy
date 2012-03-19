package edu.umn.ncs
import org.joda.time.*
import org.codehaus.groovy.grails.plugins.orm.auditable.AuditLogEvent

class Period {

    static auditable = true

	Date startDate
	Date endDate

	String toString () { "${startDate} - ${endDate}" }

    static constraints = {
		startDate()
		endDate()
    }

	static hasMany = [assignment: Assignment]

	def onDelete = { oldMap ->

        def now = new Date()

        String oldValue = "Period"
            oldValue += ", startDate: ${oldMap.startDate}"
            oldValue += ", endDate: ${oldMap.endDate} "
        //println "PRINTLN ReportingPeriodDomain.onDelete.oldValue: ${oldValue}"

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
                //println "${now}\tError Transacting DELETE:: \t ${it}"
            }
        }

    }
}
