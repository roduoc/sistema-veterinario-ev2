package cl.duoc.veterinaria.creardueno.controller;

import cl.duoc.veterinaria.domain.model.Dueno;
import cl.duoc.veterinaria.creardueno.service.CrearDuenoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrearDuenoController {
    private final CrearDuenoService service;

    public CrearDuenoController(CrearDuenoService service) {
        this.service = service;
    }

    @PostMapping("/crear-dueno")
    public ResponseEntity<Dueno> crearDueno(@Valid @RequestBody Dueno dueno) {
        return ResponseEntity.ok(service.ejecutar(dueno));
    }
}
