package cl.duoc.veterinaria.listarveterinarios.controller;

import cl.duoc.veterinaria.domain.model.Veterinario;
import cl.duoc.veterinaria.listarveterinarios.service.ListarVeterinariosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Veterinarios", description = "Consulta de veterinarios registrados")
public class ListarVeterinariosController {
    private final ListarVeterinariosService service;

    public ListarVeterinariosController(ListarVeterinariosService service) {
        this.service = service;
    }

    @GetMapping("/listar-veterinarios")
    @Operation(
            summary = "Listar veterinarios",
            description = "Retorna todos los veterinarios registrados en la clinica.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
            }
    )
    public ResponseEntity<List<Veterinario>> listarVeterinarios() {
        return ResponseEntity.ok(service.ejecutar());
    }
}
