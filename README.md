# Effective Docker and Kubernetes for Java EE Developers
This repository shows several key trade-offs to consider while using Docker and Kubernetes with Java EE applications. The repository hosts the demos for [this](abstract.md) talk or [this](lab-abstract.md) lab. A video for the talk is [here](https://www.youtube.com/watch?v=x-tAP4YZCcY).

The basic Java EE application used throughout is in the [javaee](/javaee) folder. 

Factors demostrated include:

* Using thin WARs with Docker. The [thin-war](/thin-war) folder shows how this is done.
* Using uber jars with Docker. The [uber-jar](/uber-jar) folder shows how this is done.
* Using hollow uber-jars with Docker. The [hollow-uber-jar](/hollow-uber-jar) folder shows how this is done.
* Administering applications through admin ports exposed out of Docker (as opposed to just deploying applications within Docker images). The [exposed-port](/exposed-port) folder shows how this is done.
* Auto-deploying applications through mounted external Docker volumes on the host (as opposed to deploying applications within Docker images). The [external-volume](/external-volume) folder shows how this is done.
* How to make application server administration, clustering, autodiscovery and load-balancing work with Docker networking and Kubernetes cluster deployments. The [kubernetes-clustering](/kubernetes-clustering) folder shows how this is done.
* How your CI/CD pipeline looks like with Java EE, Docker and Kubernetes. The [kubernetes-devops](/kubernetes-devops) folder shows how this is done.

The demos use Java EE 8, WebSphere Liberty, Azure Kubernetes Service (AKS) and Azure DevOps Pipelines.

## Hands on Lab path
Each of the parts of this repository can be run independently, which means you can go directly to the parts you interest the most. Below some recommendations

- Take a quickly look of the code in the [javaee](/javaee) folder.
- If you don't have a Cloud Service you can explore 3 ways  a Java EE application can be packaged to run on Docker [thin-war](/thin-war) , [uber-jar](/uber-jar) , [hollow-uber-jar](/hollow-uber-jar) also [exposed-port](/exposed-port) and [external-volume](/external-volume) can be run in a local Docker

- If you have a Cloud Service with a Kubernetes Engine you can go to [kubernetes-clustering](/kubernetes-clustering) , [kubernetes-devops](/kubernetes-devops) parts.
  
