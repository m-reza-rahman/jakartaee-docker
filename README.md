# Effective Docker and Kubernetes for Java EE Developers
This repository shows several key trade-offs to consider while using Docker and Kubernetes with Java EE applications. 

The basic Java EE application used throughout is in the [javaee](/javaee) folder. 

The factors demostrated include:
* Using thin WARs with Docker
* Using uber jars with Docker
, or hollow uber-JARs to effectively work with Docker repositories, layering, and caching; whether to deploy applications within 
images, through exposed admin ports or as autodeployed mounted external volumes; how Docker clusters, networking, and Kubernetes deployments align 
with application server administration, clustering, autodiscovery, and load-balancing; 
how the CI/CD pipeline of your application can be adapted to Docker and Kubernetes; and many others.
