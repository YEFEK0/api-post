# Etapa 1: Construcción (Empaqueta tu aplicación con Maven)
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (Corre la aplicación en un servidor ligero de Java)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Copia el archivo .jar que se generó en la etapa 1
COPY --from=build /app/target/api-post-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]