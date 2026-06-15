package cl.duoc.veterinaria.buscaranimal.controller;

import cl.duoc.veterinaria.domain.model.Animal;
import cl.duoc.veterinaria.buscaranimal.service.BuscarAnimalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Animales", description = "Busqueda de animales por identificador")
public class BuscarAnimalController {
    private final BuscarAnimalService service;

    public BuscarAnimalController(BuscarAnimalService service) {
        this.service = service;
    }

    @GetMapping("/buscar-animal")
    @Operation(
            summary = "Buscar animal",
            description = "Busca un animal por id y retorna sus datos con dueno y veterinario asignado.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Animal encontrado"),
                    @ApiResponse(responseCode = "404", description = "Animal no encontrado")
            }
    )
    public ResponseEntity<Animal> buscarAnimal(@RequestParam Long id) {
        return ResponseEntity.ok(service.ejecutar(id));
    }
}
