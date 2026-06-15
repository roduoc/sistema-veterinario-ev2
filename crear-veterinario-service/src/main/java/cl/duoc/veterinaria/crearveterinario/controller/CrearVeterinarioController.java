package cl.duoc.veterinaria.crearveterinario.controller;

import cl.duoc.veterinaria.domain.model.Veterinario;
import cl.duoc.veterinaria.crearveterinario.service.CrearVeterinarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrearVeterinarioController {
    private final CrearVeterinarioService service;

    public CrearVeterinarioController(CrearVeterinarioService service) {
        this.service = service;
    }

    @PostMapping("/crear-veterinario")
    public ResponseEntity<Veterinario> crearVeterinario(@Valid @RequestBody Veterinario veterinario) {
        return ResponseEntity.ok(service.ejecutar(veterinario));
    }
}
