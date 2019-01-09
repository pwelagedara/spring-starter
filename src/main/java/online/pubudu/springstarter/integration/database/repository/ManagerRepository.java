package online.pubudu.springstarter.integration.database.repository;

import online.pubudu.springstarter.integration.database.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ManagerRepository Repository for {@link online.pubudu.springstarter.integration.database.entity.Manager}.
 * @author pubudu welagedara
 * @see <a href="http://pubudu.online">pubudu.online</a>
 */
@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
