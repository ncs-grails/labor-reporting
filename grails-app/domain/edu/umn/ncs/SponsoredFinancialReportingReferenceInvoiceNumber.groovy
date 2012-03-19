package edu.umn.ncs
import org.codehaus.groovy.grails.plugins.orm.auditable.AuditLogEvent

class SponsoredFinancialReportingReferenceInvoiceNumber {

    static auditable = true

	Period period
	Integer referenceInvoiceNumber
	Date reportCompletionDate
	Date dateCreated = new Date()
	String userCreated 
	String appCreated = 'labor-reporting'
	
	String toString() { referenceInvoiceNumber }	
    
	static constraints = {
		period()
		referenceInvoiceNumber()
		reportCompletionDate()
		dateCreated()
		userCreated(blank:false, maxSize:60) 
		appCreated(blank:false, maxSize:60)
    }

	def onDelete = { oldMap ->

		def now = new Date()

		String oldValue = "SponsoredFinancialReportingReferenceInvoiceNumber"
			oldValue += ", period.startDate: ${oldMap.period.startDate}"
			oldValue += ", period.endDate: ${oldMap.period.endDate}"
			oldValue += ", referenceInvoiceNumber: ${oldMap.referenceInvoiceNumber}"
			oldValue += ", reportCompletionDate: ${oldMap.reportCompletionDate}"
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
			auditLogEventInstance.errors.each				
				//println "${now}\tError Transacting DELETE:: \t ${it}"
		}

	}
	
}
