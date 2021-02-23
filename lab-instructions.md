# Lab Setup Instructions
The following are the setup instructions for the lab (do look at the diferent optional paths for the lab below. You won't need the cloud specific parts if that is not what interests you such as GitHub, Docker Hub, Kubernetes and Azure).

* Please bring your laptop and ensure a reliable internet connection.
* Install Java SE 8 or Java SE 11 (we used [AdoptOpenJDK OpenJDK 8 LTS/HotSpot](https://adoptopenjdk.net)).
* Install the Eclipse IDE for Enterprise Developers from [here](https://www.eclipse.org/downloads/packages/).
* Install WebSphere Liberty in Eclipse by following the instructions [here](https://developer.ibm.com/wasdev/downloads/liberty-profile-using-eclipse/). Make sure to install WebSphere Liberty with full Jakarta EE 8 (you can automatically download right from the IDE).
* Install Docker for your OS.
* You will need a GitHub account.
* You need to have a [Docker Hub](https://hub.docker.com) account.
* You will need to setup kubectl. [Here](https://kubernetes.io/docs/tasks/tools/install-kubectl/) are instructions on how to do that.
* Optionally get a free one year Azure subscription [here](https://azure.microsoft.com/en-us/free). You will need to provide a credit card.
* Optionally install the Azure CLI. [Here](https://docs.microsoft.com/en-us/cli/azure/install-azure-cli?view=azure-cli-latest) are instructions on how to do that.
* Optionally sign up for Azure DevOps for free [here](https://azure.microsoft.com/en-us/services/devops/).

# Lab Paths
Each of the parts of this repository can be run independently, which means you can go directly to the parts that interest you the most. Below are some recommendations.

* Take a quick look at the code in the [jakartaee](/jakartaee) folder.
* If you don't have a cloud service you could explore the 3 ways a Jakarta EE application can be packaged to run on Docker [thin-war](/thin-war), [uber-jar](/uber-jar) and [hollow-uber-jar](/hollow-uber-jar). Also, the [exposed-port](/exposed-port) and [external-volume](/external-volume) sections can be run in a local Docker.
* If you have a cloud service such as Azure with a Kubernetes engine you can go to the [kubernetes-clustering](/kubernetes-clustering) and [kubernetes-devops](/kubernetes-devops) parts.
