FROM eclipse-temurin:21

WORKDIR /app

COPY .mvn/ .mvn

COPY mvnw pom.xml ./

RUN ./mvnw dependency:resolve

COPY src ./src

EXPOSE 8083

CMD ["./mvnw","spring-boot:run"]