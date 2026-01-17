#=====================
#--- 1. Build Stage
#=====================
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn -pl service-auth -am clean package -DskipTests

#=====================
#--- 2. Stage Runtime
#=====================
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/service-auth/target/*.jar app.jar

ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}
ENV SERVER_PORT=${AUTH_SERVICE_PORT}

EXPOSE ${AUTH_SERVICE_PORT}

ENTRYPOINT ["java", "-jar", "app.jar"]