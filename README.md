# NetCracker project


[![Build Status](https://travis-ci.org/NetCracker-2017-Team/project.svg?branch=master)]()

# Building
Build as follow
```
mvn clean install 
```
By default integration tests won't run but you can explicitly define that you want to run only unit tests
```
mvn clean install -P unit
```
To enable only integration tests you have to define database connection environment variables:
```
JDBC_DATABASE_URL=jdbc:postgresql://localhost:5432/yourdb;
JDBC_DATABASE_USERNAME=username
JDBC_DATABASE_PASSWORD=password
```
And run it as follow
```
mvn clean install -P integration
```
To run all tests during building: (in this case you also have to define environment variables as in previous section)
```
mvn clean install -P full
```
# Running
Once application is built

Run it as follow if you want to use embedded database
```
java -jar service/target/service.jar
```
If you want to use postgres database, you should define the following environment variables and run it as in previous section
```
SPRING_PROFILES_ACTIVE=prod
JDBC_DATABASE_URL=jdbc:postgresql://localhost:5432/yourdb;
JDBC_DATABASE_USERNAME=username
JDBC_DATABASE_PASSWORD=password
```
