package online.pubudu.springstarter.integration.database.repository;

import online.pubudu.springstarter.integration.database.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Employee Repository for {@link online.pubudu.springstarter.integration.database.entity.Employee}.
 * @author pubudu welagedara
 * @see <a href="http://pubudu.online">pubudu.online</a>
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
