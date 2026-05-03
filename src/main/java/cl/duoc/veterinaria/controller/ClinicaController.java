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
}
