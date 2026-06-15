package cl.duoc.veterinaria.creardueno.service;

import cl.duoc.veterinaria.domain.model.Dueno;
import cl.duoc.veterinaria.domain.repository.DuenoRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CrearDuenoServiceTest {

    @Test
    void ejecutarGuardaDuenoValido() {
        DuenoRepository repository = mock(DuenoRepository.class);
        when(repository.save(any(Dueno.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Dueno dueno = new Dueno();
        dueno.setNombre("Ana");
        dueno.setTelefono("999999999");

        CrearDuenoService service = new CrearDuenoService(repository);
        Dueno resultado = service.ejecutar(dueno);

        assertEquals("Ana", resultado.getNombre());
    }

    @Test
    void ejecutarRechazaDuenoSinTelefono() {
        CrearDuenoService service = new CrearDuenoService(mock(DuenoRepository.class));
        Dueno dueno = new Dueno();
        dueno.setNombre("Ana");

        assertThrows(IllegalArgumentException.class, () -> service.ejecutar(dueno));
    }
}
