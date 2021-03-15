FROM openjdk:15

COPY ./target/docker-spring-boot.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch docker-spring-boot.jar'

ENTRYPOINT ["java","-jar","docker-spring-boot.jar"]