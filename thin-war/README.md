# Java EE Thin Wars with Docker and WebSphere Liberty
This demo shows using Java EE thin wars with Docker repositories, layering, and caching. It uses Liberty server under Docker using the `websphere-liberty` image that is available from the online Docker Hub repository.

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
