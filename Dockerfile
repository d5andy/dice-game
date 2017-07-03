FROM openjdk:8u121-jdk-alpine

EXPOSE 8080

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

COPY . /usr/src/app

RUN ./gradlew build

EXPOSE 8080

CMD [ "java", "-jar", "/usr/src/app/build/libs/dice-game-0.1.0.jar" ]

