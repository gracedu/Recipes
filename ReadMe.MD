# Delicious Recipes by Jinxia Du
## Overview
You can find your favorite recipes in this website and learn how to cook!

### Project Technical Overview:
This project involved Spring Framework.......(will update as I learn)
### Business Rules
    1. Objects: User, Recipe, Comment
    2. Relationships:
       1. One user could publish many recipes.
       2. One user could publish many comments.
       3. A recipe could have many comments.
       4. A recipe is published by one user.
       5. A comment is published by one user.
### Development Approaches
    1. Create User, Recipe, and Comment.
    2. Use Flyway to migrate database schema
    3. Use JDBC to connect to Postgres
    4.
    5.
    
## Configure local environment
### 1. setup local database
Refer to postgres docker [image](https://hub.docker.com/_/postgres) for environment option.
```
docker pull postgres
docker run --name training-db -e POSTGRES_USER=${username} -e POSTGRES_PASSWORD=${password} -e POSTGRES_DB=${databaseName} -p 5431:5432 -d postgres
```
### 2. migrate database schema
Refer to flyway setup [documentation](https://flywaydb.org/documentation/), find all [migration schema](/src/main/resources/db/migration)
```
mvn clean compile flyway:migrate
```
