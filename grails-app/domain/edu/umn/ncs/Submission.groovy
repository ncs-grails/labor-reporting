package edu.umn.ncs

class Submission {

	BigDecimal percentEffort
	Classification classification
	TaskSponsoredFinancialReporting task
	Date dateCreated = new Date()
	String userCreted
	String appCreated

    static constraints = {
		percentEffort(max:100.00, scale:2)
		classification()
		task()
		dateCreated(blank:false)
		userCreted(blank:false)
		appCreated(blank:false)
    }

	String toString() { percentEffort }

}
