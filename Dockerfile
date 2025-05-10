# Usa la imagen oficial de OpenJDK
FROM openjdk:17-jdk-slim

# Crea un directorio para la aplicación
WORKDIR /app

# Copia el jar construido (ajusta el nombre si es diferente)
COPY target/*.jar app.jar

# Expone el puerto (ajusta según el microservicio)
EXPOSE 8082

# Comando de ejecución
ENTRYPOINT ["java", "-jar", "app.jar"]