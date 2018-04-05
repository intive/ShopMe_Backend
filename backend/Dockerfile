FROM maven:3-jdk-10-slim
MAINTAINER Maciej Łotysz <maciej.lotysz@intive.com>

WORKDIR /usr/src/app

COPY pom.xml .
COPY ext/ ext/
# latest maven goal + ignore errors (cache deps)
RUN mvn -B -e -C verify || true

COPY . .
RUN mvn -B -e -C package|| true

FROM openjdk:10-jre-slim
COPY --from=0 /usr/src/app/target/be.jar app.jar
EXPOSE 8080
VOLUME /app.conf
ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar /app.jar
