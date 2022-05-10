## Day 3

### Key takeaways
1. Decide on a database to use (RDBMS, NoSQL? In Memory? Cloud? etc.)
2. Decide on a testing infrastructure ([Docker](#docker)? In Memory? Locally installed)
3. Hibernate Annotations 
    1. Add [@Entity](https://docs.oracle.com/javaee/7/api/javax/persistence/Entity.html) annotations to the relevant domain objects.
    2. Add [relationship annotations](https://www.objectdb.com/api/java/jpa/annotations/relationship)
    3. Further notes below in [Hibernate section](#hibernate)
4. JPA Data
    1. Create repositories for classes that will be processed https://www.baeldung.com/spring-data-repositories
    2. Define any custom queries that you might need https://www.baeldung.com/the-persistence-layer-with-spring-data-jpa
5. Once you are satisfied, add versioning via [flyway](#flyway) or liquibase
    1. Decide on a solution based on:
        * flexibility needed (+1 for liquibase)
        * ease of use (+1 for flyway)
        * other criteria
    2. Dump existing DB
    3. Migrate it to one of the formats
    4. Add dependencies to the project POM
    5. Change ```spring.jpa.hibernate.ddl-auto=validate```
    6. Further notes below in [Flyway section](#flyway)

### Docker
#### Docker Installation
https://docs.docker.com/desktop/windows/install/
#### Docker compose tutorial
https://docs.docker.com/compose/gettingstarted/

To start the database from the docker file:
```docker compose down; docker compose up```

### How to choose the right database for your service? 
https://medium.com/wix-engineering/how-to-choose-the-right-database-for-your-service-97b1670c5632

## Hibernate
### Most important annotations
https://thorben-janssen.com/key-jpa-hibernate-annotations/

### Ways of mapping inheritance
The specific solution used is the one on paragraph 3, single table
https://www.baeldung.com/hibernate-inheritance

### Many to Many Relationships
https://www.baeldung.com/jpa-many-to-many

## Flyway
### Dumping the DB
Run the following command:
```
mkdir -P /path/to/project/src/resources/db/migration
docker exec -i pizzalab-postgres /bin/bash -c "PGPASSWORD=example  pg_dump --username user pizzalab" > /path/to/project/src/resources/db/migration/V1.0.1__Init.sql
```
### Add POM dependencies
```
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-core</artifactId>
    <version>8.5.10</version>
</dependency>
```
### Check GIT
Check commit  `25fd49f22` for further info. 
You may use github, command line or any other GUI.
#### Command line
`git diff 25fd49f22~ 25fd49f22`
### Github
Check it [here](https://github.com/javacamp2022/pizza-lab/commit/25fd49f22bde5eff31d8f120a6141513399ea1f7)

