package ma.usmba.app_portefeuille_service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class Devise {
    @Id
    private String code;
    private String nom;
    private String Symbole;
    private String prixVente;
    private String prixAchat;
}
