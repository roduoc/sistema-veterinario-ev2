package cl.duoc.veterinaria.crearanimal.controller;

import cl.duoc.veterinaria.domain.model.Animal;
import cl.duoc.veterinaria.crearanimal.service.CrearAnimalService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrearAnimalController {
    private final CrearAnimalService service;

    public CrearAnimalController(CrearAnimalService service) {
        this.service = service;
    }

    @PostMapping("/crear-animal")
    public ResponseEntity<Animal> crearAnimal(
            @Valid @RequestBody Animal animal,
            @RequestParam Long duenoId,
            @RequestParam Long veterinarioId
    ) {
        return ResponseEntity.ok(service.ejecutar(animal, duenoId, veterinarioId));
    }
}
