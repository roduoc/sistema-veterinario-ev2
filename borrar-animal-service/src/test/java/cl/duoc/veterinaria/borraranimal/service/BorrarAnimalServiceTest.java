package cl.duoc.veterinaria.borraranimal.service;

import cl.duoc.veterinaria.domain.model.Animal;
import cl.duoc.veterinaria.domain.repository.AnimalRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BorrarAnimalServiceTest {

    @Test
    void ejecutarEliminaAnimalExistente() {
        Animal animal = new Animal();
        animal.setId(1L);
        AnimalRepository repository = mock(AnimalRepository.class);
        when(repository.findById(1L)).thenReturn(Optional.of(animal));

        BorrarAnimalService service = new BorrarAnimalService(repository);
        service.ejecutar(1L);

        verify(repository).delete(animal);
    }
}
