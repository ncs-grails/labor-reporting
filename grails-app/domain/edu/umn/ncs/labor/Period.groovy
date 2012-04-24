package edu.umn.ncs.labor
import org.joda.time.*
import org.codehaus.groovy.grails.plugins.orm.auditable.AuditLogEvent

class Period {

    static auditable = true

	Date startDate
	Date endDate
	PeriodType type

	String toString () { "${startDate} - ${endDate}" }

    static constraints = {
		startDate()
		endDate()

    }

	static hasMany = [assignments: Assignment]

	def onDelete = { oldMap ->

        def now = new Date()

        String oldValue = "Period"
            oldValue += ", startDate: ${oldMap.startDate}"
            oldValue += ", endDate: ${oldMap.endDate} "
            oldValue += ", periodType: ${oldMap.periodType} "

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
