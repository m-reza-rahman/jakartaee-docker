# Java EE Thin Wars with Docker and WebSphere Liberty
This demo shows using Java EE thin wars with Docker repositories, layering, and caching. It uses Liberty server under Docker using the `websphere-liberty` image that is available from the online Docker Hub repository.

## Packing WebSphere Liberty and the Java EE Application .war file

1. The Dockerfile starts from the `websphere-liberty` image and adds the `javaee-cafe.war` from the current directory in to the `dropins` directory. To see `Dockerfile` file use the following command:

    ```bash
    $ cat Dockerfile
    ```
    
2. Build an image tagged `javaee-app` using the current directory as context using the command:

    ```bash
    $ docker build -t javaee-app .
    ```
3. To run the newly built image, use the command:

    ```bash
    $ docker run -d -p 80:9080 --name=wlp javaee-app
    ```
     Note that we specified the `-d` option to run the container in the background, `-p` option to map the port 9080 from the container to port 80 on the host virtual machine and `-d` option to give a name to the container.
     
4. Use the following command to watch the server logs:

    ```bash
    $ docker logs --tail=all -f wlp
    ```
   If you want to return to the command prompt type `Ctrl-C`.

5. To stop the container use the following command:

    ```bash
    $ docker stop javaee-app
    ```
