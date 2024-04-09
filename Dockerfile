
FROM maven:3.8.4-openjdk-17-slim AS maven
WORKDIR /maven
COPY pom.xml .
RUN mvn dependency:go-offline

FROM maven AS build
WORKDIR /src
COPY . .
RUN mvn clean package -DskipTests


FROM openjdk:17-jdk-alpine AS final
WORKDIR /app
COPY --from=build /src/target/*.jar app.jar
RUN addgroup -g 2000 webadmin
RUN adduser -D -u 2000 -G webadmin webadmin
RUN chown -R 2000:2000 /app/app.jar
USER webadmin
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "app.jar"]
