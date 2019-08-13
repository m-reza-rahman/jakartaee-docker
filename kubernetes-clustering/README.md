# Java EE Application Server Clustering with Kubernetes

This demo will show how to make application server administration, clustering, autodiscovery and load-balancing work with Docker clusters, networking, and Kubernetes deployments.

## Prerequisites

- You need to have a Kubernetes cluster with kubectl installed and configured to use your cluster. We used the Azure Kubernetes Service but you can use any Kubernetes capable platform such as Google or IBM Cloud. You can even run Kubernetes locally.
- You need to have Docker CLI installed and you must be signed into your Docker Hub account. To create a Docker Hub account go to [https://hub.docker.com](https://hub.docker.com).

## Setup the Kubernetes Cluster
* You will need an Azure subscription. If you don't have one, you can get one for free for one year [here](https://azure.microsoft.com/en-us/free).
* You will first need to create the Kubernetes cluster. Go to the [Azure portal](http://portal.azure.com). Hit Create a resource -> Containers -> Kubernetes Service. Select the resource group to be javaee-cafe-group-`<your suffix>` (the suffix could be your first name such as "reza"). Specify the cluster name as javaee-cafe-cluster-`<your suffix>` (the suffix could be your first name such as "reza"). Hit Review + create. Hit Create.

## Setup Kubernetes Tooling
* You will now need to setup kubectl. [Here](https://kubernetes.io/docs/tasks/tools/install-kubectl/) are instructions on how to do that.
* Next you will install the Azure CLI. [Here](https://docs.microsoft.com/en-us/cli/azure/install-azure-cli?view=azure-cli-latest) are instructions on how to do that.
* You will then connect kubectl to the Kubernetes cluster you created. To do so, run the following command:

   ```
   az aks get-credentials --resource-group javaee-cafe-group-<your suffix> --name javaee-cafe-cluster-<your suffix>
   ```
  If you get an error about an already existing resource, you may need to delete the ~/.kube directory.

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
