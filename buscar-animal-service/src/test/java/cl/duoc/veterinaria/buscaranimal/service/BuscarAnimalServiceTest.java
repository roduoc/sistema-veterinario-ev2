package cl.duoc.veterinaria.buscaranimal.service;

import cl.duoc.veterinaria.domain.model.Animal;
import cl.duoc.veterinaria.domain.repository.AnimalRepository;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BuscarAnimalServiceTest {

    @Test
    void ejecutarRetornaAnimalCuandoExiste() {
        Animal animal = new Animal();
        animal.setId(1L);
        animal.setNombre("Toby");
        AnimalRepository repository = mock(AnimalRepository.class);
        when(repository.findById(1L)).thenReturn(Optional.of(animal));

        BuscarAnimalService service = new BuscarAnimalService(repository);
        Animal resultado = service.ejecutar(1L);

        assertEquals("Toby", resultado.getNombre());
    }

    @Test
    void ejecutarLanzaErrorCuandoNoExiste() {
        AnimalRepository repository = mock(AnimalRepository.class);
        when(repository.findById(99L)).thenReturn(Optional.empty());

        BuscarAnimalService service = new BuscarAnimalService(repository);

        assertThrows(NoSuchElementException.class, () -> service.ejecutar(99L));
    }
}
