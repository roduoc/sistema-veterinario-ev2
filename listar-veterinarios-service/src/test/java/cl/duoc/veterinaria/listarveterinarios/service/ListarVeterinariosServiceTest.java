package cl.duoc.veterinaria.listarveterinarios.service;

import cl.duoc.veterinaria.domain.model.Veterinario;
import cl.duoc.veterinaria.domain.repository.VeterinarioRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListarVeterinariosServiceTest {

    @Test
    void ejecutarRetornaVeterinariosRegistrados() {
        Veterinario veterinario = new Veterinario();
        veterinario.setNombre("Dr. Rojas");
        VeterinarioRepository repository = mock(VeterinarioRepository.class);
        when(repository.findAll()).thenReturn(List.of(veterinario));

        ListarVeterinariosService service = new ListarVeterinariosService(repository);
        List<Veterinario> resultado = service.ejecutar();

        assertEquals(1, resultado.size());
        assertEquals("Dr. Rojas", resultado.get(0).getNombre());
    }
}
