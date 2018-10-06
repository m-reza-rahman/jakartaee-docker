# Jenkins with Java EE, Docker and Kubernetes

This demo will show how Jenkins works with Java EE, Docker and Kubernetes.


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

*  Command to delete the WLP cluster

	```
	kubectl delete deployment/wlp
	```