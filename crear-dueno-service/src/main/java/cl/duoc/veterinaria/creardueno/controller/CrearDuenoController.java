package cl.duoc.veterinaria.creardueno.controller;

import cl.duoc.veterinaria.domain.model.Dueno;
import cl.duoc.veterinaria.creardueno.service.CrearDuenoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Duenos", description = "Registro de duenos de animales")
public class CrearDuenoController {
    private final CrearDuenoService service;

    public CrearDuenoController(CrearDuenoService service) {
        this.service = service;
    }

    @PostMapping("/crear-dueno")
    @Operation(
            summary = "Crear dueno",
            description = "Registra un dueno con datos de contacto.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Dueno creado correctamente"),
                    @ApiResponse(responseCode = "400", description = "Datos del dueno invalidos")
            }
    )
    public ResponseEntity<Dueno> crearDueno(@Valid @RequestBody Dueno dueno) {
        return ResponseEntity.ok(service.ejecutar(dueno));
    }
}
