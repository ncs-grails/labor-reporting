package edu.umn.ncs.labor

class LaborReportingService {

	static transactional = true

	def getPerson(principal) {

		log.debug "BEGIN def getPerson(principal)"
		
		def uname = principal.getUsername()
		log.debug "  uname = ${uname}" 

		def person = Person.findByUsername(uname)

		if ( !person ) {

			log.debug "  if (!person)" 

			person = new Person(username:uname)
			log.debug "  => person = ${person}" 

			person.email = principal.getEmail()
			log.debug "  => person.email = ${person.email}" 

			person.fullName = principal.getFullName()
			log.debug "  => person.fullName = ${person.fullName}" 

			person.userCreated = uname
			log.debug "  => person.userCreated = ${person.userCreated}" 

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
			log.debug " => nameParts.size() = ${nameParts.size()}" 
			log.debug " => nameParts[0] = ${nameParts[0]}" 
			log.debug " => nameParts[1] = ${nameParts[1]}" 
			log.debug " => nameParts[2] = ${nameParts[2]}" 

			person.save(flush:true)
			if ( !person.save() ) {
				person.errors.each{ println it }
			}

		}  

		log.debug "  person = ${person}"
		log.debug "END def getPerson(principal)"
		return person

	} 

}
