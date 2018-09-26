# Java EE Thin Wars with Docker and WebSphere Liberty
This demo shows using Java EE thin wars with Docker repositories, layering, and caching. It uses Liberty server under Docker using the `websphere-liberty` image that is available from the online Docker Hub repository.

#Note: I added instructions to run the Java EE from a Dockerfile that added the .war file, there are also the instructions with the external volume

## Deploying Java EE Demo Application .war file via Dockerfile

1. Run maven command to create .war file: 

    ```bash
    $ mvn clean package --file ../javaee/javaee-cafe-demo/pom.xml
    ```
    
2. Copy the .war file into the current directory:

    ```bash
    $ cp ../javaee/javaee-cafe-demo/target/javaee-cafe.war .
    ```

3. In the current directory, list the contents of `Dockerfile` with the following command:

	```bash
	$ cat Dockerfile
	```
   	
   It simply starts from the `websphere-liberty` image, adds the `javaee-cafe.war` from the current directory in to the `dropins` directory, copies the PostgreSqQL driver `postgresql-42.2.4.jar` into `shared/resources` directory and replaces the defaultServer configuration file `server.xml` 

4. Review and if it is necessary update de dataSource properties in the `server.xml` file:

	```
	serverName="localhost"
	portNumber="5432"
	databaseName="postgres"
	user="postgres"
	password=""
    ```                   
	**Note:** For macOS users the serverName could be necessary to changed for `docker.for.mac.host.internal`

5. Build an image tagged `javaeedemo-wlp` using the current directory as context using the command:

	```bash
	$ docker build -t javaeedemo-wlp .
	```

6. Run the database with next command

	```bash
	docker run -it --rm  --name JavaEEDemoDB -v pgdata:/var/lib/postgresql/data -p 5432:5432 -d postgres
	```    

7. To run the newly built image, use the command:

	```bash
	$ docker run --rm -d -p 80:9080 --name=JavaEEDemoWLP javaeedemo-wlp
	```

8. Use the following command to watch the server logs:

	```bash
	$ docker logs --tail=all -f JavaEEDemoWLP
	```
    Once the .war file is copied WebSphere-Liberty will deploy it, you must get some logs like these:
    
	```
	[AUDIT   ] CWWKT0016I: Web application available (default_host): http://66971d38111a:9080/javaee-cafe/
	[AUDIT   ] CWWKZ0001I: Application javaee-cafe started in 3.581 seconds.
	```
    
   If you want to return to the command prompt type `Ctrl-C`.

9. Test the REST full service with the URL: `http://localhost/javaee-cafe/webapi/cafeRS/all` or via the Java Server Faces Client `http://localhost/javaee-cafe/index.xhtml`

10. To stop the container use the following command:

	```bash
	$ docker stop JavaEEDemoWLP
	```






#Note: These are the instructions with an external volume (our initial idea) I decide to maintain this for the moment, if it does not work I remove it

## Deploying Java EE Demo Application .war file

1. Run maven command to create .war file: 

    ```bash
    $ mvn clean package --file ../javaee/javaee-cafe-demo/pom.xml
    ```

2. Run WebSphere-Liberty on Docker, to see instructions go to [Run WebSphere-Liberty on a Docker container](../mounted-volume/README.md)

3. Copy the .war file into the `javaeedemo-volume` directory:

    ```bash
    $ cp ../javaee/javaee-cafe-demo/target/javaee-cafe.war ~/javaeedemo-volume
    ```
4. Use the following command to watch the server logs:

    ```bash
    $ docker logs --tail=all -f wlp
    ```
    Once the .war file is copied WebSphere-Liberty will deploy it, you must get some logs like these:
    ```    
    [AUDIT   ] CWWKT0016I: Web application available (default_host): http://6fa9d9de26ca:9080/javaee-cafe/
    [AUDIT   ] CWWKZ0001I: Application javaee-cafe started in 7.355 seconds.
    ```    
    
   If you want to return to the command prompt type `Ctrl-C`.

5. Test the REST full service with the URL: `http://localhost/javaee-cafe/webapi/cafeRS/all`
 or via the Java Server Faces Client `http://localhost/javaee-cafe/index.xhtml`

6. To stop the container use the following command:

    ```bash
    $ docker stop wlp
    ```
