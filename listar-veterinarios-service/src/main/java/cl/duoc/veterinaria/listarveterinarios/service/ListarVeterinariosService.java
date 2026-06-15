package cl.duoc.veterinaria.listarveterinarios.service;

import cl.duoc.veterinaria.domain.model.Veterinario;
import cl.duoc.veterinaria.domain.repository.VeterinarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarVeterinariosService {
    private final VeterinarioRepository veterinarioRepository;

    public ListarVeterinariosService(VeterinarioRepository veterinarioRepository) {
        this.veterinarioRepository = veterinarioRepository;
    }

    public List<Veterinario> ejecutar() {
        return veterinarioRepository.findAll();
    }
}
