package cl.duoc.veterinaria.borraranimal.controller;

import cl.duoc.veterinaria.borraranimal.service.BorrarAnimalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Animales", description = "Eliminacion de animales")
public class BorrarAnimalController {
    private final BorrarAnimalService service;

    public BorrarAnimalController(BorrarAnimalService service) {
        this.service = service;
    }

    @DeleteMapping("/borrar-animal")
    @Operation(
            summary = "Borrar animal",
            description = "Elimina un animal existente por id.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Animal eliminado correctamente"),
                    @ApiResponse(responseCode = "404", description = "Animal no encontrado")
            }
    )
    public ResponseEntity<Void> borrarAnimal(@RequestParam Long id) {
        service.ejecutar(id);
        return ResponseEntity.noContent().build();
    }
}
