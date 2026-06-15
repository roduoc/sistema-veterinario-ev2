package cl.duoc.veterinaria.borraranimal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "cl.duoc.veterinaria")
@EntityScan("cl.duoc.veterinaria.domain.model")
@EnableJpaRepositories("cl.duoc.veterinaria.domain.repository")
public class BorrarAnimalApplication {
    public static void main(String[] args) {
        SpringApplication.run(BorrarAnimalApplication.class, args);
    }
}
