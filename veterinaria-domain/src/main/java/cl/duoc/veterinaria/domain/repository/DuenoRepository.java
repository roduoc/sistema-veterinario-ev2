package cl.duoc.veterinaria.domain.repository;

import cl.duoc.veterinaria.domain.model.Dueno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DuenoRepository extends JpaRepository<Dueno, Long> {
}
