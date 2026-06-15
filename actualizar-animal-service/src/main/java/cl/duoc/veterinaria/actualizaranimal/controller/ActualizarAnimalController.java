package cl.duoc.veterinaria.actualizaranimal.controller;

import cl.duoc.veterinaria.domain.model.Animal;
import cl.duoc.veterinaria.actualizaranimal.service.ActualizarAnimalService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActualizarAnimalController {
    private final ActualizarAnimalService service;

    public ActualizarAnimalController(ActualizarAnimalService service) {
        this.service = service;
    }

    @PutMapping("/actualizar-animal")
    public ResponseEntity<Animal> actualizarAnimal(@RequestParam Long id, @Valid @RequestBody Animal animal) {
        return ResponseEntity.ok(service.ejecutar(id, animal));
    }
}
