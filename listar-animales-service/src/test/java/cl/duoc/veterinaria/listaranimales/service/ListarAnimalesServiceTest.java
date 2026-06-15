package cl.duoc.veterinaria.listaranimales.service;

import cl.duoc.veterinaria.domain.model.Animal;
import cl.duoc.veterinaria.domain.repository.AnimalRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListarAnimalesServiceTest {

    @Test
    void ejecutarRetornaAnimalesRegistrados() {
        Animal animal = new Animal();
        animal.setNombre("Luna");
        AnimalRepository repository = mock(AnimalRepository.class);
        when(repository.findAll()).thenReturn(List.of(animal));

        ListarAnimalesService service = new ListarAnimalesService(repository);
        List<Animal> resultado = service.ejecutar();

        assertEquals(1, resultado.size());
        assertEquals("Luna", resultado.get(0).getNombre());
    }
}
