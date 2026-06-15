package cl.duoc.veterinaria.domain.repository;

import cl.duoc.veterinaria.domain.model.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
}
