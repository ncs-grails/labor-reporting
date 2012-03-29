import edu.umn.ncs.labor.*

class BootStrap {

	def init = { servletContext ->

		environments {

			development {

				// Title
				def titleITCoordinator = new Title(name:'IT Coordinator', active:true, userCreated:'sqv' ).save()
				def titleConsultant = new Title(name:'Consultant', active:true, userCreated:'sqv' ).save()
				def titleDataAnalyst = new Title(name:'Data Analysis', active:true, userCreated:'sqv' ).save()
				def titleOther = new Title(name:'Other', active:true, userCreated:'sqv' ).save()

				// Person
				def personBzbb = new Person(
					lastName:'Bumble-Bee', 
					firstName:'Bee', 
					middleInitial:'Z', 
					fullName:'Bee Z Bumble-Bee',
					username:'bzbb',
					title:titleITCoordinator,
					email:'sqv@.umn.edu',
					reportsEffort:true,
					userCreated:'sqv'
				).save()
				def personSqv = new Person(
					lastName:'Vuong', 
					firstName:'Simone', 
					middleInitial:'Q', 
					fullName:'Simone Q Vuong',
					username:'sqv',
					title:titleDataAnalyst,
					email:'sqv@cccs.umn.edu',
					reportsEffort:true,
					userCreated:'sqv'
				).save()

			} //development

		} //environments


	}
	def destroy = {
	}
}
