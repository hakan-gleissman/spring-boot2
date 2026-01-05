# kommentar
FROM eclipse-temurin:17-jre

WORKDIR /app
# ändra target/app.jar till target/<namnet_på_din_jar_fil>
COPY target/spring-boot2-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]