package edu.umn.ncs.labor
import org.joda.time.*

class LaborReportingService {

	static transactional = true

	def getPerson(principal) {

		log.debug "BEGIN getPerson(principal)"
		
		def uname = principal.getUsername()
		log.debug "  uname = ${uname}" 

		def person = Person.findByUsername(uname)
		log.debug "  person = ${person}" 

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
		log.debug "END getPerson(principal)"
		return person

	} // getPerson(principal)


	def getCurrentPeriodOfTypeMonth() {

		log.debug "BEGIN getCurrentPeriodOfTypeMonth"

		// period type
		def periodTypeInstance = PeriodType.findByName("Month")
		log.debug " => periodTypeInstance = ${periodTypeInstance}"

		// start and end datetime
		def today = new DateMidnight()	
		log.debug " => today = ${today}"

		def lastMonthFromToday = today.minusMonths(1)
		log.debug " => lastMonthFromToday = ${lastMonthFromToday}"

		def lastMonthFirstDay = lastMonthFromToday.minusDays(lastMonthFromToday.dayOfMonth-1)
		log.debug " => lastMonthFirstDay = ${lastMonthFirstDay}"

		def lastMonthLastDay = lastMonthFirstDay.plusMonths(1).minusDays(1)
		log.debug " => lastMonthLastDay = ${lastMonthLastDay}"

		def startDate = lastMonthFirstDay.toCalendar().getTime()
		log.debug " => startDate = ${startDate}"

		def endDate = lastMonthLastDay.toCalendar().getTime()
		log.debug " => endDate = ${endDate}"

		// period
		def currentPeriodInstance = Period.findOrCreateWhere(type:periodTypeInstance, startDate:startDate, endDate:endDate) 
		log.debug " => currentPeriodInstance = ${currentPeriodInstance}"

		log.debug "END getCurrentPeriodOfTypeMonth"

		return currentPeriodInstance
	
	}

}
