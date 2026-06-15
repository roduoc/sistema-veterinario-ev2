package cl.duoc.veterinaria.actualizaranimal.controller;

import cl.duoc.veterinaria.domain.model.Animal;
import cl.duoc.veterinaria.actualizaranimal.service.ActualizarAnimalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Animales", description = "Actualizacion de datos de animales")
public class ActualizarAnimalController {
    private final ActualizarAnimalService service;

    public ActualizarAnimalController(ActualizarAnimalService service) {
        this.service = service;
    }

    @PutMapping("/actualizar-animal")
    @Operation(
            summary = "Actualizar animal",
            description = "Actualiza los datos principales de un animal existente.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Animal actualizado correctamente"),
                    @ApiResponse(responseCode = "400", description = "Datos invalidos"),
                    @ApiResponse(responseCode = "404", description = "Animal no encontrado")
            }
    )
    public ResponseEntity<Animal> actualizarAnimal(@RequestParam Long id, @Valid @RequestBody Animal animal) {
        return ResponseEntity.ok(service.ejecutar(id, animal));
    }
}
