# NetCracker project


[![Build Status](https://travis-ci.org/NetCracker-2017-Team/project.svg?branch=master)]()

# Building
Make sure that [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and [Maven 3](https://maven.apache.org/download.cgi) are installed

Build it as follow
```bash
mvn clean install 
```
By default integration tests won't run but you can explicitly define that you want to run only unit tests
```bash
mvn clean install -P unit
```
To enable only integration tests you have to define database connection environment variables:
```
JDBC_DATABASE_URL={for example: jdbc:postgresql://localhost:5432/yourdb}
JDBC_DATABASE_USERNAME={your username here}
JDBC_DATABASE_PASSWORD={your password here}
```
And run it as follow
```bash
mvn clean install -P integration
```
To run all tests during building: (in this case you also have to define environment variables as in previous section)
```bash
mvn clean install -P full
```
# Running
Once application is built

Run it as follow if you want to use embedded database
```bash
java -jar service/target/service.jar
```
If you want to use postgres database, you should define the following environment variables and run it as in previous section
```
SPRING_PROFILES_ACTIVE=prod
JDBC_DATABASE_URL={for example: jdbc:postgresql://localhost:5432/yourdb}
JDBC_DATABASE_USERNAME={your username here}
JDBC_DATABASE_PASSWORD={your password here}
```

# Continues Integration
[Travis CI](https://travis-ci.org "Travis CI web site")

#### Useful links
[Travis deployment to Heroku](https://docs.travis-ci.com/user/deployment/heroku/)

# Deployment
[Heroku](heroku.com "Heroku web site")

#### Useful links
[Getting started with Java in Heroku](https://devcenter.heroku.com/articles/getting-started-with-java#introduction)

[Spring Boot application Heroku deployment](https://docs.spring.io/spring-boot/docs/current/reference/html/cloud-deployment.html#cloud-deployment-heroku)

[Connection to relational database in Heroku](https://devcenter.heroku.com/articles/connecting-to-relational-databases-on-heroku-with-java)
# Technologies

## Web
[Spring Boot](https://projects.spring.io/spring-boot/ "Spring boot project")

[Spring Security](https://projects.spring.io/spring-security/ "Spring security project")

[Spring HATEOAS](https://projects.spring.io/spring-hateoas/ "Spring HATEOAS project") - [Hypermedia as the Engine of Application State (HATEOAS) is a constraint of the REST application architecture](https://en.wikipedia.org/wiki/HATEOAS "Wiki HATEOAS")

Spring Web MVC

#### Useful links
[Hypertext Application Language](http://stateless.co/hal_specification.html "HAL")

[Video about hypermedia (rus)](https://www.youtube.com/watch?v=G9apMqwRedA "YouTube video from JUG")

## Persistence
[Spring Data](https://projects.spring.io/spring-data/ "Spring data project") - easy using data access technologies

[Hibernate](http://hibernate.org "Hibernate web site") - JPA provider

[Postgesql](https://www.postgresql.org "Postgresql") - great open source relational database

[HSQLDB](http://hsqldb.org "HSQLDB") - in memory relational database (used for testing and run in dev mode)

### Testing
[Spring-Test-DBUnit](https://github.com/springtestdbunit/spring-test-dbunit "GitHub repository") - [provides integration between the Spring testing framework and the popular](https://springtestdbunit.github.io/spring-test-dbunit/ "Docs") [DBUnit project](http://dbunit.sourceforge.net "Docs") 

[log4jdbc](https://github.com/arthurblake/log4jdbc "GitHub repository") - logs sql queries
