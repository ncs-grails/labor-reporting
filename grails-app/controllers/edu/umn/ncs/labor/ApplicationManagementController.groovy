package edu.umn.ncs.labor
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_NCS_IT', 'ROLE_NCS_DLR_MANAGE', 'ROLE_NCS_DLR'])
class ApplicationManagementController {
	 
	def springSecurityService
	def laborReportingService
	
	def index = {

		log.debug "params = ${params}"

		redirect(action: "list", params: params)

	}    

	def list = {

		log.debug "params = ${params}"
 		
		def principal = springSecurityService.principal                        
		log.debug "principal = ${principal}" 

		def personInstance = laborReportingService.getPerson(principal)
		log.debug "personInstance = ${personInstance}" 

		[ personInstance: personInstance ]

	}            

}
