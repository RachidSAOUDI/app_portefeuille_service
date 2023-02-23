package ma.usmba.app_portefeuille_service;

import ma.usmba.app_portefeuille_service.services.PorteFeuilleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppPortefeuilleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppPortefeuilleServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(PorteFeuilleService porteFeuilleService){
        return args -> {
            porteFeuilleService.loadData();
        };
    }
}
