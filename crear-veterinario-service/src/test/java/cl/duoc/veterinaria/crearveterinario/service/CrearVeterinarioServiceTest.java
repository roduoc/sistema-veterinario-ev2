package cl.duoc.veterinaria.crearveterinario.service;

import cl.duoc.veterinaria.domain.model.Veterinario;
import cl.duoc.veterinaria.domain.repository.VeterinarioRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CrearVeterinarioServiceTest {

    @Test
    void ejecutarGuardaVeterinarioValido() {
        VeterinarioRepository repository = mock(VeterinarioRepository.class);
        when(repository.save(any(Veterinario.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Veterinario veterinario = new Veterinario();
        veterinario.setNombre("Dra. Paz");
        veterinario.setEspecialidad("Cirugia");

        CrearVeterinarioService service = new CrearVeterinarioService(repository);
        Veterinario resultado = service.ejecutar(veterinario);

        assertEquals("Cirugia", resultado.getEspecialidad());
    }

    @Test
    void ejecutarRechazaVeterinarioSinEspecialidad() {
        CrearVeterinarioService service = new CrearVeterinarioService(mock(VeterinarioRepository.class));
        Veterinario veterinario = new Veterinario();
        veterinario.setNombre("Dra. Paz");

        assertThrows(IllegalArgumentException.class, () -> service.ejecutar(veterinario));
    }
}
