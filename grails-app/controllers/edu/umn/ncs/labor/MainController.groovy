package edu.umn.ncs.labor
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_NCS_IT', 'ROLE_NCS_DLR_MANAGE', 'ROLE_NCS_DLR'])
class MainController {

	def springSecurityService
	def laborReportingService

	def index = {

		log.debug "params = ${params}"

		redirect(action: "show")

	}    

	def show = {

		log.debug "params = ${params}"
 		
		// get login user
		def principal = springSecurityService.principal                        
		log.debug "principal = ${principal}" 

		def personInstance = laborReportingService.getPerson(principal)
		log.debug "personInstance = ${personInstance}" 

		[ personInstance: personInstance ]  

	}            

} 
