package online.pubudu.springstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/*
* Created by pubudu welagedara on 12/17/18.
* */
// https://stackoverflow.com/questions/36387265/disable-all-database-related-auto-configuration-in-spring-boot
// https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/html/#packaging-executable-configuring-main-class
// https://stackoverflow.com/questions/36917530/gradle-error-with-lack-of-mainclassname-property-in-gradle-build
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class SpringStarterApplicationMongoDB {

	public static void main(String[] args) {
		SpringApplication.run(SpringStarterApplicationMongoDB.class, args);
	}

}

