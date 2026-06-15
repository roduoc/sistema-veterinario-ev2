package cl.duoc.veterinaria.asignarveterinario.controller;

import cl.duoc.veterinaria.domain.model.Animal;
import cl.duoc.veterinaria.asignarveterinario.service.AsignarVeterinarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsignarVeterinarioController {
    private final AsignarVeterinarioService service;

    public AsignarVeterinarioController(AsignarVeterinarioService service) {
        this.service = service;
    }

    @PutMapping("/asignar-veterinario")
    public ResponseEntity<Animal> asignarVeterinario(@RequestParam Long animalId, @RequestParam Long veterinarioId) {
        return ResponseEntity.ok(service.ejecutar(animalId, veterinarioId));
    }
}
