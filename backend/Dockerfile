FROM openjdk:8-jdk-alpine
MAINTAINER Maciej Łotysz <maciej.lotysz@intive.com>
RUN mkdir build
ADD pom.xml mvnw build/
# copy directory never been easier
ADD .mvn build/.mvn/
WORKDIR build
ENV JAVA_OPTS="-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap"
RUN chmod 0700 mvnw && ./mvnw verify || true
# copy directory never been easier 2
ADD src/ ../build/src/
RUN ./mvnw package
