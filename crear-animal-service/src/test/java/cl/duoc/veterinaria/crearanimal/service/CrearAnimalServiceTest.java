package cl.duoc.veterinaria.crearanimal.service;

import cl.duoc.veterinaria.domain.model.Animal;
import cl.duoc.veterinaria.domain.model.Dueno;
import cl.duoc.veterinaria.domain.model.Veterinario;
import cl.duoc.veterinaria.domain.repository.AnimalRepository;
import cl.duoc.veterinaria.domain.repository.DuenoRepository;
import cl.duoc.veterinaria.domain.repository.VeterinarioRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CrearAnimalServiceTest {

    @Test
    void ejecutarCreaAnimalConRelacionesYValoresPorDefecto() {
        AnimalRepository animalRepository = mock(AnimalRepository.class);
        DuenoRepository duenoRepository = mock(DuenoRepository.class);
        VeterinarioRepository veterinarioRepository = mock(VeterinarioRepository.class);

        Dueno dueno = new Dueno();
        dueno.setId(1L);
        Veterinario veterinario = new Veterinario();
        veterinario.setId(2L);
        when(duenoRepository.findById(1L)).thenReturn(Optional.of(dueno));
        when(veterinarioRepository.findById(2L)).thenReturn(Optional.of(veterinario));
        when(animalRepository.save(any(Animal.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Animal animal = new Animal();
        animal.setNombre("Milo");
        animal.setEspecie("Gato");

        CrearAnimalService service = new CrearAnimalService(animalRepository, duenoRepository, veterinarioRepository);
        Animal resultado = service.ejecutar(animal, 1L, 2L);

        assertEquals(dueno, resultado.getDueno());
        assertEquals(veterinario, resultado.getVeterinario());
        assertEquals("Pendiente de evaluacion", resultado.getEstadoSalud());
        assertNotNull(resultado.getFechaRegistro());
    }

    @Test
    void ejecutarRechazaAnimalSinNombre() {
        CrearAnimalService service = new CrearAnimalService(
                mock(AnimalRepository.class),
                mock(DuenoRepository.class),
                mock(VeterinarioRepository.class)
        );
        Animal animal = new Animal();
        animal.setEspecie("Perro");

        assertThrows(IllegalArgumentException.class, () -> service.ejecutar(animal, 1L, 2L));
    }
}
