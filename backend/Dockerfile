FROM maven:3-jdk-9-slim
MAINTAINER Maciej Łotysz <maciej.lotysz@intive.com>

WORKDIR /usr/src/app

ENV JAVA_OPTS="-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap"
COPY pom.xml .
# latest maven goal + ignore errors (cache deps)
RUN mvn -B -e -C -T 1C verify || true

COPY . .
# TODO surefile plugin is not downloaded, fix and add -o
RUN mvn -B -e -T 1C verify

FROM openjdk:9-jre-slim
MAINTAINER Maciej Łotysz <maciej.lotysz@intive.com>
COPY --from=0 /usr/src/app/target/be.jar app.jar
EXPOSE 8080
#https://medium.com/@cl4r1ty/docker-spring-boot-and-java-opts-ba381c818fa2#.bvcewql21
# TODO is this inherited? Yes -> remove
ENV JAVA_OPTS="-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap"
VOLUME /app.conf
ENTRYPOINT exec java $JAVA_OPTS \
-Djava.security.egd=file:/dev/./urandom \
-jar /app.jar
