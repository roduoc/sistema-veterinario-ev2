package cl.duoc.veterinaria.listarduenos.service;

import cl.duoc.veterinaria.domain.model.Dueno;
import cl.duoc.veterinaria.domain.repository.DuenoRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListarDuenosServiceTest {

    @Test
    void ejecutarRetornaDuenosRegistrados() {
        Dueno dueno = new Dueno();
        dueno.setNombre("Carlos");
        DuenoRepository repository = mock(DuenoRepository.class);
        when(repository.findAll()).thenReturn(List.of(dueno));

        ListarDuenosService service = new ListarDuenosService(repository);
        List<Dueno> resultado = service.ejecutar();

        assertEquals(1, resultado.size());
        assertEquals("Carlos", resultado.get(0).getNombre());
    }
}
