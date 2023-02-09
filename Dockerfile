FROM eclipse-temurin:17.0.4.1_1-jdk-jammy as builder
WORKDIR ../../opt/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY ./src ./src
RUN ./mvnw clean install

FROM eclipse-temurin:17.0.4.1_1-jre-jammy
WORKDIR /opt/app
COPY --from=builder /opt/app/target/*.jar /opt/app/*.jar
ENTRYPOINT ["java", "-jar", "/opt/app/*.jar" ]