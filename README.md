**spring-cache-demo**

This application demonstrates the use the caching with spring-boot-starter-cache

**Redis** is used as underlying cache with expiration time of 30 secs

CRUD operation is performed on Domain Model <a href="src/main/java/com/github/mitrakumarsujan/springcachedemo/model/Book.java">Book</a>

To Run
1. Clone the project
2. Specify redis configuration in <a href="src/main/resources/application-dev.yml">application-dev.yml</a>
3. Run `mvnw spring-boot:run` or start the <a href="src/main/java/com/github/mitrakumarsujan/springcachedemo/SpringCacheDemoApplication.java">main</a> class from your IDE

To Test
1. Clone the project
2. Run `mvnw clean test` on project root directory