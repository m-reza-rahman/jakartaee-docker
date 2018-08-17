# Effective Docker and Kubernetes for Java EE Developers
This repository shows several key trade-offs to consider while using Docker and Kubernetes with Java EE applications. 

The basic Java EE application used throughout is in the [javaee](/javaee) folder. 

Factors demostrated include:
* Using thin WARs with Docker.
* Using uber jars with Docker.
* Using hollow uber-jars with Docker.
* Deploying applications within Docker images.
* Deploying applications through admin ports exposed out of Docker.
* Auto-deploying applications through mounted external Docker volumes.
* How to make application server administration, clustering, autodiscovery and load-balancing work with Docker clusters, networking, and Kubernetes deployments.
* How your CI/CD pipeline looks like with Java EE, Docker and Kubernetes.
