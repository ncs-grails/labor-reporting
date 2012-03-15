package edu.umn.ncs

class EmailNotice {

	static belongsTo = [assignment: Assignment]
	
	Date dateSent = new Date()
	Date userSent

    static constraints = {
		dateSent(blank:false)
		userSent(blank:false)
    }

	String toString() { "${dateSent} (${userSent})" }

}
