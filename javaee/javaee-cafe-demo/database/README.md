# Get Postgres Up and Running with Docker 

The database can be started either via docker-compose or manually. Either option is fine. 

**Via docker-compose.yml**

1. Make sure Docker is running. Open a console. Change diretories within this repository code in your file system to javaee\javaee-cafe-demo\database. Run the following docker-compose command and wait for it to initialize completely:

    ```bash
    $ docker-compose -f docker-compose.yml up
    ```
    
	**Note:** The docker-compose.yml has configurations to create the database objects and configure the database 
	to use a volume name `pgdata`. The container created is named `JavaEEDemoDB`.

2. Connect to the postgres database:
	
	- Start the PostgreSQL interactive terminal `psql`:
		
		```bash
		$ docker exec -it JavaEEDemoDB psql -U postgres
		```
	- Execute `dt` commando to check the table created:
		
		```bash
		postgres=# \dt
                 List of relations
         Schema |  Name  | Type  |  Owner   
        --------+--------+-------+----------
         public | coffee | table | postgres
        (1 row)
		```

**Manual installation**


1. Check the existent volumes:

    ```bash
    $ docker volume ls
    ```

2. If there is not a volume named `pgdata`, you can use that name to create a new one, otherwise you must choose other name:

    ```bash
    $ docker volume create pgdata
    ```
    
3. Start a Postgres container with a volume :

    ```bash
    $ docker run -it --rm -v pgdata:/var/lib/postgresql/data postgres
    ```

	**Note:** Once it's finished initializing successfully and is waiting for connections, stop it (control c)

3. Changes ownership of files and directories of the volume:

    ```bash
    $ docker run -it --rm -v pgdata:/var/lib/postgresql/data bash chown -R 1000:1000 /var/lib/postgresql/data
    ```
    
4. Run the container with the following command:

    ```bash
    $ docker run -it --rm  --name JavaEEDemoDB -v pgdata:/var/lib/postgresql/data -p 5432:5432 -d postgres
     ```
	**Note:** We use --rm to delete the container once it is stopped, and we don't define any ports because we want to run many containers

5. Execute these commands to create database objects:

    ```bash
	$ docker exec -it JavaEEDemoDB psql -U postgres -c "CREATE SEQUENCE COFFEE_SEQ START 1;"
	$ docker exec -it JavaEEDemoDB psql -U postgres -c "CREATE TABLE COFFEE (ID BIGINT NOT NULL, NAME VARCHAR(255), PRICE FLOAT, PRIMARY KEY (ID));"
     ```
	**Note:** We use --rm to delete the container once it is stopped, and we don't define any ports because we want to run many containers
	    	    
6. Connect to the postgres database:
	
	- Start the PostgreSQL interactive terminal `psql`:
		
		```bash
		$ docker exec -it JavaEEDemoDB psql -U postgres
		```
	- Execute `dt` commando to check the table created:
		
		```bash
		postgres=# \dt
                 List of relations
         Schema |  Name  | Type  |  Owner   
        --------+--------+-------+----------
         public | coffee | table | postgres
        (1 row)
		```
