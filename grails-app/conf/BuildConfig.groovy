grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve

    repositories {
        inherits true // Whether to inherit repository definitions from plugins
        grailsPlugins()
        grailsHome()
        grailsCentral()

        // uncomment these to enable remote dependency resolution from public Maven repositories
        //mavenCentral()
        //mavenLocal()
        mavenRepo "http://artifact.ncs.umn.edu/plugins-release"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        runtime 'mysql:mysql-connector-java:5.1.18'
    }

    plugins {

		runtime ":hibernate:$grailsVersion"
        runtime ":jquery:1.7.1"
        runtime ":resources:1.1.5"

        build ":tomcat:$grailsVersion"

		compile ":audit-logging:0.5.4"
		compile ":spring-security-core:1.2.7.3"
		compile ":spring-security-ldap:1.0.6"   
		compile ":spring-security-shibboleth-native-sp:1.0.3"
		compile ":ncs-web-template:0.2.1"

		// Do not compile in WAR
		compile ":spring-security-mock:1.0.1" 

		// Test
		test ":code-coverage:1.2.5"
		test ":codenarc:0.16.1"
    }

}

codenarc.reports = {
	JenkinsXmlReport('xml') {
		outputFile = 'target/test-reports/CodeNarcReport.xml'
		title = 'CodeNarc Report for Direct Labor Reporting System'
	}
	JenkinsHtmlReport('html') {
		outputFile = 'CodeNarcReport.html'
		title = 'CodeNarc Report for Direct Labor Reporting System'
	}
}
codenarc.propertiesFile = 'grails-app/conf/codenarc.properties'

