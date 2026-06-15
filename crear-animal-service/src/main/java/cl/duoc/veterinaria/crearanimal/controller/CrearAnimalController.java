package cl.duoc.veterinaria.crearanimal.controller;

import cl.duoc.veterinaria.domain.model.Animal;
import cl.duoc.veterinaria.crearanimal.service.CrearAnimalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Animales", description = "Registro de animales")
public class CrearAnimalController {
    private final CrearAnimalService service;

    public CrearAnimalController(CrearAnimalService service) {
        this.service = service;
    }

    @PostMapping("/crear-animal")
    @Operation(
            summary = "Crear animal",
            description = "Registra un animal y lo relaciona con un dueno y un veterinario existentes.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Animal creado correctamente"),
                    @ApiResponse(responseCode = "400", description = "Datos del animal invalidos"),
                    @ApiResponse(responseCode = "404", description = "Dueno o veterinario no encontrado")
            }
    )
    public ResponseEntity<Animal> crearAnimal(
            @Valid @RequestBody Animal animal,
            @RequestParam Long duenoId,
            @RequestParam Long veterinarioId
    ) {
        return ResponseEntity.ok(service.ejecutar(animal, duenoId, veterinarioId));
    }
}
