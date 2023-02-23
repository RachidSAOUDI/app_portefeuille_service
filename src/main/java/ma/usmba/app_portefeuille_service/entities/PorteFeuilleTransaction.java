package ma.usmba.app_portefeuille_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.usmba.app_portefeuille_service.enums.TypeTransaction;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PorteFeuilleTransaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long timesTamp;
    private Double montant;
    @ManyToOne
    private PorteFeuille porteFeuille;
    @Enumerated(EnumType.STRING)
    private TypeTransaction type;
}
