package online.pubudu.springstarter.integration.database.repository;

import online.pubudu.springstarter.integration.database.document.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

// No need to annotate here
// https://o7planning.org/en/11773/spring-boot-and-mongodb-tutorial
// Good MongoDB Tutorial
// https://www.mkyong.com/spring-boot/spring-boot-spring-data-mongodb-example/
public interface UserRepository extends MongoRepository<User, String> {

    public User findByFirstName(String firstName);
    public List<User> findByLastName(String lastName);

}