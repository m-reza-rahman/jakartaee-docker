# Using Uber Jars
This demo will show an alternative way of deploying Java EE applications on Docker. The application server is packaged inside a self-contained jar that will run inside Docker. The application will be run without any external dependencies.

## Start the Database with Docker
The first step to getting the application running is getting the database up. Please follow the instructions below to get the database running.

* Ensure that all running Docker containers are shut down. You may want to do this by restarting Docker. The demo depends on containers started in the exact order as below (this will be less of a problem when we start using Kubernetes).
* Make sure Docker is running. Open a console.
* Enter the following command and wait for the database to come up fully.
```
docker run -it --rm --name JavaEECafeDB -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres
```
* The database is now ready (to stop it, simply press Control-C after the Java EE application is shutdown).

## Start the Application with Docker
The next step is to get the application up and running. Follow the steps below to do so.

* Open Eclipse.
* Get the uber jar version of the javaee-cafe application into the IDE. In order to do that, go to File -> Import -> Maven -> Existing Maven Projects. Then browse to where you have this repository code in your file system and select uber-jar/javaee-cafe. Accept the rest of the defaults and finish.
* Once the application loads, you need to do a full Maven build by going to: right click the application -> Run As -> [Maven build...].
* In the Goals field of the build dialog, enter:

	```
	clean install net.wasdev.wlp.maven.plugins:liberty-maven-plugin:package-server
	```
* Hit Run and wait for the build to finish completely.	
* Browse to where you have this repository code in your file system. Go to the uber-jar/javaee-cafe directory.
* You should explore the Dockerfile in this directory used to build the Docker image. It starts from the `openjdk:8-jre-alpine` image, adds the `javaee-cafe.jar` from the current directory into the Docker image and runs the jar. 
* Open a console. Build a Docker image tagged `javaee-cafe` navigating to the uber-jar/javaee-cafe directory as context and issuing the command:

	```
	docker build -t javaee-cafe .
	```
* To run the newly built image, use the command:

	```
	docker run -it --rm -p 9080:9080 javaee-cafe
	```

* Wait for WebSphere Liberty to start and the application to deploy sucessfully (to stop the application and Liberty, simply press Control-C).
* Once the application starts, you can test the REST service at the URL: [http://localhost:9080/javaee-cafe/rest/coffees](http://localhost:9080/javaee-cafe/rest/coffees) or via the JSF client at [http://localhost:9080/javaee-cafe/index.xhtml](http://localhost:9080/javaee-cafe/index.xhtml).
