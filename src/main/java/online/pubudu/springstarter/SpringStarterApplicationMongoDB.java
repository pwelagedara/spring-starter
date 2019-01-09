package online.pubudu.springstarter;

import online.pubudu.springstarter.integration.database.document.User;
import online.pubudu.springstarter.integration.database.mongorepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/*
* Created by pubudu welagedara on 12/17/18.
* */
// https://stackoverflow.com/questions/36387265/disable-all-database-related-auto-configuration-in-spring-boot
// https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/html/#packaging-executable-configuring-main-class
// https://stackoverflow.com/questions/36917530/gradle-error-with-lack-of-mainclassname-property-in-gradle-build
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
//@ComponentScan("online.pubudu.springstarter")
//@ComponentScan(basePackages = { "online.pubudu.springstarter"},
//		includeFilters = @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "online.pubudu.springstarter.integration.database.mongorepository.*")
//
//)
//@ComponentScan("online.pubudu.springstarter")
//@ComponentScan("online.pubudu.springstarter.integration.database.mongorepository")
//@ComponentScan(basePackages = { "online.pubudu.springstarter"},
//		excludeFilters = @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "online.pubudu.springstarter.integration.database.repository.*"))
//@EnableMongoRepositories(basePackages = "online.pubudu.springstarter.integration.database.mongorepository")
//@EnableMongoRepositories(basePackageClasses = UserRepository.class)
public class SpringStarterApplicationMongoDB implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringStarterApplicationMongoDB.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User("Thusitha", "Welagedara"));
	}

//	@Bean
//	public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory, MongoMappingContext context) {
//		MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), context);
//		converter.setTypeMapper(new DefaultMongoTypeMapper(null));
//
//		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory, converter);
//		return mongoTemplate;
//	}

}

