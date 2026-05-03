package cl.duoc.veterinaria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Dueno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del dueno es obligatorio")
    private String nombre;

    @NotBlank(message = "El telefono del dueno es obligatorio")
    private String telefono;

    @Email(message = "El email del dueno debe tener un formato valido")
    private String email;

    private String direccion;

    @JsonIgnore
    @OneToMany(mappedBy = "dueno")
    private List<Animal> animales = new ArrayList<>();
}
