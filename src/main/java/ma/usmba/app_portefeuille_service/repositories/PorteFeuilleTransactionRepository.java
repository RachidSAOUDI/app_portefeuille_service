package ma.usmba.app_portefeuille_service.repositories;

import ma.usmba.app_portefeuille_service.entities.PorteFeuilleTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PorteFeuilleTransactionRepository extends JpaRepository<PorteFeuilleTransaction, Long> {
}
