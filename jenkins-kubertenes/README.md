# Jenkins with Java EE, Docker and Kubernetes

This demo will show how Jenkins works with Java EE, Docker and Kubernetes. 

## Prerequisites

- We used the Google Cloud. 
- Compute Engine, Container Engine, and Container Builder APIs, go to this link to enable [https://console.cloud.google.com/flows/enableapi?apiid=compute_component,container,cloudbuild.googleapis.com](https://console.cloud.google.com/flows/enableapi?apiid=compute_component,container,cloudbuild.googleapis.com)

**Open Google Cloud Shell**

* Log in on Google Cloud and go to your project.

* Open Google Cloud Shell.

* Set a default compute zone:
	```
	$ gcloud config set compute/zone us-east1-d
	```
* Clone the repository:
	```
	$ git clone https://github.com/m-reza-rahman/javaee-docker.git
	```


**Create a Kubernetes Cluster**

* Open Google Cloud Shell.

* Use Google Container Engine to create and manage a Kubernetes cluster. Provision the cluster with gcloud:
	```
	gcloud container clusters create jenkins-cd \
	--num-nodes 2 \
	--machine-type n1-standard-2 \
	--scopes "https://www.googleapis.com/auth/projecthosting,cloud-platform"
	```
	
	Wait for a time the operation is complete

* Download the credentials for the cluster using the gcloud CLI:
	```
	$ gcloud container clusters get-credentials jenkins-cd
	Fetching cluster endpoint and auth data.
	kubeconfig entry generated for jenkins-cd.
	```
* Confirm that the cluster is running and kubectl is working by listing pods:
	```
	$ kubectl get pods
	No resources found.
	```


**Install Helm**

We used Helm to install Jenkins from the Charts repository. Helm is a package manager that makes it easy to configure and deploy Kubernetes applications. 

Once you have Jenkins installed, you'll be able to set up your CI/CD pipleline.

* Download and install the helm binary
	```
	wget https://storage.googleapis.com/kubernetes-helm/helm-v2.9.1-linux-amd64.tar.gz
	```

* Unzip the helm binary:
	```
	tar zxfv helm-v2.9.1-linux-amd64.tar.gz
	cp linux-amd64/helm .
	```

* Add the logged users a cluster administrator in the cluster's RBAC so it can give Jenkins permissions in the cluster:

	```
	kubectl create clusterrolebinding cluster-admin-binding --clusterrole=cluster-admin --user=$(gcloud config get-value account)
	```
	
* Grant Tiller, the server side of Helm, the cluster-admin role in the cluster:

	```
	kubectl create serviceaccount tiller --namespace kube-system
	kubectl create clusterrolebinding tiller-admin-binding --clusterrole=cluster-admin --serviceaccount=kube-system:tiller
	```
	
* Initialize Helm. This ensures that the server side of Helm (Tiller) is properly installed in the cluster.

	```
	./helm init --service-account=tiller
	./helm update
	```
	
* Ensure Helm is properly installed by running the following command. The versions appear for both the server and the client of v2.9.1:
	```
	./helm version
	Client: &version.Version{SemVer:"v2.9.1", GitCommit:"20adb27c7c5868466912eebdf6664e7390ebe710", GitTreeState:"clean"}
    Server: &version.Version{SemVer:"v2.9.1", GitCommit:"20adb27c7c5868466912eebdf6664e7390ebe710", GitTreeState:"clean"}
    ```

## Configure and Install Jenkins

* Open Google Cloud Shell.

* Check the repository and helm path with an ls command
	```
	ls
	```
		
> **Note:** Next steps assumed both the repository and and help are in the same directory.
	
* Use the Helm CLI to deploy the chart with your configuration set.

    ```shell
    ./helm install -n cd stable/jenkins -f javaee-docker/jenkins-kubertenes/values.yaml --version 0.16.6 --wait
    ```
	> **Note:** It may take a few minutes for the chart to be deployed.

* Ensure the Jenkins pod goes to the `Running` state and the container is in the `READY` state:

    ```shell
    $ kubectl get pods
    NAME                          READY     STATUS     RESTARTS   AGE
    cd-jenkins-657d48bd5b-fbrpk   0/1       Init:0/1   0          23s
    ```

* Setup a port forwarding to the Jenkins UI from the Cloud Shell

    ```shell
    export POD_NAME=$(kubectl get pods -l "component=cd-jenkins-master" -o jsonpath="{.items[0].metadata.name}")
    kubectl port-forward $POD_NAME 8080:8080 >> /dev/null &
    ```

* Check that the Jenkins Service was created properly:

    ```shell
    $ kubectl get svc
    NAME               CLUSTER-IP     EXTERNAL-IP   PORT(S)     AGE
    cd-jenkins         10.35.249.67   <none>        8080/TCP    3h
    cd-jenkins-agent   10.35.248.1    <none>        50000/TCP   3h
    kubernetes         10.35.240.1    <none>        443/TCP     9h
    ```

We have used the [Kubernetes Plugin](https://wiki.jenkins-ci.org/display/JENKINS/Kubernetes+Plugin) so that our builder nodes will be automatically launched as necessary when the Jenkins master requests them.
Upon completion of their work they will automatically be turned down and their resources added back to the clusters resource pool.

Notice that this service exposes ports `8080` and `50000` for any pods that match the `selector`. This will expose the Jenkins web UI and builder/agent registration ports within the Kubernetes cluster.
Additionally the `jenkins-ui` services is exposed using a ClusterIP so that it is not accessible from outside the cluster.

## Connect to Jenkins

* Retrieve the Jenkins admin password:

    ```shell
    printf $(kubectl get secret cd-jenkins -o jsonpath="{.data.jenkins-admin-password}" | base64 --decode);echo
    ```

* Open the Jenkins user interface by click on the Web Preview button in cloud shell, then click “Preview on port 8080”:

	Log in with username `admin` and your auto generated password.




#NOTE: NEXT STEPS ARE FOR DEPLOY OUR JAVAEE-APP, I AM WORKING ON IT  :)


## Clean up

To clean up, navigate to the [Google Developers Console Project List](https://console.developers.google.com/project), choose the project you created and delete it.