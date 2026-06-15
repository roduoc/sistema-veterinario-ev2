package cl.duoc.veterinaria.buscaranimal.controller;

import cl.duoc.veterinaria.domain.model.Animal;
import cl.duoc.veterinaria.buscaranimal.service.BuscarAnimalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuscarAnimalController {
    private final BuscarAnimalService service;

    public BuscarAnimalController(BuscarAnimalService service) {
        this.service = service;
    }

    @GetMapping("/buscar-animal")
    public ResponseEntity<Animal> buscarAnimal(@RequestParam Long id) {
        return ResponseEntity.ok(service.ejecutar(id));
    }
}
