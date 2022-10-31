#FROM openjdk:11
#WORKDIR /app
#EXPOSE 8080
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#RUN ./mvnw dependency:go-offline
#COPY src ./src
#CMD ["./mvnw", "spring-boot:run"]

FROM openjdk:11
EXPOSE 8080
ADD target/amaris-0.0.1-SNAPSHOT.jar amaris-training.jar
ENTRYPOINT ["java", "-jar", "/amaris-training.jar"]