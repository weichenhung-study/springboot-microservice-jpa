FROM openjdk:21-jdk

COPY target/springboot-microservice-jpa.jar .

EXPOSE 8080

ENTRYPOINT ["java","-jar","springboot-microservice-jpa.jar"]