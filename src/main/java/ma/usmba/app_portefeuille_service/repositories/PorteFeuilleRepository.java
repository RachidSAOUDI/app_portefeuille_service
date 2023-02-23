package ma.usmba.app_portefeuille_service.repositories;

import ma.usmba.app_portefeuille_service.entities.PorteFeuille;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PorteFeuilleRepository extends JpaRepository<PorteFeuille, String> {
}
