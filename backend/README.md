[![Build Status](https://travis-ci.org/blstream/ShopMe_Backend.svg?branch=master)](https://travis-ci.org/blstream/ShopMe_Backend)

# ShopMe by Intive Patronage '18

ShopMe is a Web Application created during Intive Patronage `18 Project.
This application will allow you to buy, offer and sell widely defined services.

## Requirements
* [Java JDK 10](http://www.oracle.com/technetwork/java/javase/downloads/index.html)

## Build and run

* Build
```
mvnw clean install
```

* Run
```
mvnw spring-boot:run
```

* Run with specific profile

```
java -jar -Dspring.profiles.active={dev,stage,production} target/be.jar
```

## Swagger API documentation

* http://localhost:8080/swagger-ui.html
* http://localhost:8080/v2/api-docs

## More information:
* [CodeStyle](https://github.com/blstream/ShopMe_Backend/blob/master/backend/doc/CodeStyle.md)
