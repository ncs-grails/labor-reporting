import edu.umn.ncs.labor.*

class BootStrap {

	def init = { servletContext ->

		environments {

			development {

				// Period Type
				def periodTypeCalendarWeek = new PeriodType(name:'Week').save(failOnError:true)
				def periodTypeCalendarMonth = new PeriodType(name:'Month').save(failOnError:true)

				// Period
				def period2012January = new Period(startDate:'1/1/2012', endDate:'1/31/2012', type:periodTypeCalendarMonth).save(failOnError:true)
				def period2012February = new Period(startDate:'2/1/2012', endDate:'2/29/2012', type:periodTypeCalendarMonth).save(failOnError:true)
				def period2012March = new Period(startDate:'3/1/2012', endDate:'2/31/2012', type:periodTypeCalendarMonth).save(failOnError:true)

				// Title
				def titleAdministrator = new Title(name:'Administrator', active:true, dateCreated:'4/1/2012', userCreated:'will1945', appCreated:'labor-reporting').save(failOnError:true)
				def titleITCoordinator = new Title(name:'IT Coordinator', active:true, userCreated:'sqv').save(failOnError:true)
				def titleConsultant = new Title(name:'Consultant', active:true, userCreated:'sqv').save(failOnError:true)
				def titleDataAnalyst = new Title(name:'Data Analysis', active:true, userCreated:'sqv').save(failOnError:true)
				def titleOther = new Title(name:'Other', active:true, userCreated:'sqv').save(failOnError:true)

				// Person
				def personWill1945 = new Person(
					lastName:'Brendalen', 
					firstName:'Sadie', 
					middleInitial:'E', 
					fullName:'Sadie E Brendalen',
					username:'will1945',
					title:titleAdministrator,
					email:'sqv@cccs.umn.edu',
					reportsEffort:true,
					userCreated:'ajz'
				).save(failOnError:true)
				def personSqv = new Person(
					lastName:'Vuong', 
					firstName:'Simone', 
					middleInitial:'Q', 
					fullName:'Simone Q Vuong',
					username:'sqv',
					title:titleDataAnalyst,
					email:'sqv@cccs.umn.edu',
					reportsEffort:true,
					userCreated:'ajz'
				).save(failOnError:true)

				// Classification
				def classCoin = new Classification(name:'COIN Project', active:true, userCreated:'will1945').save(failOnError:true) 
				def classHighLow = new Classification(name:'High-Low', active:true, userCreated:'will1945').save(failOnError:true) 
				def classEff = new Classification(name:'Executive Function Formative', active:true, userCreated:'will1945').save(failOnError:true) 
				def classDaf = new Classification(name:'Dietary Assessment Formative', active:true, userCreated:'will1945').save(failOnError:true) 
				def classUmf = new Classification(name:'Ulnar Measurement Formative', active:true, userCreated:'will1945').save(failOnError:true) 
				def classScf = new Classification(name:'Stress & Cortisol Formative', active:true, userCreated:'will1945').save(failOnError:true) 

				// Task Type
				def typeSfr = new TaskType(name:'Sponsored Financial Reporting', active:true, userCreated:'will1945').save(failOnError:true)
				def typeEtdlr = new TaskType(name:'Electronically Transmitted Direct Labor Reporing', active:true, userCreated:'will1945').save(failOnError:true)
				def typeOde = new TaskType(name:'Operations Data Elements', active:true, userCreated:'will1945').save(failOnError:true)

				// Task
				def taskAdministrative = new Task(name:'Administrative', type:typeSfr, active:true, userCreated:'sqv').save(failOnError:true) 
				def taskMeeting = new Task(name:'Meeting', type:typeSfr, active:true, userCreated:'sqv').save(failOnError:true) 
				def taskData = new Task(name:'Data Coolection', type:typeSfr, active:true, userCreated:'sqv').save(failOnError:true) 
				def taskInformaticsManagement = new Task(name:'Informatics Management', type:typeSfr, active:true, userCreated:'sqv').save(failOnError:true) 
				def taskTraining = new Task(name:'Training', type:typeSfr, active:true, userCreated:'sqv').save(failOnError:true) 


			} //development

		} //environments

	}

	def destroy = {
	}

}
