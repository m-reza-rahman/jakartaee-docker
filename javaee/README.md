# Basic Java EE CRUD Service
This is the basic Java EE 8.0 application used throughout the Docker and Kubertenes demos. It is a simple CRUD RESTful service. It uses Maven and Java EE 8 (JAX-RS, EJB, CDI, JPA, JSF, BEAN VALIDATION).

## Pre requisites

- Maven
- JDK8+
- A datasource connection: DefaultDataSource

## Build

Clone this repository and build the examples using:

```
mvn package
```

## Content

This application is composed by:

- **A RESTFul service*:** protocol://hostname:port/javaee-cafe/webapi/cafeRS

	- **_GET by Id_**: protocol://hostname:port/javaee-cafe/webapi/cafeRS/{id} 
	- **_GET all_**: protocol://hostname:port/javaee-cafe/webapi/cafeRS/all 
	- **_POST_** to add a new element
	- **_DELETE_** to delete an element


- **A Java Server Faces Client:** protocol://hostname:port/javaee-cafe/index.xhtml
