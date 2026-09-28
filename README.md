# Flight shortest path application

### Run tests
* mvn test
* mvn -Dtest=FlightRouteServiceImplTest test

### Run app
* mvn spring-boot:run

### Initialize the database over HTTP

The API replaces all airlines, cities, and flight routes with the supplied JSON dataset:

```bash
curl -X PUT http://localhost:8080/api/initial-data \
  -H 'Content-Type: application/json' \
  --data-binary @src/main/resources/sample-data.json
```

The request format is in `src/main/resources/sample-data.json`. Routes refer to airlines and cities by ID.

Flyway creates the schema on startup and inserts example rows by default. Disable only the sample rows with:

```bash
mvn spring-boot:run -Dspring-boot.run.arguments=--app.sample-data.enabled=false
```

This setting applies when Flyway runs the sample-data migration against a new database. Schema migrations remain enabled.

### DB viewer

* For database use browser: http://localhost:8080/h2-console/
* Find the jdbc url in the app log:
* INFO 813743 --- [flight-shortest-path] [  restartedMain] org.flywaydb.core.FlywayExecutor         : Database: jdbc:h2:mem:4e41864c-30f7-4c5c-a265-20a59a52e72b
