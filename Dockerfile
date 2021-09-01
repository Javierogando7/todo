FROM maven:3-adoptopenjdk-11-openj9 AS build  
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml clean package

FROM gcr.io/distroless/java  
COPY --from=build /usr/src/app/target/todo-1.0.0-SNAPSHOT.jar /usr/app/todo-1.0.0-SNAPSHOT.jar  
EXPOSE 8080  
ENTRYPOINT ["java","-jar","/usr/app/todo-1.0.0-SNAPSHOT.jar"]  