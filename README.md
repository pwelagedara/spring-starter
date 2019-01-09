# spring-starter

Having multiple classes with @SpringBootApplication is a bad idea. You will not do that in an actual application. I have done that just as a learning exercise.

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

To build the project disable the tests.

```bash
./gradlew clean build -x test
```

## Good MongoDB Tutorial

https://www.mkyong.com/spring-boot/spring-boot-spring-data-mongodb-example/

