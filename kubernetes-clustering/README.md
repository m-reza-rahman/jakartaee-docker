# Java EE Application Server Clustering with Kubernetes

This demo will show how to make application server administration, clustering, autodiscovery and load-balancing work with Docker clusters, networking, and Kubernetes deployments.

TODO Reza will do this part.

``
sudo docker run -it --rm --name JavaEECafeDB -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres

``

``
cd kubernetes-clustering/controller
docker build -t controller .
docker run -it --rm -p 9080:9080 -p 9443:9443 controller
``
``
copy javaee/javaee-cafe/target/javaee-cafe.war kubernetes-clustering
cd kubernetes-clustering/member
docker build -t member .
docker run -it --rm -p 9081:9080 -p 9444:9443 controller
``
