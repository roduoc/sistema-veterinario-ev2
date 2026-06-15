package cl.duoc.veterinaria.actualizaranimal.service;

import cl.duoc.veterinaria.domain.model.Animal;
import cl.duoc.veterinaria.domain.repository.AnimalRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ActualizarAnimalServiceTest {

    @Test
    void ejecutarActualizaDatosDelAnimal() {
        AnimalRepository repository = mock(AnimalRepository.class);
        Animal existente = new Animal();
        existente.setId(1L);
        existente.setNombre("Nombre antiguo");
        when(repository.findById(1L)).thenReturn(Optional.of(existente));
        when(repository.save(any(Animal.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Animal datos = new Animal();
        datos.setNombre("Nombre nuevo");
        datos.setEspecie("Perro");
        datos.setEdad(4);

        ActualizarAnimalService service = new ActualizarAnimalService(repository);
        Animal resultado = service.ejecutar(1L, datos);

        assertEquals("Nombre nuevo", resultado.getNombre());
        assertEquals("Perro", resultado.getEspecie());
        assertEquals(4, resultado.getEdad());
    }
}
