# Auto-Deploying Java EE Applications through Mounted Docker Volumes 
As opposed to deploying applications within Docker images, this demo will show an alternative way of deploying Java EE applications on Docker. Application server auto-deploy directories will point to external Docker mounted host volumes. When the files in the mounted host volumes change, the server inside Docker will auto-deploy.

The following is how you run the demo.

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

* Browse to where you have this repository code in your file system. Go the external_volume directory.
* You should explore the Dockerfile in this directory used to build the Docker image. It simply starts from the `websphere-liberty` image and copies the PostgreSqQL driver `postgresql-42.2.4.jar` into the `shared/resources` directory and replaces the defaultServer configuration file `server.xml`. Notice no war file is actually added to this image.
* Pick a directory where you would like to have the applications auto-deployed from (such as /home/john/webapps). Make sure the directory is created and is empty. 
* Open a console. Build a Docker image tagged `javaee-cafe` navigating to the external_volume/ directory as context and issuing the command:

	```
	docker build -t javaee-cafe .
	```
* To run the newly built image, use the command:

	```
	docker run -it --rm -v [your auto deploy directory]:/config/dropins/ -p 9080:9080 javaee-cafe
	```
* Note, we have told Docker that the /config/dropins/ path WebSphere Liberty scans within the Docker image actually points to our external volume on the host.
* Wait for WebSphere Liberty to start (to stop the application and Liberty, simply press Control-C). Note there is no application to deploy right now.
* Open Eclipse.
* Do a full build of the javaee-cafe application via Maven by going to Right click the application -> Run As -> Maven install.
* Browse to where you have this repository code in your file system. You will now need to copy the built war file to where the external auto deploy directory is (e.g. /home/john/webapps). You will find the war file under javaee/javaee-cafe/target. 
* Notice WebSphere Liberty will now detect the fact that you have added an application to the auto-deploy directory and then deploy it (this might take a few seconds to happen).
* Once the application deploys, you can test the REST service at the URL: [http://localhost:9080/javaee-cafe/rest/coffees](http://localhost:9080/javaee-cafe/rest/coffees) or via the JSF client at [http://localhost:9080/javaee-cafe/index.xhtml](http://localhost:9080/javaee-cafe/index.xhtml).
* Now you can delete the war file from the auto-deploy location (e.g. /home/john/webapps). Notice WebSphere Liberty will detect the change and undeploy the appliction. Pretty cool!
