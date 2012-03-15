package edu.umn.ncs

class TaskOperationsDataElements {

	String name
	Boolean active
	Date dateCreated = new Date()
	String userCreated
	String appCreated = 'labor-reporting'

    static constraints = {
		name(blank:false)
		active()
		dateCreated(blank:false)
		userCreated(blank:false)
		appCreated(blank:false)
    }

	String toString() { name }

}
