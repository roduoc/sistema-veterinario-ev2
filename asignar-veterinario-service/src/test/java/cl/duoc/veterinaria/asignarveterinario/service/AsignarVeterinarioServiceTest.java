package cl.duoc.veterinaria.asignarveterinario.service;

import cl.duoc.veterinaria.domain.model.Animal;
import cl.duoc.veterinaria.domain.model.Veterinario;
import cl.duoc.veterinaria.domain.repository.AnimalRepository;
import cl.duoc.veterinaria.domain.repository.VeterinarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AsignarVeterinarioServiceTest {

    @Test
    void ejecutarValidaAnimalRemotoYAsignaVeterinario() {
        Animal animal = new Animal();
        animal.setId(1L);
        Veterinario veterinario = new Veterinario();
        veterinario.setId(2L);

        AnimalRepository animalRepository = mock(AnimalRepository.class);
        VeterinarioRepository veterinarioRepository = mock(VeterinarioRepository.class);
        when(animalRepository.findById(1L)).thenReturn(Optional.of(animal));
        when(veterinarioRepository.findById(2L)).thenReturn(Optional.of(veterinario));
        when(animalRepository.save(any(Animal.class))).thenAnswer(invocation -> invocation.getArgument(0));

        WebClient.Builder webClientBuilder = WebClient.builder()
                .exchangeFunction(request -> Mono.just(
                        ClientResponse.create(HttpStatus.OK)
                                .header("Content-Type", "application/json")
                                .body("{\"id\":1,\"nombre\":\"Luna\",\"especie\":\"Gato\"}")
                                .build()
                ));

        AsignarVeterinarioService service = new AsignarVeterinarioService(
                animalRepository,
                veterinarioRepository,
                webClientBuilder,
                "http://buscar-animal-service"
        );
        Animal resultado = service.ejecutar(1L, 2L);

        assertEquals(veterinario, resultado.getVeterinario());
    }
}
