# ShopMe by Intive Patronage '18
ShopMe is a Web Application created during Intive Patronage `18 Project.
This application will allow you to buy, offer and sell widely defined services.
### Requirements
* [JAVA JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
### Build and run

* Build
```
mvnw clean install
```

* Run
```
mvnw spring-boot:run
```

*Run with specific profile (dev is default)
Available profiles are: dev, stage, production
```
$ java -jar -Dspring.profiles.active={profile name} {JAR name}.jar
```

### Swagger API documentation

* http://localhost:8080/swagger-ui.html
* http://localhost:8080/v2/api-docs
* [swagger.json](doc/swagger.json)

### More information:
* [CodeStyle](https://github.com/blstream/ShopMe_Backend/blob/master/backend/doc/CodeStyle.md)
