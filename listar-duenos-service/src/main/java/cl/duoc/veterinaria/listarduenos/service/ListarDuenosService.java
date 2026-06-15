package cl.duoc.veterinaria.listarduenos.service;

import cl.duoc.veterinaria.domain.model.Dueno;
import cl.duoc.veterinaria.domain.repository.DuenoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarDuenosService {
    private final DuenoRepository duenoRepository;

    public ListarDuenosService(DuenoRepository duenoRepository) {
        this.duenoRepository = duenoRepository;
    }

    public List<Dueno> ejecutar() {
        return duenoRepository.findAll();
    }
}
