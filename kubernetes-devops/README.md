# CI/CD of Java EE Applications with Kubernetes

This demo will show how to do continous integration (CI)/continous delivery (CD) of Java EE applications using Kubernetes. We will use Azure DevOps Pipelines for our demo but you could easily use Jenkins or any other DevOps tool.

## Prerequisites

- You will need an Azure subscription. If you don't have one, you can get one for free for one year [here](https://azure.microsoft.com/en-us/free).
- You need to have a [Docker Hub](https://hub.docker.com) account.
- You need to have an Azure DevOps Project. You can sign up for Azure DevOps for free [here](https://azure.microsoft.com/en-us/services/devops/). [Here](https://docs.microsoft.com/en-us/azure/devops/organizations/projects/create-project) are instructions on how to set up an Azure DevOps Project. Make sure you choose Git for source control.

## Setup the Kubernetes Cluster
* You will first need to have a Kubernetes cluster configured. We used the Azure Kubernetes Service but you can use any Kubernetes capable platform such as Google or IBM Cloud.
* Go to the [Azure portal](http://portal.azure.com). Hit Create a resource -> Containers -> Kubernetes Service. Create a new resource group named javaee-cafe-group. Specify the cluster name as javaee-cafe-cluster. Hit Review + create. Hit Create.

## Create Service Connections
* Go to [Azure DevOps home](https://dev.azure.com).
* Select your project. Click on project settings -> Pipelines -> Service connections -> New service connection -> GitHub. Provide a connection name. Click authorize. Click OK.
* Select New service connection -> Docker Registry. Select Docker Hub as your registry type. Specify the connection name to be <your Docker Hub ID>-docker-hub. Fill in your Docker ID, password and email. Click OK. 



## Deploy the Java EE Application and Postgres on Kubernetes
* Open a terminal. Navigate to where you have this repository code in your file system. Navigate to the kubernetes-clustering/ directory.
* Deploy postgres with a persistent volume claim with the following command:
   ```
   kubectl create -f postgres.yml
   ```

* Open Eclipse.
* Do a full build of the javaee-cafe application via Maven by going to Right click the application -> Run As -> Maven install.
* Browse to where you have this repository code in your file system. You will now need to copy the war file to where we will build the Docker image next. You will find the war file under javaee/javaee-cafe/target. Copy the war file to kubernetes-clustering/.
* Open a terminal. Navigate to where you have this repository code in your file system. Navigate to the kubernetes-clustering/ directory.
* Log in to Docker Hub using the docker login command:
   ```
   docker login
   ```
* Build a Docker image and push the image to Docker Hub:
   ```
   docker build -t <your Docker Hub account>/javaee-cafe:v1 .
   docker push <your Docker Hub account>/javaee-cafe:v1
   ```
* Replace the `<your Docker Hub account>` value with your account name in `javaee-cafe.yml` file, then deploy the application:
   ```
   kubectl create -f javaee-cafe.yml
   ```

* Get the External IP address of the Service, then the application will be accessible at `http://<External IP Address>/javaee-cafe`:
   ```
   kubectl get svc javaee-cafe --watch
   ```
  It may take a few minutes for the load balancer to be created. When the external IP changes over from *pending* to a valid IP, just hit Control-C to exit.

   > **Note:** Use the command below to find the assigned IP address and port if you are running Kubernetes locally on `Minikube`:

 	```
 	minikube service javaee-cafe --url
 	```

* Scale your application:
   ```
   kubectl scale deployment javaee-cafe --replicas=3
   ```
   
## Deleting the Resources
* Delete the Java EE deployment:
   ```
   kubectl delete -f javaee-cafe.yml
   ```

* Delete Postgres:
   ```
   kubectl delete -f postgres.yml
   ```
