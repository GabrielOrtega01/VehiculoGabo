FROM openjdk:17


COPY "./target/MongoVehiculoGabo-1.jar" "app.jar"
EXPOSE 8060
ENTRYPOINT [ "java", "-jar", "app.jar" ]
