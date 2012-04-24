package edu.umn.ncs.labor
import org.codehaus.groovy.grails.plugins.orm.auditable.AuditLogEvent

class Assignment {

    static auditable = true

	static belongsTo = [person: Person, period: Period]
	static hasMany = [emails: EmailNotice, submissions: Submission]


	Period period
	BigDecimal percentEffort
	Date dateCreated = new Date()
	Person userCreated
	String appCreated = 'labor-reporting'
	Person person
	Date certifyDate
	Person certifier

    static constraints = {
		period()
		percentEffort(min:0.01, max:100.00, scale:2)
		dateCreated()
		userCreated(blank:false)
		appCreated(blank:false)
		person()
		certifyDate(nullable:true)
		certifier(blank:true)
    }

	String toString() { percentEffort }

    def onDelete = { oldMap ->

        def now = new Date()

        String oldValue = "Assignment"
            oldValue += ", period.startDate ${oldMap.period.startDate}"
            oldValue += ", period.endDate ${oldMap.period.endDate}"
            oldValue += ", percentEffort: ${oldMap.percentEffort}"
            oldValue += ", dateCreated: ${oldMap.dateCreated}"
            oldValue += ", userCreated ${oldMap.userCreated}"
            oldValue += ", appCreated: ${oldMap.appCreated}"
            oldValue += ", person ${oldMap.person}"
            oldValue += ", certifyDate: ${oldMap.certifyDate}"
            oldValue += ", certifier: "
            oldValue += "${oldMap.certifier}" ? "${null} " : "${oldMap.certifier} "
        	log.debug "ReportingStaffDomain.onDelete.oldValue: ${oldValue}"

        String className = this.class.toString().replace('class ', '')
        	log.debug "${now}\tAudit:DELETE::\t${oldValue}"

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
