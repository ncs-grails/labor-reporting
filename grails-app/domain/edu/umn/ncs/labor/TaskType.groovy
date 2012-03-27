package edu.umn.ncs.labor

class TaskType {

	String name
	Boolean active
	Date dateCreated = new Date()
	String userCreated
	String appCreated = 'labor-reporting'

    static constraints = {
		name(blank:false, unique:true, maxSize:100)
		active()
		dateCreated(blank:false)
		userCreated(blank:false)
		appCreated(blank:false)
    }

	String toString() { name }
	
	static mapping = { sort "name" }

}
