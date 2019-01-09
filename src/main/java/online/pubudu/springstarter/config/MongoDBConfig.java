package online.pubudu.springstarter.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

//@Configuration
//@ComponentScan("online.pubudu.springstarter.integration.database.mongorepository")
//@EnableMongoRepositories(basePackages = "online.pubudu.springstarter.integration.database.mongorepository")
@Configuration
@EnableMongoRepositories(basePackages = "online.pubudu.springstarter.integration.database.mongorepository")
public class MongoDBConfig extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "springmongodb";
    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient("127.0.0.1", 27017);
    }

}
