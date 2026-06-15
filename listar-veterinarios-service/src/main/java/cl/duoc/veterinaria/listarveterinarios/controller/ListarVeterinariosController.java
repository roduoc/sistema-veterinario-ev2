package cl.duoc.veterinaria.listarveterinarios.controller;

import cl.duoc.veterinaria.domain.model.Veterinario;
import cl.duoc.veterinaria.listarveterinarios.service.ListarVeterinariosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ListarVeterinariosController {
    private final ListarVeterinariosService service;

    public ListarVeterinariosController(ListarVeterinariosService service) {
        this.service = service;
    }

    @GetMapping("/listar-veterinarios")
    public ResponseEntity<List<Veterinario>> listarVeterinarios() {
        return ResponseEntity.ok(service.ejecutar());
    }
}
