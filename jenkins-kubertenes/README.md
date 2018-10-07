# Jenkins with Java EE, Docker and Kubernetes

This demo will show how Jenkins works with Java EE, Docker and Kubernetes.


**Creating a service for an application running in two pods**

* Enter the following command to create the WebSphere Cluster and wait for the process to come up fully. 
The process create two resources (pod) 

	```
	kubectl create -f deployment.yml
	```

* Command to see the resources (pods).
	
	```
	kubectl get po
	```
	
	<pre>
	NAME                     READY   STATUS    RESTARTS   AGE
	wlp-58464fb498-q5sbn     1/1     Running   0          10s
	wlp-58464fb498-qvzlh     1/1     Running   0          10s
	</pre>

*  Command to describe the first resource in WLP cluster

	```
	kubectl describe po wlp-58464fb498-q5sbn
	```

	<pre>
	Labels:         app=wlp
					pod-template-hash=1402096054
	Annotations:    <none>
	Status:         Running
	IP:             172.17.0.5
	</pre>

*  Command to test the first resource in WLP cluster

	```
	curl 172.17.0.5:9080
	```	
	
*  Command to describe the second resource in WLP cluster
	```
	kubectl describe po wlp-58464fb498-qvzlh
	```
	
	<pre>
	Labels:         app=wlp
					pod-template-hash=1402096054
	Annotations:    <none>
	Status:         Running
	IP:             172.17.0.7
	</pre>
		
*  Command to test the first resource in WLP cluster

	```
	curl 172.17.0.7:9080
	```	
	

*  Command to Display information about the Deployment

	```
	kubectl get deployments wlp
	kubectl describe deployments wlp
	```
**OPCION 1 TO EXPOSE THE CLUSTER**

* Create a Service object that exposes the deployment:

	```
	kubectl expose deployment wlp --type=NodePort --name=wlp-service
	```
	 
* Display information about the Service:

	```
	kubectl describe services wlp-service
	```
	<pre>	 
	Name:                     wlp-service
	 Namespace:                default
	 Labels:                   app=wlp
	 Annotations:              <none>
	 Selector:                 app=wlp
	 Type:                     NodePort
	 IP:                       10.107.188.45
	 Port:                     port-1  9080/TCP
	 TargetPort:               9080/TCP
	 NodePort:                 port-1  30307/TCP
	 Endpoints:                172.17.0.5:9080,172.17.0.7:9080
	 Port:                     port-2  9443/TCP
	 TargetPort:               9443/TCP
	 NodePort:                 port-2  31089/TCP
	 Endpoints:                172.17.0.5:9443,172.17.0.7:9443
	 Session Affinity:         None
	 External Traffic Policy:  Cluster
	 Events:                   <none>
	</pre>
	
	Make a note of the NodePort value for the service. For example, in the preceding output, the NodePort value is 31496.
 
*  Command to test the NodePort
 
 	```
 	curl 10.107.188.45:9080
 	```	

*  Command to find the assigned IP address and port If you are running your service on `Minikube`:

 	```
 	minikube service wlp-service --url
 	```

	<pre> 	
	172-0-14-31:kubernetes hacm$ 
	http://192.168.99.100:31000
	http://192.168.99.100:30329
	</pre>

 	
* Command to see service
 
 	```
 	kubectl get services
 	```
 	
**OPCION 2 TO EXPOSE THE CLUSTER WITH A .yml file**


 	
* Command to create a service from a .yml file
 
 	```
 	kubectl create -f service.yml
 	```
 	
* Display information about the Service:

	```
	kubectl describe services wlp-service
	```
	
	<pre>
    Name:                     wlp-service
    Namespace:                default
    Labels:                   <none>
    Annotations:              <none>
    Selector:                 app=wlp
    Type:                     NodePort
    IP:                       10.104.230.20
    Port:                     <unset>  80/TCP
    TargetPort:               9080/TCP
    NodePort:                 <unset>  30807/TCP
    Endpoints:                172.17.0.5:9080,172.17.0.7:9080
    Session Affinity:         None
    External Traffic Policy:  Cluster
    Events:                   <none>
    </pre> 
    
*  Command to find the assigned IP address and port If you are running your service on `Minikube`:

 	```
 	minikube service wlp-service --url
 	```
 	WebSphere can be acceded from the URL likes next
 	
 	``` 
 	http://192.168.99.100:30807	
  	```

**Cleaning up**

* Command to delete the service 
  
 	```
 	kubectl delete services wlp-service
 	```

* Command to delete the WLP cluster
 
 	```
 	kubectl delete deployment/wlp
 	```