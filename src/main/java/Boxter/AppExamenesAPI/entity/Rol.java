package Boxter.AppExamenesAPI.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Rol {

    @Id
    private Long rolId;
    private String rolNombre;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rol")
    @JsonIgnore
    private Set<UsuarioRol> usuarioRoles = new HashSet<>();

}
