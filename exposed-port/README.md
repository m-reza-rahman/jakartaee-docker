# Using Exposed Docker Admin Ports with Java EE and Docker
As opposed to only deploying applications within Docker images, this demo will show an alternative way of administering Java EE applications on Docker. Application server admin ports will be exposed outside Docker. The application will then be administered through this admin port or console.

## Start the Database with Docker
The first step to getting the application running is getting the database up. Please follow the instructions below to get the database running.

* Ensure that all running Docker containers are shut down. You may want to do this by restarting Docker. The demo depends on containers started in the exact order as below (this will be less of a problem when we start using Kubernetes).
* Make sure Docker is running. Open a console.
* Enter the following command and wait for the database to come up fully.
```
docker run -it --rm --name javaee-cafe-db -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres
```
* The database is now ready (to stop it, simply press Control-C after the Java EE application is shutdown).

## Start the Application with Docker
The next step is to get the application up and running. Follow the steps below to do so.

* Open Eclipse.
* Do a full build of the javaee-cafe application via Maven by going to Right click the application -> Run As -> Maven install.
* Browse to where you have this repository code in your file system. You will now need to copy the war file to where we will build the Docker image. You will find the war file under javaee/javaee-cafe/target. Copy the war file to exposed-port/.
* You should explore the Dockerfile in this directory used to build the Docker image. It starts from the `websphere-liberty` image, adds the `javaee-cafe.war` from the current directory in to the `dropins` directory, copies the PostgreSqQL driver `postgresql-42.2.4.jar` into the `shared/resources` directory and replaces the defaultServer configuration file `server.xml`. Note that we also use the WebSphere installation utility to install the admin center that we will use to administer the application.
* You should also note the `server.xml`. We have added an admin role to access the adminstrative tool with.
* Open a console. Build a Docker image tagged `javaee-cafe` navigating to the exposed-port/ directory as context and issuing the command:

	```
	docker build -t javaee-cafe .
	```
* To run the newly built image, use the command:

	```
	docker run -it --rm -p 9080:9080 -p 9443:9443 javaee-cafe
	```
* Note that we are exposing both the HTTP port and HTTPS port to access the application and administrative tool.
* Wait for WebSphere Liberty to start and the application to deploy sucessfully (to stop the application and Liberty, simply press Control-C). You'll notice that the administrative tool is also deployed.
* Once the application starts, you can test the REST service at the URL: [http://localhost:9080/javaee-cafe/rest/coffees](http://localhost:9080/javaee-cafe/rest/coffees) or via the JSF client at [http://localhost:9080/javaee-cafe/index.xhtml](http://localhost:9080/javaee-cafe/index.xhtml).
* You can now also log into the administrative tool using the credentials in the server.xml and administer the application by accessing [http://localhost:9080/adminCenter](http://localhost:9080/adminCenter).
