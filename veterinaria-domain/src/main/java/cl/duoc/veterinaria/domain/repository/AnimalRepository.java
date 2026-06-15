package cl.duoc.veterinaria.domain.repository;

import cl.duoc.veterinaria.domain.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
