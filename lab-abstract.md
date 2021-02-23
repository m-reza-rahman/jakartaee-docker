# Docker and Kubernetes for Jakarta EE Developers Workshop

There are several key trade-offs to consider while using Docker and Kubernetes with Java/Jakarta EE applications. Examples include:

* Whether to use thin WARs, fat JARs, or hollow uber-JARs to effectively work with Docker repositories, layering, and caching.
* Whether to deploy applications within images, through exposed admin ports or as autodeployed mounted external volumes.
* How Docker networking and Kubernetes cluster deployments align with application server administration, clustering, auto-discovery, and load-balancing.
* How the CI/CD pipeline of your application can be adapted to Docker and Kubernetes.

This hands-on lab walks through each of these considerations in detail.
