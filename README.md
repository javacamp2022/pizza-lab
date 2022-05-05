# pizza-lab

## Schedule
### Day 1
- Welcome + Intro
- Initial Setup
- Java Recap

### Day 2
- Intro to Spring Boot and Web Services
- Building REST APIs using Spring

### Day 3
- Adding a database to the Spring Boot application
- Hibernate, JPA, HQL/JPQL

### Day 4
- Building the Service Layer 
- Design patterns
- Http client

### Day 5
- Testing your application
- Unit tests, Mockito, integration/e2e tests

### Day 6
- Deploying in containers
- Docker, Logs

### Day 7

- Deep dive into Web Services Suggestions
- Best practices/ Spring Security
- Cloud

### Day 8
**DEMO DAY**




## Day 1
### Initial setup

- Download and install [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows)
- Create a new GitHub repository using the following [tutorial](https://docs.github.com/en/get-started/quickstart/create-a-repo). If you don't have a github account yet, please create one using your
  personal email address.
- Generate a new SSH key and add it to GITHUB [tutorial](https://docs.github.com/en/authentication/connecting-to-github-with-ssh/adding-a-new-ssh-key-to-your-github-account)
- Configure Java 1.8 [tutorial](https://www.jetbrains.com/help/idea/sdk.html#change-module-sdk)
- Create new Maven project in your repository: use https://github.com/javacamp2022/pizza-lab as reference
- Add lombok plugin [tutorial](https://projectlombok.org/setup/intellij)
- [shortcuts intellij](https://cheatography.com/dmop/cheat-sheets/intellij-idea/)
- Short tutorial Streams (map, filter) [more...](https://www.baeldung.com/java-8-streams)
- Short tutorial design pattern (Builder, Singleton)[more...](https://sourcemaking.com/design_patterns/creational_patterns)

- Create at least 8 domain objects
- Create a dummy repository(storage) that stores in memory a few collections of your objects. The storage should be initialized at startup.
- Create at least 5 operations to retrieve and add new objects to the dummy storage
- Create a main class that will call the 5 operations

Sugestii proiecte:

- structura unei organizații (angajați, relații ierarhice, salarii)
- o agendă personală (categorii, întâlniri, sarcini)
- activitatea unei companii de transport (orașe, legături, mașini, rute)
- credite (client, credit, rate)
- cabinet medical (pacienți, medici, rețete)
- admitere (candidat, facultate, examen)
- vanzare de bilete online(client, eveniment, locatie)
- software casa de marcat(metoda de plata, client, produs)
- rezervare loc în sala de spectacol (spectacol, loc, client)
