# Transfer Scheduler System

This project consists of a cash transfer scheduling system.

I chose to build this application using the MVC architecture, because it is a simple, functional and easy to maintain architecture.

I used the strategy pattern to calculate the taxes and the factory to instantiate the right tax class to make it easy to maintain and scale.

The language used was Java 14.

## Deployment

To deploy this application you need to follow these steps:
- install the JDK from this link https://adoptopenjdk.net/releases.html?variant=openjdk14&jvmVariant=hotspot.
- open the command prompt and go to the root directory of the project
- run the commmand 'mvn clean package'
- go to the target directory now and run the command 'java -jar scheduler-0.0.1-SNAPSHOT.jar'

## Built With

* [SpringWebMvc](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/) - The web framework used
* [Thymeleaf](https://www.thymeleaf.org/documentation.html) - Template engine framework
* [SpringDataJPA](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/) - Spring Data JPA repository
* [H2 Database](https://www.h2database.com/html/main.html) - H2 Database (in-memory)
* [ModelMapper](http://modelmapper.org/) - Object mapping
* [HibernateValidator](https://hibernate.org/validator/documentation/) - Validation by annotations
* [ApacheCommonsLangAPI](https://commons.apache.org/proper/commons-lang/javadocs/api-2.6/) - ToString and Equals Builder
* [JUnit](https://junit.org/junit5/docs/current/user-guide/) - Unit tests
* [Maven](https://maven.apache.org/) - Dependency Management


## Authors

* **Felipe Ayoub Zanini** - (https://github.com/feayoub)