package ma.usmba.app_portefeuille_service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PorteFeuille {
    @Id
    private String id;
    private Double solde;
    private Long dateCreation;
    private String userId;
    @ManyToOne
    private Devise devise;
    @OneToMany(mappedBy = "porteFeuille")
    private List<PorteFeuilleTransaction> porteFeuilleTransactions;
}
