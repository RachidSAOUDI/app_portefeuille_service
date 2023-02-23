package ma.usmba.app_portefeuille_service.repositories;

import ma.usmba.app_portefeuille_service.entities.Devise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviseRepository extends JpaRepository<Devise, String> {
}
