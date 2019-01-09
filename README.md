# spring-starter

Having multiple classes with @SpringBootApplication is a bad idea. You will not do that in an actual application. I have done that just as a learning exercise. This implementation is in all_in_one branch.

## Running the project with Environment Variables

```bash
SPRING_PROFILES_ACTIVE="h2,apikey" ./gradlew bootRun
```
Or build the project and run it

```bash
SPRING_PROFILES_ACTIVE="h2,apikey" java -jar ./build/libs/spring-starter.jar
```

## Running the project with spring.profiles.active set

```bash
./gradlew bootRun -Dspring.profiles.active=mongodb,apikey
```

## Building the project

To build the project disable the tests( only in all_in_one branch)

```bash
./gradlew clean build -x test
```

## JavaDocs 

Go to Tools -> Generate JavaDocs in IntelliJ to generate JavaDocs.

https://www.oracle.com/technetwork/java/javase/tech/index-137868.html

https://www.oracle.com/technetwork/java/javase/documentation/index-137868.html#examples

https://docs.oracle.com/javase/7/docs/technotes/tools/windows/javadoc.html#see

https://www.baeldung.com/javadoc

https://www.jetbrains.com/help/idea/working-with-code-documentation.html
