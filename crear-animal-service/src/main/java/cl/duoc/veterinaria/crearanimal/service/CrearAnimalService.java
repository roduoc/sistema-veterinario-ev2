package cl.duoc.veterinaria.crearanimal.service;

import cl.duoc.veterinaria.domain.model.Animal;
import cl.duoc.veterinaria.domain.model.Dueno;
import cl.duoc.veterinaria.domain.model.Veterinario;
import cl.duoc.veterinaria.domain.repository.AnimalRepository;
import cl.duoc.veterinaria.domain.repository.DuenoRepository;
import cl.duoc.veterinaria.domain.repository.VeterinarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
public class CrearAnimalService {
    private final AnimalRepository animalRepository;
    private final DuenoRepository duenoRepository;
    private final VeterinarioRepository veterinarioRepository;

    public CrearAnimalService(
            AnimalRepository animalRepository,
            DuenoRepository duenoRepository,
            VeterinarioRepository veterinarioRepository
    ) {
        this.animalRepository = animalRepository;
        this.duenoRepository = duenoRepository;
        this.veterinarioRepository = veterinarioRepository;
    }

    public Animal ejecutar(Animal animal, Long duenoId, Long veterinarioId) {
        validarAnimal(animal);
        Dueno dueno = duenoRepository.findById(duenoId)
                .orElseThrow(() -> new NoSuchElementException("No existe un dueno con id " + duenoId));
        Veterinario veterinario = veterinarioRepository.findById(veterinarioId)
                .orElseThrow(() -> new NoSuchElementException("No existe un veterinario con id " + veterinarioId));

        if (animal.getFechaRegistro() == null) {
            animal.setFechaRegistro(LocalDate.now());
        }
        if (esTextoVacio(animal.getEstadoSalud())) {
            animal.setEstadoSalud("Pendiente de evaluacion");
        }

        animal.setDueno(dueno);
        animal.setVeterinario(veterinario);
        return animalRepository.save(animal);
    }

    private void validarAnimal(Animal animal) {
        if (animal == null) {
            throw new IllegalArgumentException("Los datos del animal son obligatorios");
        }
        if (esTextoVacio(animal.getNombre())) {
            throw new IllegalArgumentException("El nombre del animal es obligatorio");
        }
        if (esTextoVacio(animal.getEspecie())) {
            throw new IllegalArgumentException("La especie del animal es obligatoria");
        }
        if (animal.getEdad() != null && animal.getEdad() < 0) {
            throw new IllegalArgumentException("La edad del animal no puede ser negativa");
        }
    }

    private boolean esTextoVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }
}
