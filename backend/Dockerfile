FROM openjdk:8-jdk-alpine
ADD target/cinema-0.0.1-SNAPSHOT.jar /cinema-0.0.1-SNAPSHOT.jar
CMD [ "java" , "-jar" , "/cinema-0.0.1-SNAPSHOT.jar" , "--spring.profiles.active = prod"]
EXPOSE 8080