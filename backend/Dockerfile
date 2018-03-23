FROM maven:alpine
MAINTAINER Maciej Łotysz <maciej.lotysz@intive.com>

ENV JAVA_OPTS="-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap"
COPY pom.xml /tmp/pom.xml
RUN mvn -B -f /tmp/pom.xml -s /usr/share/maven/ref/settings-docker.xml dependency:resolve

RUN mkdir /build
WORKDIR /build
COPY . .
RUN mvn -s /usr/share/maven/ref/settings-docker.xml package

VOLUME /build
