package cl.duoc.veterinaria.borraranimal.controller;

import cl.duoc.veterinaria.borraranimal.service.BorrarAnimalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BorrarAnimalController {
    private final BorrarAnimalService service;

    public BorrarAnimalController(BorrarAnimalService service) {
        this.service = service;
    }

    @DeleteMapping("/borrar-animal")
    public ResponseEntity<Void> borrarAnimal(@RequestParam Long id) {
        service.ejecutar(id);
        return ResponseEntity.noContent().build();
    }
}
