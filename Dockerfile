FROM maven:3.8.2-openjdk-11

WORKDIR /gestao-festa
COPY . .
RUN mvn clean install

CMD mvn spring-boot:run
