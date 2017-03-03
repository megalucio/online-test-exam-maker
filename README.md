# online-test-exam-maker
Demo project for Online Test Exam Maker

### Tools and Technologies ###

- Java 8
- Maven 3.3.9
- Spring Boot 1.5.1
- Hibernate 5.0.11
- H2 1.4 
- Thymeleaf 2.1.5

### Assumptions Made ###

Passwords are stored in plain text and only http is used.

Despite the fact that mysql and tomcat were proposed I decided to use spring-boot and H2 in memory database instead in order to simplify the deployment process.

Using thymeleaf templates as the view rendering technology.

The test data provided only includes one exam therefore all attempts will be refered to the very same exam. However the design allows for more than one exam, you will only have to include a select in the index.html in order to choose from the different exams and do a forward with the appropriate exam id in order to accomplish this, however for simplicity reasons and considering this wasn't included in the requirements I decided not to include this.

### Deployment ###

All you need to do in order to deploy the application is execute the following:

```sh
./mvnw spring-boot:run
```

This command will download all required dependencies and also initialize server and in memory DB accordingly so there is no need for any extra steps. Then you can access the application in http://localhost:8080/ to start a new exam.

The default username/password is candidate/no1knows.