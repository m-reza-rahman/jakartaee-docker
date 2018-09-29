# Basic Java EE CRUD Application
This is the basic Java EE 8 application used throughout the Docker and Kubertenes demos. It is a simple CRUD application. It uses Maven and Java EE 8 (JAX-RS, EJB, CDI, JPA, JSF, Bean Validation).

We use Eclipse but you can use any Maven capable IDE such as NetBeans. We use WebSphere Liberty but you should be able to use any Java EE 8 compatiple application server such as WildFly or Payara. We use Postgres but you can use any relational database such as MySQL.

## Setup

- Install JDK 8+.
- Install the Eclipse IDE for Java EE Developers from [here](https://www.eclipse.org/downloads/packages/).
- Install Docker for your OS.

## Database creation

This demo can be run with a standalone database of a dockerized database. To run Postgres on Docker see [Run Postgres Database on a Docker container](javaee-cafe-demo/database/README.md)

Datasource is configured on WEB-INF/web.xml file, by default with next properties:

**Server:** localhost
**Port:** 5432
**Database-name:** postgres
**User:** postgres
**Password:** 123

The database can be run with next command

```
docker run -it --rm  --name JavaEEDemoDB -v pgdata:/var/lib/postgresql/data -p 5432:5432 -d postgres
```

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
