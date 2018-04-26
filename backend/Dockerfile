FROM maven:3-jdk-10-slim
LABEL autor="Maciej Łotysz <maciej.lotysz@intive.com>"

WORKDIR /usr/src/app

COPY pom.xml .
COPY ext/ ext/
# latest maven goal + ignore errors (cache deps)
RUN mvn -B -e -C -P docker verify > /dev/null 2>&1 || true

COPY . .
RUN mvn -B -e -C -P docker package

FROM openjdk:10-jre-slim

# wait-for-postgresql.sh requirement... Docker is a fun!
RUN apt-get update \
    && apt-get install -y --no-install-recommends postgresql-client \
    && rm -rf /var/lib/apt/lists/*

COPY --from=0 /usr/src/app/target/be.jar app.jar

ENTRYPOINT ["java", "--add-modules", "java.xml.bind"]
CMD ["-jar", "/app.jar"]

