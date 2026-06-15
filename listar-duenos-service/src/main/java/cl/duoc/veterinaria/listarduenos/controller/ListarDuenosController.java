package cl.duoc.veterinaria.listarduenos.controller;

import cl.duoc.veterinaria.domain.model.Dueno;
import cl.duoc.veterinaria.listarduenos.service.ListarDuenosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ListarDuenosController {
    private final ListarDuenosService service;

    public ListarDuenosController(ListarDuenosService service) {
        this.service = service;
    }

    @GetMapping("/listar-duenos")
    public ResponseEntity<List<Dueno>> listarDuenos() {
        return ResponseEntity.ok(service.ejecutar());
    }
}
