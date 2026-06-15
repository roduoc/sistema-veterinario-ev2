package cl.duoc.veterinaria.asignarveterinario.service;

import cl.duoc.veterinaria.domain.model.Animal;
import cl.duoc.veterinaria.domain.model.Veterinario;
import cl.duoc.veterinaria.domain.repository.AnimalRepository;
import cl.duoc.veterinaria.domain.repository.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.Duration;
import java.util.NoSuchElementException;

@Service
public class AsignarVeterinarioService {
    private final AnimalRepository animalRepository;
    private final VeterinarioRepository veterinarioRepository;
    private final WebClient webClient;
    private final String buscarAnimalUrl;

    public AsignarVeterinarioService(
            AnimalRepository animalRepository,
            VeterinarioRepository veterinarioRepository,
            WebClient.Builder webClientBuilder,
            @Value("${services.buscar-animal.url}") String buscarAnimalUrl
    ) {
        this.animalRepository = animalRepository;
        this.veterinarioRepository = veterinarioRepository;
        this.webClient = webClientBuilder.build();
        this.buscarAnimalUrl = buscarAnimalUrl;
    }

    public Animal ejecutar(Long animalId, Long veterinarioId) {
        validarAnimalRemoto(animalId);

        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new NoSuchElementException("No existe un animal con id " + animalId));
        Veterinario veterinario = veterinarioRepository.findById(veterinarioId)
                .orElseThrow(() -> new NoSuchElementException("No existe un veterinario con id " + veterinarioId));

        animal.setVeterinario(veterinario);
        return animalRepository.save(animal);
    }

    private void validarAnimalRemoto(Long animalId) {
        try {
            webClient.get()
                    .uri(buscarAnimalUrl + "/buscar-animal?id={id}", animalId)
                    .retrieve()
                    .bodyToMono(Animal.class)
                    .block(Duration.ofSeconds(3));
        } catch (WebClientResponseException.NotFound ex) {
            throw new NoSuchElementException("No existe un animal con id " + animalId);
        } catch (RuntimeException ex) {
            throw new IllegalStateException("No fue posible validar el animal en buscar-animal-service", ex);
        }
    }
}
