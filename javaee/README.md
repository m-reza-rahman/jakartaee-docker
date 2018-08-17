# Effective Docker and Kubernetes for Java EE Developers

There are several key trade-offs to consider while using Docker and Kubernetes with Java EE applications. Examples include whether to use 
thin WARs, fat JARs, or hollow uber-JARs to effectively work with Docker repositories, layering, and caching; whether to deploy applications within 
images, through exposed admin ports or as autodeployed mounted external volumes; how Docker clusters, networking, and Kubernetes deployments align 
with application server administration, clustering, autodiscovery, and load-balancing; 
how the CI/CD pipeline of your application can be adapted to Docker and Kubernetes; and many others.