package cl.duoc.veterinaria.listaranimales.controller;

import cl.duoc.veterinaria.domain.model.Animal;
import cl.duoc.veterinaria.listaranimales.service.ListarAnimalesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Animales", description = "Consulta de animales registrados")
public class ListarAnimalesController {

    private final ListarAnimalesService service;

    public ListarAnimalesController(ListarAnimalesService service) {
        this.service = service;
    }

    @GetMapping("/listar-animales")
    @Operation(
            summary = "Listar animales",
            description = "Retorna todos los animales registrados en la clinica veterinaria.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
            }
    )
    public ResponseEntity<List<Animal>> listarAnimales() {
        return ResponseEntity.ok(service.ejecutar());
    }
}
