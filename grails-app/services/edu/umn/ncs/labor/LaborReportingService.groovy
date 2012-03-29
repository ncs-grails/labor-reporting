package edu.umn.ncs.labor

class LaborReportingService {

	static transactional = true

	def getPerson(principal) {

		def uname = principal.getUsername()
		log.debug "getPerson.uname: ${uname}" 

		def person = Person.findByUsername(uname)
		log.debug "getPerson.person: ${person}" 

		if ( !person ) {

			log.debug "if(!person) = TRUE" 

			person = new Person(username:uname)
			log.debug "getPerson.person: ${person}" 

			person.email = principal.getEmail()
			log.debug "getPerson.person.email: ${person.email}" 

			person.fullName = principal.getFullName()
			log.debug "getPerson.person.fullName: ${person.fullName}" 

			person.userCreated = uname
			log.debug "getPerson.person.userCreated: ${person.userCreated}" 

			def nameParts = person.fullName.tokenize()
			if ( nameParts.size() == 2 ) {
				person.firstName = nameParts[0]
				person.lastName = nameParts[1]
			}
			if ( nameParts.size() == 3 ) {
				person.firstName = nameParts[0]
				person.middleInit = nameParts[1]
				person.lastName = nameParts[2]
			}
			log.debug "getPerson.nameParts = ${nameParts.size()}" 
			log.debug "getPerson.nameParts[0] = ${nameParts[0]}" 
			log.debug "getPerson.nameParts[1] = ${nameParts[1]}" 
			log.debug "getPerson.nameParts[2] = ${nameParts[2]}" 

			person.save(flush:true)
			if ( !person.save() ) {
				person.errors.each{ println it }
			}

		} // if ( !person )

		return person

	} // getPerson()

}
