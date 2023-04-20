FROM openjdk
WORKDIR usr/lib
ADD ./target/payment-service-0.0.1-SNAPSHOT.jar /usr/lib/payment-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","payment-service-0.0.1-SNAPSHOT.jar"]