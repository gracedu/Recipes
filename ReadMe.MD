# Delicious Recipes by Jinxia Du
## Overview
You can find your favorite recipes in this website and learn how to cook!

### Project Technical Overview:
This is a web application involves MVC design pattern and allows users to 
register accounts, search for recipes and post comments. Frameworks and tools
used include Java Spring Boot, Restful Web Services, Hibernate, servlet, JWT,
Git, Maven, AWS(S3, SQS), and Tomcat. 
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
       
    3. Only the role admin could create other roles based on development needs. 
       User is the role for users.
       
    4. Authorization for each role:
       1. allowed_resource: resources that this role allowed to do actions.
       2. allowed_read: whether the role could read resources or not
       3. allowed_create: whether the role could create resources or not
       4. allowed_update: whether the role could update resources or not
       5. allowed_delete: whether the role could delete resources or not
      Admin has all resources and has every authorization set to true.
### Code Structure
This is a multi-model project managed by Maven.
* root
    * mvc: model, view, and controller 
    * consumer: consumes internal messages
### Development Approaches
    1. Create User, Recipe, Comment and Role.
    2. Create an admin.
    3. Pull the Postgres Docker image and start the container
    4. Use Flyway to migrate database schema.
    5. Use Hibernate to do ORM and interact with Postgres database
    6. Implement persistence layer, service layer, and controller
    7. Use Postman to interact with the back-end
    8. Do test for each Restful APIs
    9. Initializa AWS S3 bucket as a cloud storage
    10. Implement JMS(Java Message Service) with AWS SQS(Simple Queue Service) to process internal messages
    
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
mvn clean compile flyway:migrate -Ddatabase.url=XXX -Ddatabase.name=XXX -Ddatabase.user=XXX -Ddatabase.password=XXX 
```
### 4. setup Logger level
Refer to [document](https://logging.apache.org/log4j/2.x/manual/customloglevels.html)
```
logging.level.${projectFolderName}=${level}
```

