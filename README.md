# api-technical
API developed in spring boot on vehicle management


-Technologies Used
-Spring Boot 2.7
-Java 11
-Spring Data JPA
-PostgreSQL 16
-Maven

1-In application.yml file add credentials configuration to postgres database


spring.datasource.url=jdbc:postgresql://localhost:5433/automobile
spring.datasource.username=your_username
spring.datasource.password=your_password


2-execute the migrations on the sql that is in the following folder of the project repository
src/scripts/automobile_10072024.sql
psql -U postgres -p 5433 automobile < automobile_10072024.sql

You can change the database port, you just have to modify it in the application.yml file as well.


3-compiler
mvn clean install

4-ejecution
mvn spring-boot:run ó java -jar name_jar.java



5-The service runs on port 8080 and the swagger url is the following

http://localhost:8080/swagger-ui/index.html#/









