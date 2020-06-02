# Delicious Recipes by Jinxia Du
## Overview
You can find your favorite recipes in this website and learn how to cook!

### Project Technical Overview:
This project involved Spring Framework.......
* Business Rules
    1. Objects: User, Recipe, Comment
    2. Relationships:
       
       1.
       2.
       3.
    3.
* Development Approaches
    1.
    2.
    
## Configure local environment
### 1. setup local database
Refer to postgres docker [image](https://hub.docker.com/_/postgres) for environment option.
```
docker run --name training-db -e POSTGRES_USER=${username} -e POSTGRES_PASSWORD=${password} -e POSTGRES_DB=${databaseName} -p 5431:5432 -d postgres
```
### 2. migrate database schema
Refer to flyway setup [documentation](https://flywaydb.org/documentation/), find all [migration schema](/src/main/resources/db/migration)
```
mvn clean compile flyway:migrate
```
