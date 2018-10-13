# Using Uber Jars
This demo will show an alternative way of deploying Java EE applications on Docker. The application server is packaged inside the jar that will run inside Docker. The application will be run without any external dependencies.

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
* Copy the `_server.xml` from the location `uber-jar/liberty/config/` to the project location `/javaee-cafe/src/main/liberty/config/_server.xml`
* Replace the `pom.xml` from the location `uber-jar/`.
* Do a full build of the javaee-cafe application via Maven command
	```
	mvn  clean install net.wasdev.wlp.maven.plugins:liberty-maven-plugin:package-server
	```
* Browse to where you have this repository code in your file system. You will now need to copy the war file to where we will build the Docker image. You will find the war file under javaee/javaee-cafe/target. Copy the jar file to uber-jar/.
* You should explore the Dockerfile in this directory used to build the Docker image. It starts from the `java:8-jre` image, adds the `embedded-wlp-liberty.jar` from the current directory in to the `uber-jar` directory. 
* Open a console. Build a Docker image tagged `javaee-cafe` navigating to the uber-jar/ directory as context and issuing the command:

	```
	docker build -t javaee-cafe .
	```
* To run the newly built image, use the command:

	```
	docker run -it --rm -p 9080:9080 javaee-cafe
	```
* Note that we are exposing both the HTTP port and HTTPS port to access the application and administrative tool.
* Wait for WebSphere Liberty to start and the application to deploy sucessfully (to stop the application and Liberty, simply press Control-C). You'll notice that the administrative tool is also deployed.
* Once the application starts, you can test the REST service at the URL: [http://localhost:9080/javaee-cafe/rest/coffees](http://localhost:9080/javaee-cafe/rest/coffees) or via the JSF client at [http://localhost:9080/javaee-cafe/index.xhtml](http://localhost:9080/javaee-cafe/index.xhtml).
