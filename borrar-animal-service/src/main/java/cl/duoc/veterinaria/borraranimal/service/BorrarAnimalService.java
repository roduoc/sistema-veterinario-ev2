package cl.duoc.veterinaria.borraranimal.service;

import cl.duoc.veterinaria.domain.model.Animal;
import cl.duoc.veterinaria.domain.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class BorrarAnimalService {
    private final AnimalRepository animalRepository;

    public BorrarAnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public void ejecutar(Long id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No existe un animal con id " + id));
        animalRepository.delete(animal);
    }
}
