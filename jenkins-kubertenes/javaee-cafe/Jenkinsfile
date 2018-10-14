pipeline {
	agent any

	tools {
		// Get the Maven tool.
		// ** NOTE: This 'maven_3_5_4' Maven tool must be configured
		// ** in the global configuration.
		maven 'maven_3_5_4'
	}

	stages {

		stage ('Build') {
			steps {
					sh 'mvn clean package'
			}
		}

		stage ('Deploy') {

			steps {
					sh 'mvn deploy'
			}
		}
	}
}