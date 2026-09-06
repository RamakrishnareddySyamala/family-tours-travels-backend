FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

EXPOSE 8082

CMD ["java", "-jar", "target/family-tours-travels-0.0.1-SNAPSHOT.jar"]