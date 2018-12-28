package online.pubudu.springstarter.integration.database.repository;

import online.pubudu.springstarter.integration.database.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
