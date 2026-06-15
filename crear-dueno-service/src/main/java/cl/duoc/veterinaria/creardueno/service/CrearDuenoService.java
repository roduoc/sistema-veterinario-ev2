package cl.duoc.veterinaria.creardueno.service;

import cl.duoc.veterinaria.domain.model.Dueno;
import cl.duoc.veterinaria.domain.repository.DuenoRepository;
import org.springframework.stereotype.Service;

@Service
public class CrearDuenoService {
    private final DuenoRepository duenoRepository;

    public CrearDuenoService(DuenoRepository duenoRepository) {
        this.duenoRepository = duenoRepository;
    }

    public Dueno ejecutar(Dueno dueno) {
        validarDueno(dueno);
        return duenoRepository.save(dueno);
    }

    private void validarDueno(Dueno dueno) {
        if (dueno == null) {
            throw new IllegalArgumentException("Los datos del dueno son obligatorios");
        }
        if (esTextoVacio(dueno.getNombre())) {
            throw new IllegalArgumentException("El nombre del dueno es obligatorio");
        }
        if (esTextoVacio(dueno.getTelefono())) {
            throw new IllegalArgumentException("El telefono del dueno es obligatorio");
        }
    }

    private boolean esTextoVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }
}
