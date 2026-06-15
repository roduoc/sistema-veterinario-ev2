package cl.duoc.veterinaria.crearveterinario.service;

import cl.duoc.veterinaria.domain.model.Veterinario;
import cl.duoc.veterinaria.domain.repository.VeterinarioRepository;
import org.springframework.stereotype.Service;

@Service
public class CrearVeterinarioService {
    private final VeterinarioRepository veterinarioRepository;

    public CrearVeterinarioService(VeterinarioRepository veterinarioRepository) {
        this.veterinarioRepository = veterinarioRepository;
    }

    public Veterinario ejecutar(Veterinario veterinario) {
        validarVeterinario(veterinario);
        return veterinarioRepository.save(veterinario);
    }

    private void validarVeterinario(Veterinario veterinario) {
        if (veterinario == null) {
            throw new IllegalArgumentException("Los datos del veterinario son obligatorios");
        }
        if (esTextoVacio(veterinario.getNombre())) {
            throw new IllegalArgumentException("El nombre del veterinario es obligatorio");
        }
        if (esTextoVacio(veterinario.getEspecialidad())) {
            throw new IllegalArgumentException("La especialidad del veterinario es obligatoria");
        }
    }

    private boolean esTextoVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }
}
