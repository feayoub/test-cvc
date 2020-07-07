# Transfer Scheduler System

This project consists of a cash transfer scheduling system.

I chose to build this application using the MVC architecture, because it is a simple, functional and easy to maintain architecture.

I used the strategy pattern to calculate the taxes and the factory to instantiate the right tax class to make it easy to maintain and scale.

The programming language used was Java 14.

## Deployment

To deploy this application you need to follow these steps:
- Install the JDK from this link https://www.oracle.com/java/technologies/javase/jdk14-archive-downloads.html
- Open the environment variables of the system and edit the "Path" variable
- Choose the directory you installed your JDK and add the bin folder
- Make sure that this Path is above any other JDK paths you could have in your system. (to make sure, you can put this path at the top of the list)
- Open the command prompt and go to the root directory of the project
- Run the commmand 'mvn clean package'
- Go to the target directory now and run the command 'java -jar scheduler-0.0.1-SNAPSHOT.jar'
- After finishing the lines in the command prompt go to localhost:8080 and use the application 

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
