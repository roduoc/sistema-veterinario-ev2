package cl.duoc.veterinaria.service;

import cl.duoc.veterinaria.model.Animal;
import cl.duoc.veterinaria.model.Dueno;
import cl.duoc.veterinaria.model.Veterinario;
import cl.duoc.veterinaria.repository.AnimalRepository;
import cl.duoc.veterinaria.repository.DuenoRepository;
import cl.duoc.veterinaria.repository.VeterinarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClinicaService {

    private static final Logger logger = LoggerFactory.getLogger(ClinicaService.class);

    private final AnimalRepository animalRepository;
    private final DuenoRepository duenoRepository;
    private final VeterinarioRepository veterinarioRepository;

    public ClinicaService(
            AnimalRepository animalRepository,
            DuenoRepository duenoRepository,
            VeterinarioRepository veterinarioRepository
    ) {
        this.animalRepository = animalRepository;
        this.duenoRepository = duenoRepository;
        this.veterinarioRepository = veterinarioRepository;
    }

    public List<Animal> listarAnimales() {
        logger.info("Listando animales registrados");
        return animalRepository.findAll();
    }

    public Animal buscarAnimal(Long id) {
        logger.info("Buscando animal con id {}", id);
        return animalRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No existe un animal con id " + id));
    }

    public Animal crearAnimal(Animal animal, Long duenoId, Long veterinarioId) {
        logger.info("Creando animal para dueno {} y veterinario {}", duenoId, veterinarioId);
        validarAnimal(animal);

        Dueno dueno = duenoRepository.findById(duenoId)
                .orElseThrow(() -> new NoSuchElementException("No existe un dueno con id " + duenoId));
        Veterinario veterinario = veterinarioRepository.findById(veterinarioId)
                .orElseThrow(() -> new NoSuchElementException("No existe un veterinario con id " + veterinarioId));

        if (animal.getFechaRegistro() == null) {
            animal.setFechaRegistro(LocalDate.now());
        }

        if (esTextoVacio(animal.getEstadoSalud())) {
            animal.setEstadoSalud("Pendiente de evaluacion");
        }

        animal.setDueno(dueno);
        animal.setVeterinario(veterinario);
        return animalRepository.save(animal);
    }

    public Animal actualizarAnimal(Long id, Animal datosAnimal) {
        logger.info("Actualizando animal con id {}", id);
        validarAnimal(datosAnimal);

        Animal animalExistente = buscarAnimal(id);
        animalExistente.setNombre(datosAnimal.getNombre());
        animalExistente.setEspecie(datosAnimal.getEspecie());
        animalExistente.setRaza(datosAnimal.getRaza());
        animalExistente.setEdad(datosAnimal.getEdad());
        animalExistente.setEstadoSalud(datosAnimal.getEstadoSalud());

        if (datosAnimal.getFechaRegistro() != null) {
            animalExistente.setFechaRegistro(datosAnimal.getFechaRegistro());
        }

        return animalRepository.save(animalExistente);
    }

    public void borrarAnimal(Long id) {
        logger.info("Borrando animal con id {}", id);
        Animal animal = buscarAnimal(id);
        animalRepository.delete(animal);
    }

    public Dueno crearDueno(Dueno dueno) {
        logger.info("Creando dueno");
        validarDueno(dueno);
        return duenoRepository.save(dueno);
    }

    public List<Dueno> listarDuenos() {
        logger.info("Listando duenos registrados");
        return duenoRepository.findAll();
    }

    public Veterinario crearVeterinario(Veterinario veterinario) {
        logger.info("Creando veterinario");
        validarVeterinario(veterinario);
        return veterinarioRepository.save(veterinario);
    }

    public List<Veterinario> listarVeterinarios() {
        logger.info("Listando veterinarios registrados");
        return veterinarioRepository.findAll();
    }

    public Animal asignarVeterinario(Long animalId, Long veterinarioId) {
        logger.info("Asignando veterinario {} al animal {}", veterinarioId, animalId);
        Animal animal = buscarAnimal(animalId);
        Veterinario veterinario = veterinarioRepository.findById(veterinarioId)
                .orElseThrow(() -> new NoSuchElementException("No existe un veterinario con id " + veterinarioId));

        animal.setVeterinario(veterinario);
        return animalRepository.save(animal);
    }

    private void validarAnimal(Animal animal) {
        if (animal == null) {
            throw new IllegalArgumentException("Los datos del animal son obligatorios");
        }
        if (esTextoVacio(animal.getNombre())) {
            throw new IllegalArgumentException("El nombre del animal es obligatorio");
        }
        if (esTextoVacio(animal.getEspecie())) {
            throw new IllegalArgumentException("La especie del animal es obligatoria");
        }
        if (animal.getEdad() != null && animal.getEdad() < 0) {
            throw new IllegalArgumentException("La edad del animal no puede ser negativa");
        }
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