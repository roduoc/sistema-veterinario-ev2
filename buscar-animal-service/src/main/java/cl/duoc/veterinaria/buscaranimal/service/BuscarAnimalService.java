package cl.duoc.veterinaria.buscaranimal.service;

import cl.duoc.veterinaria.domain.model.Animal;
import cl.duoc.veterinaria.domain.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class BuscarAnimalService {
    private final AnimalRepository animalRepository;

    public BuscarAnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public Animal ejecutar(Long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No existe un animal con id " + id));
    }
}
