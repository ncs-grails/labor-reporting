class UrlMappings {

	static mappings = {

		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller:"main", action:"show")
		//"/"(view:"/index")
		"500"(view:'/error')

	}

}
