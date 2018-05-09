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

## Authorization

To get token ```POST``` user credentials to ```/users/login```

Curl:
```
 curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{
       "email": "unknown@gmail.com",
       "password": "password"
     }' 'http://localhost:8080/login'
```

To authorize send JWT token in Authorization header with Bearer prefix in request.

Curl:
```
curl -X GET --header 'Accept: application/json'
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9...
' 'http://localhost:8080/categories'
```

#### Swagger Authorization

To get token ```POST``` user credentials to ```/users/login```

Then go to ```Authorization``` by pressing ```Authorize``` button.

In value text box type ```Bearer ``` and paste token.
## More information:
* [CodeStyle](https://github.com/blstream/ShopMe_Backend/blob/master/backend/doc/CodeStyle.md)
