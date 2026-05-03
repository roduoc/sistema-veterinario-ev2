package cl.duoc.veterinaria.controller;

import cl.duoc.veterinaria.model.Animal;
import cl.duoc.veterinaria.model.Dueno;
import cl.duoc.veterinaria.model.Veterinario;
import cl.duoc.veterinaria.service.ClinicaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping
public class ClinicaController {

    private final ClinicaService clinicaService;

    public ClinicaController(ClinicaService clinicaService) {
        this.clinicaService = clinicaService;
    }

    @GetMapping("/listar-animales")
    public ResponseEntity<List<Animal>> listarAnimales() {
        return ResponseEntity.ok(clinicaService.listarAnimales());
    }

    @GetMapping("/buscar-animal")
    public ResponseEntity<Animal> buscarAnimal(@RequestParam Long id) {
        return ResponseEntity.ok(clinicaService.buscarAnimal(id));
    }

    @PostMapping("/crear-animal")
    public ResponseEntity<Animal> crearAnimal(
            @Valid @RequestBody Animal animal,
            @RequestParam Long duenoId,
            @RequestParam Long veterinarioId
    ) {
        return ResponseEntity.ok(clinicaService.crearAnimal(animal, duenoId, veterinarioId));
    }

    @PutMapping("/actualizar-animal")
    public ResponseEntity<Animal> actualizarAnimal(@RequestParam Long id, @Valid @RequestBody Animal animal) {
        return ResponseEntity.ok(clinicaService.actualizarAnimal(id, animal));
    }

    @DeleteMapping("/borrar-animal")
    public ResponseEntity<Void> borrarAnimal(@RequestParam Long id) {
        clinicaService.borrarAnimal(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/crear-dueno")
    public ResponseEntity<Dueno> crearDueno(@Valid @RequestBody Dueno dueno) {
        return ResponseEntity.ok(clinicaService.crearDueno(dueno));
    }

    @GetMapping("/listar-duenos")
    public ResponseEntity<List<Dueno>> listarDuenos() {
        return ResponseEntity.ok(clinicaService.listarDuenos());
    }

    @PostMapping("/crear-veterinario")
    public ResponseEntity<Veterinario> crearVeterinario(@Valid @RequestBody Veterinario veterinario) {
        return ResponseEntity.ok(clinicaService.crearVeterinario(veterinario));
    }

    @GetMapping("/listar-veterinarios")
    public ResponseEntity<List<Veterinario>> listarVeterinarios() {
        return ResponseEntity.ok(clinicaService.listarVeterinarios());
    }

    @PutMapping("/asignar-veterinario")
    public ResponseEntity<Animal> asignarVeterinario(
            @RequestParam Long animalId,
            @RequestParam Long veterinarioId
    ) {
        return ResponseEntity.ok(clinicaService.asignarVeterinario(animalId, veterinarioId));
    }
}