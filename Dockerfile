####
# Multi-stage build: compiles and tests the application inside Docker, so no local JDK or Maven is needed.
#
# Build the image. The build stage runs all unit and @QuarkusTest tests whenever pom.xml or src/ changed,
# and a failing test fails the build; on unchanged sources the layer is cached, so add --no-cache to re-run them:
#
# docker build -t math-formulas:v1.1 .
#
# Then run the container (the application listens on quarkus.http.port=8888, see application.properties):
#
# docker run -i --rm -p 8888:8888 math-formulas:v1.1
#
# Swagger UI: http://localhost:8888/q/swagger-ui   Health: http://localhost:8888/q/health
#
# The runtime stage is the same as src/main/docker/Dockerfile.jvm, which expects a local ./mvnw package first.
###

## Stage 1: build and test with Maven
FROM maven:3.9.16-eclipse-temurin-21 AS build
WORKDIR /build
COPY pom.xml .
COPY src ./src
# The cache mount keeps the local Maven repository between builds, so dependencies are downloaded once
RUN --mount=type=cache,target=/root/.m2 mvn -B -ntp package

## Stage 2: runtime image
FROM registry.access.redhat.com/ubi8/openjdk-21:1.23-4

ENV LANGUAGE='en_US:en'

# We make four distinct layers so if there are application changes the library layers can be re-used
COPY --from=build --chown=185 /build/target/quarkus-app/lib/ /deployments/lib/
COPY --from=build --chown=185 /build/target/quarkus-app/*.jar /deployments/
COPY --from=build --chown=185 /build/target/quarkus-app/app/ /deployments/app/
COPY --from=build --chown=185 /build/target/quarkus-app/quarkus/ /deployments/quarkus/

EXPOSE 8888
USER 185
ENV JAVA_OPTS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV JAVA_APP_JAR="/deployments/quarkus-run.jar"
