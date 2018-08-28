# Java EE Thin Wars with Docker and WebSphere Liberty
This demo shows using Java EE thin wars with Docker repositories, layering, and caching. It uses Liberty server under Docker using the `websphere-liberty` image that is available from the online Docker Hub repository.

## Packing WebSphere Liberty and the Java EE Application .war file

1. Run maven command to create .war file: 

    ```bash
    $ mvn clean package --file ../javaee/javaee-cafe-demo/pom.xml
    ```

2. Copy the .war file into the thin-war directory:

    ```bash
    $ cp ../javaee/javaee-cafe-demo/target/javaee-cafe.war .
    ```

3. Download the PostgreSQL JDBC Driver into the thin-war directory from: [https://jdbc.postgresql.org](https://jdbc.postgresql.org)
    
4. The Dockerfile starts from the `websphere-liberty` image and adds the `javaee-cafe.war` from the current directory in to the `dropins` directory. To see `Dockerfile` file use the following command:

    ```bash
    $ cat Dockerfile
    ```
    
5. Build an image tagged `javaee-app` using the current directory as context using the command:

    ```bash
    $ docker build -t javaee-app .
    ```
6. To run the newly built image, use the command:

    ```bash
    $ docker run --rm -d -p 80:9080 --name=wlp javaee-app
    ```
     Note that we specified the `-d` option to run the container in the background, `-p` option to map the port 9080 from the container to port 80 on the host virtual machine and `-d` option to give a name to the container.
     
7. Use the following command to watch the server logs:

    ```bash
    $ docker logs --tail=all -f wlp
    ```
   If you want to return to the command prompt type `Ctrl-C`.

8. Test the REST full service with the URL: `http://localhost/javaee-cafe/webapi/cafeRS/all`
 or via the Java Server Faces Client `http://localhost/javaee-cafe/index.xhtml`

9. To stop the container use the following command:

    ```bash
    $ docker stop javaee-app
    ```
