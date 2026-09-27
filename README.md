# Flight shortest path application

### Run tests
* mvn test
* mvn -Dtest=FlightRouteServiceImplTest test

### Run app
* mvn spring-boot:run

### DB viewer

* For database use browser: http://localhost:8080/h2-console/
* Find the jdbc url in the app log:
* INFO 813743 --- [flight-shortest-path] [  restartedMain] org.flywaydb.core.FlywayExecutor         : Database: jdbc:h2:mem:4e41864c-30f7-4c5c-a265-20a59a52e72b
