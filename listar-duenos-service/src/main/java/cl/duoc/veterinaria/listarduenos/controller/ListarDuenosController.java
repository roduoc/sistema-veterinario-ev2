package cl.duoc.veterinaria.listarduenos.controller;

import cl.duoc.veterinaria.domain.model.Dueno;
import cl.duoc.veterinaria.listarduenos.service.ListarDuenosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Duenos", description = "Consulta de duenos registrados")
public class ListarDuenosController {
    private final ListarDuenosService service;

    public ListarDuenosController(ListarDuenosService service) {
        this.service = service;
    }

    @GetMapping("/listar-duenos")
    @Operation(
            summary = "Listar duenos",
            description = "Retorna todos los duenos registrados en la clinica.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
            }
    )
    public ResponseEntity<List<Dueno>> listarDuenos() {
        return ResponseEntity.ok(service.ejecutar());
    }
}
