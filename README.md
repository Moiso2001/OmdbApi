
# Technical Test - Praecipio Moises Plata

I'd made this project following the requeriments of the technical test, here you will see an open REST service which request data from OmdbAPI and handle petitions to own PostgreSQL DB.



## Requirements

- Java Development Kit (JDK): Ensure JDK 17+ is installed and configured in your environment.

- Apache Maven: Make sure Maven is installed and configured.

- PostgreSQL JDBC Url, credentials  and general settings on application.properties

```bash
spring.datasource.url=YOUR_JDBC
spring.datasource.username=USERNAME
spring.datasource.password=PASSWORD
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
- Include the API Key on application.properties

```bash
omdb.api.key=API_KEY
```

## Run Locally

Clone the project

```bash
  git clone https://github.com/Moiso2001/
```

Go to the project directory

```bash
  cd becritic
```

Install dependencies

```bash
  mvn clean install
```

To run the tests on the app (Optional)

```bash
  mvn test
```

Start the server

```bash
  mvn spring-boot:run
```

Alternatively, you can generate the JAR file:


```bash
  java -jar target/becritic-0.0.1-SNAPSHOT.jar
```
