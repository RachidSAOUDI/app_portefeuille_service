package ma.usmba.app_portefeuille_service.services;

import ma.usmba.app_portefeuille_service.entities.Devise;
import ma.usmba.app_portefeuille_service.entities.PorteFeuille;
import ma.usmba.app_portefeuille_service.entities.PorteFeuilleTransaction;
import ma.usmba.app_portefeuille_service.enums.TypeTransaction;
import ma.usmba.app_portefeuille_service.repositories.DeviseRepository;
import ma.usmba.app_portefeuille_service.repositories.PorteFeuilleRepository;
import ma.usmba.app_portefeuille_service.repositories.PorteFeuilleTransactionRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@Transactional
public class PorteFeuilleService {
    private DeviseRepository deviseRepository;
    private PorteFeuilleRepository porteFeuilleRepository;
    private PorteFeuilleTransactionRepository porteFeuilleTransactionRepository;

    public PorteFeuilleService(DeviseRepository deviseRepository, PorteFeuilleRepository porteFeuilleRepository, PorteFeuilleTransactionRepository porteFeuilleTransactionRepository) {
        this.deviseRepository = deviseRepository;
        this.porteFeuilleRepository = porteFeuilleRepository;
        this.porteFeuilleTransactionRepository = porteFeuilleTransactionRepository;
    }


    public void loadData() throws IOException {
        URI uri=new ClassPathResource("devises.data.csv").getURI();
        Path path=Paths.get(uri);
        List<String> lines = Files.readAllLines(path);
        for (int i = 1; i < lines.size(); i++) {
            String[] line=lines.get(i).split(";");
            Devise devise= Devise.builder()
                    .code(line[0])
                    .nom(line[1])
                    .prixVente(Double.parseDouble(line[2]))
                    .prixAchat(Double.parseDouble(line[3]))
                    .build();
            deviseRepository.save(devise);
        }
        Stream.of("MAD","USD","EUR","CAD").forEach(codeDevise->{
            Devise devise=deviseRepository.findById(codeDevise)
                    .orElseThrow(()->new RuntimeException(String.format("Currency %s not found", codeDevise)));
            PorteFeuille porteFeuille=new PorteFeuille();
            porteFeuille.setSolde(12000.0);
            porteFeuille.setDevise(devise);
            porteFeuille.setDateCreation(System.currentTimeMillis());
            porteFeuille.setUserId("user1");
            porteFeuille.setId(UUID.randomUUID().toString());
            porteFeuilleRepository.save(porteFeuille);
        });
        porteFeuilleRepository.findAll().forEach(porteFeuille -> {
            for (int i = 0; i < 10; i++) {
                PorteFeuilleTransaction debitPorteFeuilleTransaction=PorteFeuilleTransaction.builder()
                        .montant(Math.random()*1000)
                        .porteFeuille(porteFeuille)
                        .type(TypeTransaction.DEBIT)
                        .timesTamp(System.currentTimeMillis())
                        .build();
                porteFeuilleTransactionRepository.save(debitPorteFeuilleTransaction);
                porteFeuille.setSolde(porteFeuille.getSolde() - debitPorteFeuilleTransaction.getMontant());
                PorteFeuilleTransaction creditPorteFeuilleTransaction=PorteFeuilleTransaction.builder()
                        .montant(Math.random()*1000)
                        .porteFeuille(porteFeuille)
                        .type(TypeTransaction.CREDIT)
                        .timesTamp(System.currentTimeMillis())
                        .build();
                porteFeuilleTransactionRepository.save(creditPorteFeuilleTransaction);
                porteFeuille.setSolde(porteFeuille.getSolde() + creditPorteFeuilleTransaction.getMontant());
                porteFeuilleRepository.save(porteFeuille);
            }
        });
    }
}
