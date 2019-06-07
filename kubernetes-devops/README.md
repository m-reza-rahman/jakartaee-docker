# CI/CD of Java EE Applications with Kubernetes

This demo will show how to do continous integration (CI)/continous delivery (CD) of Java EE applications using Kubernetes. We will use Azure DevOps Pipelines for our demo but you could easily use Jenkins or any other DevOps tool.

## Prerequisites

- You will need a GitHub account.
- You will need an Azure subscription. If you don't have one, you can get one for free for one year [here](https://azure.microsoft.com/en-us/free).
- You need to have a [Docker Hub](https://hub.docker.com) account.
- You need to have an Azure DevOps Project. You can sign up for Azure DevOps for free [here](https://azure.microsoft.com/en-us/services/devops/). [Here](https://docs.microsoft.com/en-us/azure/devops/organizations/projects/create-project) are instructions on how to set up an Azure DevOps Project. Make sure you choose Git for source control.

## Setup the Kubernetes Cluster
* You will first need to have a Kubernetes cluster configured. We used the Azure Kubernetes Service but you can use any Kubernetes capable platform such as Google or IBM Cloud.
* Go to the [Azure portal](http://portal.azure.com). Hit Create a resource -> Containers -> Kubernetes Service. Create a new resource group named javaee-cafe-group. Specify the cluster name as javaee-cafe-cluster. Hit Review + create. Hit Create.

## Setup Kubernetes Tooling
* You will now need to setup kubectl. [Here](https://kubernetes.io/docs/tasks/tools/install-kubectl/) are instructions on how to do that.
* Next you will install the Azure CLI. [Here](https://docs.microsoft.com/en-us/cli/azure/install-azure-cli?view=azure-cli-latest) are instructions on how to do that.
* You will then connect kubectl to the Kubernetes cluster you created. To do so, run the following command:

   ```
   az aks get-credentials --resource-group javaee-cafe-group --name javaee-cafe-cluster
   ```
  If you get an error about an already existing resource, you may need to delete the ~/.kube directory.

## Create Service Connections
* Clone this repository into your own GitHub account.
* Go to [Azure DevOps home](https://dev.azure.com).
* Select your project. Click on project settings -> Pipelines -> Service connections -> New service connection -> GitHub. Provide a connection name. Click authorize. Click OK.
* Select New service connection -> Docker Registry. Select Docker Hub as your registry type. Specify the connection name to be docker-hub-`<Your Docker Hub ID>`. Fill in your Docker ID, password and email. Click OK. 
* Select New service connection -> Kubernetes. Select Azure subscription as your authentication. Specify the connection name to be javaee-cafe-cluster. Select the cluster to be javaee-cafe-cluster. Click OK.

## Create and Run the Pipeline
* Select pipelines. Click new -> new build pipeline. Select GitHub as source control. Select javaee-docker from your own repository. Select existing Azure Pipelines YAML file. Select /kubernetes-devops/javaee-cafe/azure-pipelines.yml as the path. 

* In the YAML file, replace occurrences of `rezarahman` with `<Your Docker Hub ID>`. When done, hit save and hit run.
* When the job finishes running, the application will be deployed to Kubernetes.
* Get the External IP address of the Service, then the application will be accessible at `http://<External IP Address>/javaee-cafe`:
   ```
   kubectl get svc javaee-cafe --watch
   ```
  It may take a few minutes for the load balancer to be created. When the external IP changes over from *pending* to a valid IP, just hit Control-C to exit.

   > **Note:** Use the command below to find the assigned IP address and port if you are running Kubernetes locally on `Minikube`:

 	```
 	minikube service javaee-cafe --url
 	```
