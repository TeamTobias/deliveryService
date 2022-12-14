FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY build/libs/deliveryService-1.0.jar deliveryservice.jar
ENTRYPOINT ["java","-jar","/deliveryservice.jar"]