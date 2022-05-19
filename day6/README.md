## Day 6

### Building the code
We are using Maven as our build tool. Our goal is to run the tests and other plugins whenever building the project; more than this we want the application jar that is generated to be fully executable (have included the tomcat spring-boot runnables).

There are a couple of options to configure maven to build the uber-jar (shaded, fat) and include all of the dependencies.
https://www.baeldung.com/deployable-fat-jar-spring-boot
https://www.baeldung.com/spring-boot-run-maven-vs-executable-jar

The option we chose was to add a plugin in the pom.xml that will configure a spring-boot plugin that is used via the repackage goal to generate the jar.
```xml

            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <executions>
                    <execution>
                        <configuration>
                            <mainClass>pizzalab.Main</mainClass>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
```


```bash
$ mvn clean package spring-boot:repackage
```

If we now have the database running - we can simply start the jar;
```bash
$ java -jar target/pizza-lab-0.0.1-SNAPSHOT.jar
```

### Building the image
Given our executable jar, we want to containerize the app and prepare a Docker Image that can be executed and have all the needed Java and OS prerequisites:

The Dockerfile is build based on a openjdk-8 image; and our customization is only copying the previously build fat jar.
```dockerfile
FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
```

https://spring.io/guides/gs/spring-boot-docker/

```bash
$ docker build -t pizza-lab:latest .
```

### Running docker
We have a few options to run the Docker Image. One is to run the image directly; but we will need to override and config the app to use an H2 database.
Another option is to repurpose the docker-compose.yaml file to start the database and web service together.

https://stackoverflow.com/questions/64135291/how-to-connect-java-app-in-docker-container-with-database-in-another-container

```yaml
version: '3.1'

services:

  pizzalab:
    image: pizza-lab:v2
    environment:
      SPRING_PROFILES_ACTIVE: docker
    depends_on:
      - db
    ports:
      - "8080:8080"

  db:
    image: postgres
    container_name: pizzalab-postgres
    restart: always
    ports:
      - "8432:5432"
    expose:
      - 5432
    environment:
      POSTGRES_USER: user
      POSTGRES_PASSWORD: example
      POSTGRES_DB: pizzalab
    healthcheck:
      test: [ "CMD-SHELL", "pg_isready -d pizzalab -U user" ]
      interval: 30s
      timeout: 30s
      retries: 3

  adminer:
    image: adminer
    restart: always
    ports:
      - 9080:8080
```

Simply triggering the docker-compose commant we will startup the database, externally exposed on port 8432, along the application that will use spring-boot profile "docker".
```bash
docker-compose up
```

The spring-boot profile is linked with a tweaked properties file that knows to connect to the dockerized database as well;
```properties
spring.datasource.url=jdbc:postgresql://db:5432/pizzalab
```
