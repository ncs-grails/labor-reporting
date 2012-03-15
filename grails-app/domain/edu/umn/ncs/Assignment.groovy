package edu.umn.ncs

class Assignment {

	BigDecimal percentEffort
	Date dateCreated = new Date()
	Person userCreated
	String appCreated = 'labor-reporting'
	Person staff
	Date certifyDate
	Person certifier

    static constraints = {
		percentEffort(min:0.01, max:100.00, scale:2)
		dateCreated()
		userCreated(blank:false)
		appCreated(blank:false)
		staff(blank:false)
		certifyDate(nullable:true)
		certifier(blank:true)
    }

	String toString() { percentEffort }

}
