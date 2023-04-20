FROM openjdk
WORKDIR usr/lib
ADD ./target/MovieEventService-0.0.1-SNAPSHOT.jar /usr/lib/MovieEventService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","MovieEventService-0.0.1-SNAPSHOT.jar"]


FROM openjdk:11
COPY target/MovieEventService-0.0.1-SNAPSHOT.jar MovieEventService-0.0.1-SNAPSHOT.jar
COPY . .
CMD ["java", "-jar", "MovieEventService-0.0.1-SNAPSHOT.jar"]
