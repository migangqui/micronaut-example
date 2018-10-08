# Micronaut Framework + Kotlin + Mongo example

This is an POC using microservice oriented JVM framework named Micronaut, that supports
differents languages as Kotlin, used in this case. Also it uses MongoDB as datasource.
In this example Maven is the dependency manager, but it is posible use Gradle.
Take a look Micronaut documentation: https://docs.micronaut.io/latest/guide/index.html

## To run
In order to run this project is necesary set up Mongo database and run the project:
1. Use docker to run the database `docker run -p 27017:27017 -m some-mongo bitnami/mongodb:latest`
2. Compile the project `mvn clean -U install`
3. Run maven command `./mvnw exec:exec`

## Misc
This branch is implemented with asynchronous Mongo driver.