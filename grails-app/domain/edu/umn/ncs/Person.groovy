package edu.umn.ncs

class Person {

	String lastName
	String firstName
	String middleInitial
	String fullName
	String username
	Title title
	String email
	Boolean testAccount = false
	Boolean reportsEffort = false
	Date dateCreated = new Date()
	String userCreated 
	String appCreated = 'labor-reporting'

    static constraints = {
		lastName(blank:false)
		firstName(blank:false)
		middleInitial(blank:false)
		fullName(blank:true)
		username(blank:false, unique:true)
		title()
		email(blank:false, email:true)
		testAccount()
		reportsEffort()
		dateCreated()
		userCreated(blank:false) 
		appCreated(blank:false)
    }

	String toString() { fullNameLFM }

	String getFullNameLFM() {
		"${lastName}, ${firstName} ${middleInit ?: ''}".trim()
	}

	String getFullNameFML() {
		"${firstName} ${middleInit ?: ''} ${lastName}".trim()
	}

}
