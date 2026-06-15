package cl.duoc.veterinaria.actualizaranimal.service;

import cl.duoc.veterinaria.domain.model.Animal;
import cl.duoc.veterinaria.domain.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ActualizarAnimalService {
    private final AnimalRepository animalRepository;

    public ActualizarAnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public Animal ejecutar(Long id, Animal datosAnimal) {
        validarAnimal(datosAnimal);
        Animal animalExistente = animalRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No existe un animal con id " + id));

        animalExistente.setNombre(datosAnimal.getNombre());
        animalExistente.setEspecie(datosAnimal.getEspecie());
        animalExistente.setRaza(datosAnimal.getRaza());
        animalExistente.setEdad(datosAnimal.getEdad());
        animalExistente.setEstadoSalud(datosAnimal.getEstadoSalud());

        if (datosAnimal.getFechaRegistro() != null) {
            animalExistente.setFechaRegistro(datosAnimal.getFechaRegistro());
        }

        return animalRepository.save(animalExistente);
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
