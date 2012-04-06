import edu.umn.ncs.labor.*

class BootStrap {

	def init = { servletContext ->

		environments {

			development {

				//Period
				def period2012January = new Period(startDate:'1/1/2012', endDate:'1/31/2012').save()
				def period2012February = new Period(startDate:'2/1/2012', endDate:'2/29/2012').save()
				def period2012March = new Period(startDate:'3/1/2012', endDate:'2/31/2012').save()

				// Title
				def titleAdministrator = new Title(name:'Administrator', active:true, userCreated:'will1945').save()
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
				def personWill1945 = new Person(
					lastName:'Brendalen', 
					firstName:'Sadie', 
					middleInitial:'E', 
					fullName:'Sadie E Brendalen',
					username:'will1945',
					title:titleAdministrator,
					email:'sqv@cccs.umn.edu',
					reportsEffort:true,
					userCreated:'will1945'
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

				// Classification
				def classCoin = new Classification(name:'COIN Project', active:true, userCreated:personBzbb).save() 
				def classHighLow = new Classification(name:'High-Low', active:true, userCreated:personBzbb).save() 
				def classEff = new Classification(name:'Executive Function Formative', active:true, userCreated:personBzbb).save() 
				def classDaf = new Classification(name:'Dietary Assessment Formative', active:true, userCreated:personBzbb).save() 
				def classUmf = new Classification(name:'Ulnar Measurement Formative', active:true, userCreated:personBzbb).save() 
				def classScf = new Classification(name:'Stress & Cortisol Formative', active:true, userCreated:personBzbb).save() 

				// Task Type
				def typeSfr = new TaskType(name:'Sponsored Financial Reporting', active:true, userCreated:'will1945').save()
				def typeEtdlr = new TaskType(name:'Electronically Transmitted Direct Labor Reporing', active:true, userCreated:'will1945').save()
				def typeOde = new TaskType(name:'Operations Data Elements', active:true, userCreated:'will1945').save()

				// Task
				def taskAdministrative = new Task(name:'Administrative', active:true, userCreated:personBzbb).save() 
				def taskMeeting = new Task(name:'Meeting', active:true, userCreated:personBzbb).save() 
				def taskData = new Task(name:'Data Coolection', active:true, userCreated:personBzbb).save() 
				def taskInformaticsManagement = new Task(name:'Informatics Management', active:true, userCreated:personBzbb).save() 
				def taskTraining = new Task(name:'Training', active:true, userCreated:personBzbb).save() 


			} //development

		} //environments


	}
	def destroy = {
	}
}
