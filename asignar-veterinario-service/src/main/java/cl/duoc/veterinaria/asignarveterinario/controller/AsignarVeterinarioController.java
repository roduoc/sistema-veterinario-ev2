package cl.duoc.veterinaria.asignarveterinario.controller;

import cl.duoc.veterinaria.domain.model.Animal;
import cl.duoc.veterinaria.asignarveterinario.service.AsignarVeterinarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Veterinarios", description = "Asignacion de veterinarios a animales")
public class AsignarVeterinarioController {
    private final AsignarVeterinarioService service;

    public AsignarVeterinarioController(AsignarVeterinarioService service) {
        this.service = service;
    }

    @PutMapping("/asignar-veterinario")
    @Operation(
            summary = "Asignar veterinario",
            description = "Valida el animal mediante buscar-animal-service y asigna un veterinario existente.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Veterinario asignado correctamente"),
                    @ApiResponse(responseCode = "404", description = "Animal o veterinario no encontrado"),
                    @ApiResponse(responseCode = "500", description = "Error de comunicacion con servicio remoto")
            }
    )
    public ResponseEntity<Animal> asignarVeterinario(@RequestParam Long animalId, @RequestParam Long veterinarioId) {
        return ResponseEntity.ok(service.ejecutar(animalId, veterinarioId));
    }
}
