FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -DskipTests package

FROM eclipse-temurin:17-jre
ENV JAVA_OPTS="-Xms256m -Xmx512m"
WORKDIR /app

COPY src/main/resources/normalization /app/normalization

COPY --from=build /app/target/spell-check-api.jar app.jar
EXPOSE 6001
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar app.jar"]
