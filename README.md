# Effective Docker and Kubernetes for Java/Jakarta EE Developers
This repository shows several key trade-offs to consider while using Docker and Kubernetes with Java/Jakarta EE applications. The repository hosts the demos for [this](abstract.md) talk or [this](lab-abstract.md) lab (if you are utilizing the lab, please do look through the [lab instructions](lab-instructions.md)). A video for the talk is [here](https://www.youtube.com/watch?v=x-tAP4YZCcY).

The basic Jakarta EE application used throughout is in the [jakartaee](/jakartaee) folder. 

Factors demostrated include:

* Using thin WARs with Docker. The [thin-war](/thin-war) folder shows how this is done.
* Using uber jars with Docker. The [uber-jar](/uber-jar) folder shows how this is done.
* Using hollow uber-jars with Docker. The [hollow-uber-jar](/hollow-uber-jar) folder shows how this is done.
* Administering applications through admin ports exposed out of Docker (as opposed to just deploying applications within Docker images). The [exposed-port](/exposed-port) folder shows how this is done.
* Auto-deploying applications through mounted external Docker volumes on the host (as opposed to deploying applications within Docker images). The [external-volume](/external-volume) folder shows how this is done.
* How to make application server administration, clustering, autodiscovery and load-balancing work with Docker networking and Kubernetes cluster deployments. The [kubernetes-clustering](/kubernetes-clustering) folder shows how this is done.
* How your CI/CD pipeline looks like with Java/Jakarta EE, Docker and Kubernetes. The [kubernetes-devops](/kubernetes-devops) folder shows how this is done.

The demos use Jakarta EE 8, WebSphere Liberty, Azure Kubernetes Service (AKS) and Azure DevOps Pipelines.
