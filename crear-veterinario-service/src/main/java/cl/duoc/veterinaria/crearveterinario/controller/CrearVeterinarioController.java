package cl.duoc.veterinaria.crearveterinario.controller;

import cl.duoc.veterinaria.domain.model.Veterinario;
import cl.duoc.veterinaria.crearveterinario.service.CrearVeterinarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Veterinarios", description = "Registro de veterinarios")
public class CrearVeterinarioController {
    private final CrearVeterinarioService service;

    public CrearVeterinarioController(CrearVeterinarioService service) {
        this.service = service;
    }

    @PostMapping("/crear-veterinario")
    @Operation(
            summary = "Crear veterinario",
            description = "Registra un veterinario con especialidad y datos de contacto.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Veterinario creado correctamente"),
                    @ApiResponse(responseCode = "400", description = "Datos del veterinario invalidos")
            }
    )
    public ResponseEntity<Veterinario> crearVeterinario(@Valid @RequestBody Veterinario veterinario) {
        return ResponseEntity.ok(service.ejecutar(veterinario));
    }
}
