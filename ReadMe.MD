# Delicious Recipes by Jinxia Du
## Overview
You can find your favorite recipes in this website and learn how to cook!

### Project Technical Overview:
This is a web application involves MVC design pattern and allows users to 
register accounts, search for recipes and post comments. Frameworks and tools
used include Java Spring Boot, Restful Web Services, Hibernate, servlet, JWT,
Git, Maven, and Tomcat. 
### Business Rules
    1. Objects: User, Recipe, Comment, Role
    2. Relationships:
       1. One user could publish many recipes.
       2. One user could publish many comments.
       3. A recipe could have many comments.
       4. A recipe is published by one user.
       5. A comment is published by one user.
       6. A user could have multiple roles.
       7. A role could contain multiple users.
### Development Approaches
    1. Create User, Recipe, Comment and Role.
    2. Pull the Postgres Docker image and start the container
    3. Use Flyway to migrate database schema.
    4. Use Hibernate to do ORM and interact with Postgres database
    5. Implement persistence layer, service layer, and controller
    6. Use Postman to interact with the back-end
    7. Do test for each Restful APIs
    
## Configure local environment
### 1. setup local database
Refer to postgres docker [image](https://hub.docker.com/_/postgres) for environment option.
```
docker pull postgres
docker run --name training-db -e POSTGRES_USER=${username} -e POSTGRES_PASSWORD=${password} -e POSTGRES_DB=${databaseName} -p 5431:5432 -d postgres
```
### 2. database configuration
```
database.driver = ${driverName}
database.dialect = ${dialect}
database.url = ${databaseURL}
database.user = ${userName}
database.password = ${password}
```
### 3. migrate database schema
Refer to flyway setup [documentation](https://flywaydb.org/documentation/), find all [migration schema](/src/main/resources/db/migration)
```
mvn clean compile flyway:migrate
```
### 4. setup Logger level
Refer to [document](https://logging.apache.org/log4j/2.x/manual/customloglevels.html)
```
logging.level.${projectFolderName}=${level}
```

