
# Auto-Deploying Java EE Applications through Mounted Docker Volumes 
As opposed to deploying applications within Docker images, this demo will show an alternative way of deploying Java EE applications on Docker. Application server auto-deploy directories will point to external Docker mounted volumes. When the files in the mounted volumes change, the server inside Docker will auto-deploy it.


## Run WebSphere-Liberty on a Docker container


1. The following command will create a directory called `javaeedemo-volume` in your current user's home directory 
and bindmount it to `/config/dropins` in the WebSphere-Liberty container named `wlp`:

	```bash
	$ docker run --rm -d -p 80:9080 --name=wlp -v ~/javaeedemo-volume/:/config/dropins/ websphere-liberty
	```
     
2. Use the following command to watch the server logs:

    ```bash
    $ docker logs --tail=all -f wlp
    ```
   If you want to return to the command prompt type `Ctrl-C`.

3. To stop the container use the following command:

    ```bash
    $ docker stop wlp
    ```
