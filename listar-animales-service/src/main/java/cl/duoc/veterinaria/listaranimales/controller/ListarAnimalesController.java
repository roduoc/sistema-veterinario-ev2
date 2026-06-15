package cl.duoc.veterinaria.listaranimales.controller;

import cl.duoc.veterinaria.domain.model.Animal;
import cl.duoc.veterinaria.listaranimales.service.ListarAnimalesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ListarAnimalesController {

    private final ListarAnimalesService service;

    public ListarAnimalesController(ListarAnimalesService service) {
        this.service = service;
    }

    @GetMapping("/listar-animales")
    public ResponseEntity<List<Animal>> listarAnimales() {
        return ResponseEntity.ok(service.ejecutar());
    }
}
