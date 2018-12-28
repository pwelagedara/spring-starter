package online.pubudu.springstarter.integration.database.repository;

import online.pubudu.springstarter.integration.database.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
