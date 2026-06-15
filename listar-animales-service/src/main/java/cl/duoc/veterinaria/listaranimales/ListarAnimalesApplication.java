package cl.duoc.veterinaria.listaranimales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "cl.duoc.veterinaria")
@EntityScan("cl.duoc.veterinaria.domain.model")
@EnableJpaRepositories("cl.duoc.veterinaria.domain.repository")
public class ListarAnimalesApplication {
    public static void main(String[] args) {
        SpringApplication.run(ListarAnimalesApplication.class, args);
    }
}
