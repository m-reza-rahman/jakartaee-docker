# Effective Docker and Kubernetes for Java EE Developers
This repository shows several key trade-offs to consider while using Docker and Kubernetes with Java EE applications. 

The basic Java EE application used throughout is in the [javaee](/javaee) folder. 

Factors demostrated include:
* Using thin WARs with Docker. The [thin-war](/thin-war) folder shows how this is done.
* Using uber jars with Docker. The [uber-jar](/uber-jar) folder shows how this is done.
* Using hollow uber-jars with Docker. The [hollow-uber-jar](/hollow-uber-jar) folder shows how this is done.
* Administering applications through admin ports exposed out of Docker (as opposed to just deploying applications within Docker images). The [exposed-port](/exposed-port) folder shows how this is done.
* Auto-deploying applications through mounted external Docker volumes on the host (as opposed to deploying applications within Docker images). The [external-volume](/external-volume) folder shows how this is done.
* How to make application server administration, clustering, autodiscovery and load-balancing work with Docker clusters, networking, and Kubernetes deployments. The [kubernetes-clustering](/kubernetes-clustering) folder shows how this is done.
* How your CI/CD pipeline looks like with Java EE, Docker and Kubernetes. The [jenkins-kubertenes](/jenkins-kubertenes) folder shows how this is done.

The demos use Java EE 8, WebSphere Liberty, Google Cloud and Jenkins.

# TODO
* Use Kubernetes service host names.
