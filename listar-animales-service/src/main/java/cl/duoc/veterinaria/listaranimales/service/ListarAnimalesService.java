package cl.duoc.veterinaria.listaranimales.service;

import cl.duoc.veterinaria.domain.model.Animal;
import cl.duoc.veterinaria.domain.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarAnimalesService {
    private final AnimalRepository animalRepository;

    public ListarAnimalesService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> ejecutar() {
        return animalRepository.findAll();
    }
}
