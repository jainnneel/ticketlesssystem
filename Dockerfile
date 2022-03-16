FROM openjdk:8
EXPOSE 8080
ADD target/ticketless.jar ticketless.jar
ENTRYPOINT ["java","-jar","/ticketless.jar"]