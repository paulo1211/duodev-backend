FROM eclipse-temurin:19-jdk-jammy as builder
WORKDIR /opt/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x ./mvnw
RUN ./mvnw dependency:go-offline
COPY ./src ./src
COPY tokens ./tokens
RUN ./mvnw clean install

FROM eclipse-temurin:19-alpine
WORKDIR /opt/app
EXPOSE 8080
COPY --from=builder /opt/app/target/*.jar /opt/app/*.jar
COPY tokens ./tokens
CMD ["java", "-jar", "/opt/app/*.jar"]