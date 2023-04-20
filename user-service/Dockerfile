FROM openjdk
WORKDIR usr/lib
ADD ./target/user-service-0.0.1-SNAPSHOT.jar /usr/lib/user-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","user-service-0.0.1-SNAPSHOT.jar"]